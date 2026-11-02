package rs.raf.controller;

public class ActionManager {

    private static ActionManager instance;

    private final NewFolderAction newFolderAction;
    private final ExitAction exitAction;

    private ActionManager() {
        newFolderAction = new NewFolderAction();
        exitAction = new ExitAction();
    }

    public static ActionManager getInstance() {
        if (instance == null) {
            instance = new ActionManager();
        }

        return instance;
    }

    public NewFolderAction getNewFolderAction() {
        return newFolderAction;
    }

    public ExitAction getExitAction() {
        return exitAction;
    }
}