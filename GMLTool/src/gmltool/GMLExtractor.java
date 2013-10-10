/*
 * Copyright 2013 Alessandro Falappa <alessandro.falappa@telespazio.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gmltool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Alessandro Falappa <alessandro.falappa@telespazio.com>
 */
public class GMLExtractor {

    private final XMLInputFactory fct;
    private final HashMap<String, ArrayList<String>> shapeCoordString = new HashMap<>();

    public GMLExtractor() {
        // prepare the StAX factory
        fct = XMLInputFactory.newFactory();
        fct.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);
    }

    public HashMap<String, ArrayList<String>> getShapeCoordString() {
        return shapeCoordString;
    }

    public boolean empty() {
        return shapeCoordString.isEmpty();
    }

    public void clear() {
        shapeCoordString.clear();
    }

    public void extractShapes(String text) throws XMLStreamException {
        Reader reader = new StringReader(text);
        _extractShapes(reader);
    }

    public void extractShapes(File file) throws XMLStreamException, FileNotFoundException, IOException {
        try (Reader reader = new BufferedReader(new FileReader(file))) {
            _extractShapes(reader);
        }
    }

    private void _extractShapes(Reader reader) throws XMLStreamException {
        XMLEventReader xrd = fct.createXMLEventReader(reader);
        shapeCoordString.clear();
        while (xrd.hasNext()) {
            XMLEvent xev = xrd.nextEvent();
            if (xev.isStartElement()) {
                String tagName = xev.asStartElement().getName().getLocalPart().toLowerCase();
                if (tagName.contains(Shapes.POLY)) {
                    extractPolygon(xrd);
                } else if (tagName.contains(Shapes.CIRCLE)) {
                    extractCircle(xrd);
                } else if (tagName.contains(Shapes.ENVELOPE)) {
                    extractEnvelope(xrd);
                }
            }
        }
        xrd.close();
    }

    private void extractCircle(XMLEventReader xrd) throws XMLStreamException {
        XMLEvent xev = xrd.nextTag();
        if (xev.isStartElement() && xev.asStartElement().getName().getLocalPart().toLowerCase().contains("pos")) {
            // skip attributes,comments and whitespace
            do {
                xev = xrd.nextEvent();
            } while (xrd.hasNext() && !xev.isCharacters());
            // extract center coords
            StringBuilder cenRad = new StringBuilder(xev.asCharacters().getData());
            // skip to radius
            xev = xrd.nextTag();
            xev = xrd.nextTag();
            if (xev.isStartElement() && xev.asStartElement().getName().getLocalPart().toLowerCase().contains("radius")) {
                // skip attributes,comments and whitespace
                do {
                    xev = xrd.nextEvent();
                } while (xrd.hasNext() && !xev.isCharacters());
                // extract center coords
                cenRad.append(' ').append(xev.asCharacters().getData());
            }
            // store center coords and radius
            ArrayList<String> centerRadius = shapeCoordString.get(Shapes.CIRCLE);
            // see if there are similar shapes
            if (centerRadius == null) {
                // no similar shape create list of coords
                centerRadius = new ArrayList<>();
                shapeCoordString.put(Shapes.CIRCLE, centerRadius);
            }
            // add coords to list of shape
            centerRadius.add(cenRad.toString());
        }
    }

    private void extractEnvelope(XMLEventReader xrd) throws XMLStreamException {
        XMLEvent xev = xrd.nextTag();
        if (xev.isStartElement() && xev.asStartElement().getName().getLocalPart().toLowerCase().contains("lower")) {
            // skip attributes,comments and whitespace
            do {
                xev = xrd.nextEvent();
            } while (xrd.hasNext() && !xev.isCharacters());
            // extract lower corner coords
            StringBuilder corners = new StringBuilder(xev.asCharacters().getData());
            // skip to upper corner
            xev = xrd.nextTag();
            xev = xrd.nextTag();
            if (xev.isStartElement() && xev.asStartElement().getName().getLocalPart().toLowerCase().contains("upper")) {
                // skip attributes,comments and whitespace
                do {
                    xev = xrd.nextEvent();
                } while (xrd.hasNext() && !xev.isCharacters());
                // extract upper corner coords
                corners.append(' ').append(xev.asCharacters().getData());
            }
            // store corners coords
            ArrayList<String> cornerCoords = shapeCoordString.get(Shapes.ENVELOPE);
            // see if there are similar shapes
            if (cornerCoords == null) {
                // no similar shape create list of coords
                cornerCoords = new ArrayList<>();
                shapeCoordString.put(Shapes.ENVELOPE, cornerCoords);
            }
            // add coords to list of shape
            cornerCoords.add(corners.toString());
        }
    }

    private void extractPolygon(XMLEventReader xrd) throws XMLStreamException {
        // skip to first posList tag (exterior linear ring)
        XMLEvent xev;
        do {
            xev = xrd.nextTag();
        } while (xrd.hasNext() && !xev.asStartElement().getName().getLocalPart().toLowerCase().contains("pos"));
        StringBuilder sb = new StringBuilder();
        // deal with poslist or pos
        if (xev.asStartElement().getName().getLocalPart().toLowerCase().contains("poslist")) {
            // skip attributes,comments and whitespace
            do {
                xev = xrd.nextEvent();
            } while (xrd.hasNext() && !xev.isCharacters());
            sb.append(xev.asCharacters().getData().trim());
        } else {
            do {
                // skip attributes,comments and whitespace
                do {
                    xev = xrd.nextEvent();
                } while (xrd.hasNext() && !xev.isCharacters());
                sb.append(xev.asCharacters().getData().trim()).append(' ');
                xev = xrd.nextTag();
                xev = xrd.nextTag();
            } while (xrd.hasNext() && xev.isStartElement());
        }
        // store coords string
        ArrayList<String> coords = shapeCoordString.get(Shapes.POLY);
        // see if there are similar shapes
        if (coords == null) {
            // no similar shape create list of coords
            coords = new ArrayList<>();
            shapeCoordString.put(Shapes.POLY, coords);
        }
        // add coords to list of shape
        coords.add(sb.toString());
    }
}
