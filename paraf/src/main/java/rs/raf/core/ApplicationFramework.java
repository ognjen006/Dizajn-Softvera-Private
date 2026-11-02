package rs.raf.core;

import rs.raf.gui.swing.MainFrame;
import rs.raf.model.Folder;

public class ApplicationFramework {

    private static ApplicationFramework instance;

    private Folder model;

    private ApplicationFramework() {
        model = new Folder("Project", null);
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




    public Folder getModel() {
        return model;
    }
}