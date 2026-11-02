package rs.raf.gui.swing;

import rs.raf.model.Folder;
import rs.raf.model.Node;
import rs.raf.observer.Subscriber;

import javax.swing.SwingUtilities;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.Objects;

public class TreeFrame implements TreeModel, Subscriber {

    private final Folder root;
    private final EventListenerList listeners = new EventListenerList();

    public TreeFrame(Folder root) {
        this.root = Objects.requireNonNull(
                root,
                "Koren stabla ne sme biti null."
        );

        this.root.addSubscriber(this);
    }

    //region Citanje modela

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        Node node = (Node) parent;
        return node.getNodes().get(index);
    }

    @Override
    public int getChildCount(Object parent) {
        Node node = (Node) parent;
        return node.getNodes().size();
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((Node) node).isLeaf();
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent == null || child == null) {
            return -1;
        }

        Node node = (Node) parent;
        return node.getNodes().indexOf(child);
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        // Direktno preimenovanje u stablu trenutno nije podrzano.
    }

    //endregion

    //region Swing posmatraci

    @Override
    public void addTreeModelListener(TreeModelListener listener) {
        listeners.add(TreeModelListener.class, listener);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener listener) {
        listeners.remove(TreeModelListener.class, listener);
    }

    //endregion

    //region Osvezavanje prikaza

    @Override
    public void update() {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(this::update);
            return;
        }

        TreeModelEvent event = new TreeModelEvent(
                this,
                new Object[]{root}
        );

        for (TreeModelListener listener :
                listeners.getListeners(TreeModelListener.class)) {

            listener.treeStructureChanged(event);
        }
    }

    //endregion
}