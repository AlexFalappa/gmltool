/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gmltool.gui;

import gmltool.App;
import gmltool.GMLExtractor;
import gmltool.Shapes;
import gmltool.wwind.GMLShapesLayer;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.event.RenderingExceptionListener;
import gov.nasa.worldwind.exception.WWAbsentRequirementException;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.globes.EarthFlat;
import gov.nasa.worldwind.globes.FlatGlobe;
import gov.nasa.worldwind.globes.Globe;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.SkyColorLayer;
import gov.nasa.worldwind.layers.SkyGradientLayer;
import gov.nasa.worldwind.view.orbit.BasicOrbitView;
import gov.nasa.worldwind.view.orbit.FlatOrbitView;
import gov.nasa.worldwindx.examples.util.StatusLayer;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Alessandro Falappa <alessandro.falappa@telespazio.com>
 */
public class MainFrame extends javax.swing.JFrame {

    private GMLShapesLayer gmlLayer = new GMLShapesLayer();
    private GMLExtractor extractor = new GMLExtractor();
    private Globe globeRound;
    private FlatGlobe globeFlat = new EarthFlat();

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("img/finmeccanica_logo.png")).getImage());
        setupWorldWind();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgModeRadio = new javax.swing.ButtonGroup();
        bNew = new javax.swing.JButton();
        bClear = new javax.swing.JButton();
        bInfo = new javax.swing.JButton();
        pGlobe = new javax.swing.JPanel();
        wwCanvas = new gov.nasa.worldwind.awt.WorldWindowGLCanvas();
        pViewParams = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        chPlaces = new javax.swing.JCheckBox();
        chBounds = new javax.swing.JCheckBox();
        chGrat = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        rbGlobe = new javax.swing.JRadioButton();
        rbMap = new javax.swing.JRadioButton();
        cbProj = new javax.swing.JComboBox();
        bLoad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GML Tool");
        setPreferredSize(new java.awt.Dimension(800, 600));

        bNew.setText("Enter GML...");
        bNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNewActionPerformed(evt);
            }
        });

        bClear.setText("Clear all shapes");
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
            }
        });

        bInfo.setText("?");
        bInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInfoActionPerformed(evt);
            }
        });

        pGlobe.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        pGlobe.setLayout(new java.awt.BorderLayout());
        pGlobe.add(wwCanvas, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getSize()+2f));
        jLabel1.setText("Cartography");

        chPlaces.setText("Place Names");
        chPlaces.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chPlacesItemStateChanged(evt);
            }
        });

        chBounds.setText("State Boundaries");
        chBounds.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chBoundsItemStateChanged(evt);
            }
        });

        chGrat.setText("Graticule");
        chGrat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chGratActionPerformed(evt);
            }
        });

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getSize()+2f));
        jLabel2.setText("Mode");

        bgModeRadio.add(rbGlobe);
        rbGlobe.setSelected(true);
        rbGlobe.setText("Globe");
        rbGlobe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbGlobeActionPerformed(evt);
            }
        });

        bgModeRadio.add(rbMap);
        rbMap.setText("Map");
        rbMap.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbMapItemStateChanged(evt);
            }
        });
        rbMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMapActionPerformed(evt);
            }
        });

        cbProj.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Plate Carrée", "Mercator", "Sinusoidal", "Modified Sin." }));
        cbProj.setEnabled(false);
        cbProj.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProjItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pViewParamsLayout = new javax.swing.GroupLayout(pViewParams);
        pViewParams.setLayout(pViewParamsLayout);
        pViewParamsLayout.setHorizontalGroup(
            pViewParamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pViewParamsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pViewParamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(pViewParamsLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(pViewParamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbMap)
                            .addComponent(rbGlobe)
                            .addComponent(chPlaces)
                            .addComponent(chBounds)
                            .addComponent(chGrat)
                            .addComponent(cbProj, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pViewParamsLayout.setVerticalGroup(
            pViewParamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pViewParamsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chPlaces)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chBounds)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chGrat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbGlobe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbMap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbProj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bLoad.setText("Load file...");
        bLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pViewParams, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pGlobe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bLoad)
                        .addGap(18, 18, 18)
                        .addComponent(bClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bInfo)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bClear, bNew});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bNew)
                    .addComponent(bClear)
                    .addComponent(bInfo)
                    .addComponent(bLoad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pGlobe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pViewParams, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setupWorldWind() {
        BasicModel model = new BasicModel();
        globeRound = model.getGlobe();
        wwCanvas.setModel(model);
//    highlightController = new HighlightController(wwCanvas, SelectEvent.ROLLOVER);
//    ttController = new ToolTipController(wwCanvas);
        // Register a rendering exception listener that's notified when
        // exceptions occur during rendering.
        wwCanvas.addRenderingExceptionListener(new RenderingExceptionListener() {
            public void exceptionThrown(Throwable t) {
                if (t instanceof WWAbsentRequirementException) {
                    StringBuilder message = new StringBuilder(
                            "Computer does not meet minimum graphics requirements.\n");
                    message.append("Please install up-to-date graphics driver and try again.\n");
                    message.append("Reason: ").append(t.getMessage());
                    message.append("\nThis program will end when you press OK.");
                    JOptionPane.showMessageDialog(MainFrame.this, message, "Unable to Start Program",
                            JOptionPane.ERROR_MESSAGE);
                    System.exit(-1);
                } else {
                    System.err.println("WorldWind library rendering problem!");
                    t.printStackTrace();
                }
            }
        });
        // add a StatusLayer
        StatusLayer slayer = new StatusLayer();
        slayer.setEventSource(wwCanvas);
        slayer.setDefaultFont(chPlaces.getFont());
        wwCanvas.getModel().getLayers().add(slayer);
        // add the shape drawing layer
        wwCanvas.getModel().getLayers().add((Layer) gmlLayer);

        // get position of place names layer
//        LayerList layers = model.getLayers();
//        int position = 0;
//        for (Layer l : layers) {
//            if (l instanceof PlaceNameLayer) {
//                position = layers.indexOf(l);
//            }
//        }
        // create footprints and AOI layers and add them before the place names
        // layer
//        footprints = new FootprintsLayer();
//        layers.add(position, footprints);
//        aois = new AOILayer();
//        layers.add(position, aois);
    }

    private void bNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNewActionPerformed
        TextEnterDialog dg = new TextEnterDialog(this, true);
        dg.setLocationRelativeTo(this);
        dg.setVisible(true);
        if (dg.getReturnStatus() == TextEnterDialog.RET_OK) {
            mapShapes();
        }
    }//GEN-LAST:event_bNewActionPerformed

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        mapClear();
    }//GEN-LAST:event_bClearActionPerformed

    private void chPlacesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chPlacesItemStateChanged
        // place names is 7th in zero based list in cfg/wwind-cfg.xml
        wwCanvas.getModel().getLayers().get(6).setEnabled(chPlaces.isSelected());
        wwCanvas.redraw();
    }//GEN-LAST:event_chPlacesItemStateChanged

    private void chBoundsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chBoundsItemStateChanged
        // boundaries is 6th in zero based list in cfg/wwind-cfg.xml
        wwCanvas.getModel().getLayers().get(5).setEnabled(chBounds.isSelected());
        wwCanvas.redraw();
    }//GEN-LAST:event_chBoundsItemStateChanged

    private void chGratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chGratActionPerformed
        // graticule is 8th in zero based list in cfg/wwind-cfg.xml
        wwCanvas.getModel().getLayers().get(7).setEnabled(chGrat.isSelected());
        wwCanvas.redraw();
    }//GEN-LAST:event_chGratActionPerformed

    private void rbMapItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbMapItemStateChanged
        cbProj.setEnabled(rbMap.isSelected());
    }//GEN-LAST:event_rbMapItemStateChanged

    private void cbProjItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProjItemStateChanged
        // Update flat globe projection
        globeFlat.setProjection(getProjection());
        wwCanvas.redraw();
    }//GEN-LAST:event_cbProjItemStateChanged

    private void bLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoadActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                App.ge.extractShapes(jfc.getSelectedFile());
                TextEnterDialog.showRecognizeDialog(this);
                mapShapes();
            } catch (XMLStreamException ex) {
                System.err.println(ex.getMessage());
                JOptionPane.showMessageDialog(this, "Unrecognized or malformed file!", "Loading failed", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                JOptionPane.showMessageDialog(this, ex.getMessage(), "File problem", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bLoadActionPerformed

    private void bInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInfoActionPerformed
        AboutDialog dlg = new AboutDialog(this, true);
        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true);
    }//GEN-LAST:event_bInfoActionPerformed

    private void rbGlobeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbGlobeActionPerformed
        cbProj.setEnabled(false);
        // Switch to round globe
        wwCanvas.getModel().setGlobe(globeRound);
        // Switch to orbit view and update with current position
        FlatOrbitView flatOrbitView = (FlatOrbitView) wwCanvas.getView();
        BasicOrbitView orbitView = new BasicOrbitView();
        orbitView.setCenterPosition(flatOrbitView.getCenterPosition());
        orbitView.setZoom(flatOrbitView.getZoom());
        orbitView.setHeading(flatOrbitView.getHeading());
        orbitView.setPitch(flatOrbitView.getPitch());
        wwCanvas.setView(orbitView);
        // change sky layer (second in wwind-cfg.xml)
        wwCanvas.getModel().getLayers().set(1, new SkyGradientLayer());
        // disable star layer (first in wwind-cfg.xml)
        wwCanvas.getModel().getLayers().get(0).setEnabled(true);
        wwCanvas.redraw();
    }//GEN-LAST:event_rbGlobeActionPerformed

    private void rbMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMapActionPerformed
        cbProj.setEnabled(true);
        // switch to flat globe
        wwCanvas.getModel().setGlobe(globeFlat);
        globeFlat.setProjection(this.getProjection());
        // Switch to flat view and update with current position
        BasicOrbitView orbitView = (BasicOrbitView) wwCanvas.getView();
        FlatOrbitView flatOrbitView = new FlatOrbitView();
        flatOrbitView.setCenterPosition(orbitView.getCenterPosition());
        flatOrbitView.setZoom(orbitView.getZoom());
        flatOrbitView.setHeading(orbitView.getHeading());
        flatOrbitView.setPitch(orbitView.getPitch());
        wwCanvas.setView(flatOrbitView);
        // change sky layer (second in wwind-cfg.xml)
        wwCanvas.getModel().getLayers().set(1, new SkyColorLayer());
        // disable star layer (first in wwind-cfg.xml)
        wwCanvas.getModel().getLayers().get(0).setEnabled(false);
        wwCanvas.redraw();
    }//GEN-LAST:event_rbMapActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClear;
    private javax.swing.JButton bInfo;
    private javax.swing.JButton bLoad;
    private javax.swing.JButton bNew;
    private javax.swing.ButtonGroup bgModeRadio;
    private javax.swing.JComboBox cbProj;
    private javax.swing.JCheckBox chBounds;
    private javax.swing.JCheckBox chGrat;
    private javax.swing.JCheckBox chPlaces;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel pGlobe;
    private javax.swing.JPanel pViewParams;
    private javax.swing.JRadioButton rbGlobe;
    private javax.swing.JRadioButton rbMap;
    private gov.nasa.worldwind.awt.WorldWindowGLCanvas wwCanvas;
    // End of variables declaration//GEN-END:variables

    public void mapShapes() {
        mapClear();
        int n = 1;
        ArrayList<String> shapes = App.ge.getShapeCoordString().get(Shapes.POLY);
        if (shapes != null) {
            System.out.println("polygons:");
            for (String coordString : shapes) {
                String[] coords = coordString.split("\\s");
                if (coords.length % 2 != 0) {
                    System.err.println("Odd number of coordinates!");
                    continue;
                }
                ArrayList<LatLon> geopoints = new ArrayList<>();
                boolean ok = true;
                for (int i = 0; i < coords.length; i += 2) {
                    // check lat in valid range
                    double lat = Double.valueOf(coords[i]);
                    if (lat < -90 || lat > 90) {
                        ok = false;
                        break;
                    }
                    // check lon in valid range
                    double lon = Double.valueOf(coords[i + 1]);
                    if (lon < -180 || lon > 180) {
                        ok = false;
                        break;
                    }
                    geopoints.add(LatLon.fromDegrees(lat, lon));
                }
                if (ok) {
                    System.out.print("  ");
                    System.out.println(coordString);
                    gmlLayer.addSurfPoly(geopoints, String.valueOf(n++));
                }
            }
        }
        shapes = App.ge.getShapeCoordString().get(Shapes.CIRCLE);
        if (shapes != null) {
            System.out.println("circles:");
            for (String cenRad : shapes) {
                System.out.print("  ");
                System.out.println(cenRad);
                String[] vals = cenRad.split("\\s");
                if (vals.length != 3) {
                    System.err.println("No center and radius!");
                    continue;
                }
                double lat = Double.valueOf(vals[0]);
                double lon = Double.valueOf(vals[1]);
                double radius = Double.valueOf(vals[2]);
                gmlLayer.addSurfCircle(lat, lon, radius, String.valueOf(n++));
            }
        }
        //TODO compute and draw envelopes
    }

    public void mapClear() {
        gmlLayer.removeAllRenderables();
        wwCanvas.redraw();
    }

    private String getProjection() {
        switch (cbProj.getSelectedIndex()) {
            case 0:
                return FlatGlobe.PROJECTION_LAT_LON;
            case 1:
                return FlatGlobe.PROJECTION_MERCATOR;
            case 2:
                return FlatGlobe.PROJECTION_SINUSOIDAL;
            case 3:
                return FlatGlobe.PROJECTION_MODIFIED_SINUSOIDAL;
        }
        throw new AssertionError();
    }
}
