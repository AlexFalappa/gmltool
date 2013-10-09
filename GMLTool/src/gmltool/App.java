package gmltool;

import gmltool.gui.MainFrame;
import gov.nasa.worldwind.Configuration;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * GMLTool application entry point.
 */
public class App {

    public static final String NAME = "GT";
    public static final String VERSION_MAJOR = "0";
    public static final String VERSION_MINOR = "1";
    public static final String VERSION = VERSION_MAJOR + "." + VERSION_MINOR;
    public static final String FRAME_TITLE = App.NAME;

    public static GMLExtractor ge = new GMLExtractor();
    public static MainFrame mf = null;

    static {
        System.setProperty("gov.nasa.worldwind.app.config.document", "cfg/wwind-cfg.xml");
        if (Configuration.isMacOS()) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", App.FRAME_TITLE);
            System.setProperty("com.apple.mrj.application.growbox.intrudes", "false");
        } else if (Configuration.isWindowsOS()) {
            // prevents flashing during window resizing
            System.setProperty("sun.awt.noerasebackground", "true");
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            //ignored
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mf = new MainFrame();
                mf.setLocationRelativeTo(null);
                mf.setVisible(true);
            }
        });
    }
}
