package rs.raf.gui.swing;

import rs.raf.controller.ActionManager;
import rs.raf.core.ApplicationFramework;
import rs.raf.gui.swing.TreeFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

    private static MainFrame instance;

    private final ActionManager actionManager;
    private final JTextArea textArea;
    private final JTree tree;

    private MainFrame() {
        actionManager = ActionManager.getInstance();

        setTitle("Paraf");
        setLayout(new BorderLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);

        // I zatvaranje prozora koristi ExitAction.
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                actionManager.getExitAction().actionPerformed(
                        new ActionEvent(
                                MainFrame.this,
                                ActionEvent.ACTION_PERFORMED,
                                "Exit"
                        )
                );
            }
        });

        // Meni i toolbar
        setJMenuBar(createMenuBar());
        add(createToolBar(), BorderLayout.NORTH);

        // Tekstualno polje
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));

        textArea.setBorder(
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        );

        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Stablo
        TreeFrame treeModel = new TreeFrame(
                ApplicationFramework.getInstance().getModel()
        );

        tree = new JTree(treeModel);
        tree.setEditable(false);
        tree.setRootVisible(true);
        tree.setShowsRootHandles(true);

        JScrollPane treeScrollPane = new JScrollPane(tree);
        treeScrollPane.setPreferredSize(new Dimension(230, 400));

        add(treeScrollPane, BorderLayout.WEST);
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }

        return instance;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        fileMenu.add(
                new JMenuItem(actionManager.getNewFolderAction())
        );

        fileMenu.addSeparator();

        fileMenu.add(
                new JMenuItem(actionManager.getExitAction())
        );

        menuBar.add(fileMenu);
        return menuBar;
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        toolBar.add(actionManager.getNewFolderAction());
        toolBar.addSeparator();
        toolBar.add(actionManager.getExitAction());

        return toolBar;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JTree getTree() {
        return tree;
    }
}