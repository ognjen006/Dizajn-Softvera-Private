package rs.raf.gui.swing;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

    public static final String TITLE = "Paraf";

    private static final String FILE_MENU_TITLE = "File";
    private static final String EXIT_ITEM_TITLE = "Exit";
    private static final int TEXT_MARGIN = 8;
    private static final int FONT_SIZE = 14;

    private final JTextArea textArea;

    public MainFrame() {
        setTitle(TITLE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setJMenuBar(createMenuBar());

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE));
        textArea.setBorder(BorderFactory.createEmptyBorder(TEXT_MARGIN, TEXT_MARGIN, TEXT_MARGIN, TEXT_MARGIN));

        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu(FILE_MENU_TITLE);
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem exitItem = new JMenuItem(EXIT_ITEM_TITLE);
        exitItem.addActionListener(event -> System.exit(0));
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        return menuBar;
    }
}
