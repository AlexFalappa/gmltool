/*
 * Copyright 2013 afalappa.
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
package gmltool.wwind;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.SurfaceCircle;
import gov.nasa.worldwind.render.SurfacePolygon;
import gov.nasa.worldwind.render.SurfaceQuad;
import gov.nasa.worldwind.render.SurfaceSector;
import gov.nasa.worldwind.render.SurfaceShape;
import java.awt.Color;
import java.util.List;

/**
 *
 * @author Alessandro Falappa <alessandro.falappa@telespazio.com>
 */
public class GMLShapesLayer extends RenderableLayer {

    private final BasicShapeAttributes attrPoly = new BasicShapeAttributes();
    private final BasicShapeAttributes attrCirc = new BasicShapeAttributes();
    private final BasicShapeAttributes attrBox = new BasicShapeAttributes();
    private final BasicShapeAttributes attrHigh = new BasicShapeAttributes();

    public GMLShapesLayer() {
        // properties of layer
        setName("Shapes");
        setEnabled(true);
        // painting attributes for polygons
        attrPoly.setOutlineMaterial(Material.YELLOW);
        attrPoly.setOutlineWidth(2);
        attrPoly.setInteriorMaterial(Material.YELLOW);
        attrPoly.setInteriorOpacity(0.4f);
        // painting attributes for circles
        attrCirc.setOutlineMaterial(Material.RED);
        attrCirc.setOutlineWidth(2);
        attrCirc.setInteriorMaterial(Material.RED);
        attrCirc.setInteriorOpacity(0.4f);
        // painting attributes for boxes
        attrBox.setOutlineMaterial(Material.GREEN);
        attrBox.setOutlineWidth(2);
        attrBox.setInteriorMaterial(Material.GREEN);
        attrBox.setInteriorOpacity(0.4f);
        // painting attributes for hihglighted shapes
        attrHigh.setOutlineMaterial(Material.BLACK);
        attrHigh.setOutlineWidth(2);
        attrHigh.setInteriorMaterial(Material.WHITE);
        attrHigh.setInteriorOpacity(0.7f);
    }

    public void setColorPoly(Color col) {
        Material mat = new Material(col);
        attrPoly.setOutlineMaterial(mat);
        attrPoly.setInteriorMaterial(mat);
    }

    public void setColorCirc(Color col) {
        Material mat = new Material(col);
        attrCirc.setOutlineMaterial(mat);
        attrCirc.setInteriorMaterial(mat);
    }

    public void setColorBox(Color col) {
        Material mat = new Material(col);
        attrBox.setOutlineMaterial(mat);
        attrBox.setInteriorMaterial(mat);
    }

    public void addSurfCircle(double lat, double lon, double rad, String tooltip) {
        SurfaceCircle shape = new SurfaceCircle(attrCirc, LatLon.fromDegrees(lat, lon), rad);
        shape.setValue(AVKey.HOVER_TEXT, tooltip);
        setPropAndAdd(shape, tooltip);
    }

    public void addSurfCircle(double lat, double lon, double rad) {
        addSurfCircle(lat, lon, rad, null);
    }

    public void addSurfPoly(List<LatLon> coords, String tooltip) {
        SurfacePolygon shape = new SurfacePolygon(attrPoly, coords);
        setPropAndAdd(shape, tooltip);
    }

    public void addSurfPoly(List<LatLon> coords) {
        addSurfPoly(coords, null);
    }

    public void addSurfQuad(double lat, double lon, double w, double h, String tooltip) {
        SurfaceQuad shape = new SurfaceQuad(attrBox, LatLon.fromDegrees(lat, lon), w, h);
        setPropAndAdd(shape, tooltip);
    }

    public void addSurfQuad(double lat, double lon, double w, double h) {
        addSurfQuad(lat, lon, w, h, null);
    }

    public void addSurfSect(double minlat, double minlon, double maxlat, double maxlon, String tooltip) {
        SurfaceSector shape = new SurfaceSector(attrBox, Sector.fromDegrees(minlat, maxlat, minlon, maxlon));
        shape.setPathType(AVKey.LINEAR);
        setPropAndAdd(shape, tooltip);
    }

    public void addSurfSect(double minlat, double minlon, double maxlat, double maxlon) {
        addSurfSect(minlat, minlon, maxlat, maxlon, null);
    }

    public void addPath(List<Position> coords, String tooltip) {
        Path shape = new Path(coords);
        shape.setAttributes(attrPoly);
        shape.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        shape.setPathType(AVKey.GREAT_CIRCLE);
        shape.setFollowTerrain(true);
        shape.setTerrainConformance(40);
        if (tooltip != null) {
            shape.setValue(AVKey.HOVER_TEXT, "hover: " + tooltip);
            shape.setValue(AVKey.ROLLOVER_TEXT, "rollover: " + tooltip);
        }
        addRenderable(shape);
    }

    public void addPoly(List<Position> coords, String tooltip) {
        Polygon shape = new Polygon(coords);
        shape.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
        shape.setAttributes(attrPoly);
        if (tooltip != null) {
            shape.setValue(AVKey.HOVER_TEXT, "hover: " + tooltip);
            shape.setValue(AVKey.ROLLOVER_TEXT, "rollover: " + tooltip);
        }
        addRenderable(shape);
    }

    private void setPropAndAdd(SurfaceShape shape, String tooltip) {
        shape.setHighlightAttributes(attrHigh);
        if (tooltip != null) {
            shape.setValue(AVKey.HOVER_TEXT, tooltip);
        }
        addRenderable(shape);
    }
}
