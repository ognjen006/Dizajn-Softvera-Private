package rs.raf.controller;

import rs.raf.gui.swing.MainFrame;
import rs.raf.model.Folder;
import rs.raf.model.Node;

import javax.swing.JTree;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;

public class NewFolderAction extends ParafAbstractAction {

    public NewFolderAction() {
        super("New Folder", "Pravi novu fasciklu.");
    }

    @Override
    protected void perform(ActionEvent event) {
        createFolder(MainFrame.getInstance().getTree());
    }

    void createFolder(JTree tree) {
        TreePath targetPath = tree.getSelectionPath();

        // Bez selekcije dodajemo u koren.
        if (targetPath == null) {
            targetPath = new TreePath(tree.getModel().getRoot());
        }

        Node selected = (Node) targetPath.getLastPathComponent();

        // Ako je izabran dokument, koristimo njegovog roditelja.
        if (!(selected instanceof Folder)) {
            targetPath = targetPath.getParentPath();
        }

        if (targetPath == null ||
                !(targetPath.getLastPathComponent() instanceof Folder)) {

            throw new IllegalStateException(
                    "Nije pronadjena odredisna fascikla."
            );
        }

        Folder target = (Folder) targetPath.getLastPathComponent();
        Folder folder = new Folder(Folder.nextDefaultName(), null);

        // Izmena modela pokrece Observer obavestavanje.
        target.AddNode(folder);

        // Oznacavamo i otkrivamo novonapravljenu fasciklu.
        TreePath newPath = targetPath.pathByAddingChild(folder);
        tree.setSelectionPath(newPath);
        tree.scrollPathToVisible(newPath);
    }
}