package rs.raf.core;

import rs.raf.gui.swing.MainFrame;

public class ApplicationFramework {

    private static ApplicationFramework instance;

    private ApplicationFramework() {
    }

    public static ApplicationFramework getInstance() {
        if (instance == null) {
            instance = new ApplicationFramework();
        }

        return instance;
    }

    public void initialize() {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
    }
}