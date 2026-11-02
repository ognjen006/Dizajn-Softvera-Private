package rs.raf.controller;

import rs.raf.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public abstract class ParafAbstractAction extends AbstractAction {

    protected ParafAbstractAction(String name, String description) {
        super(name);
        putValue(SHORT_DESCRIPTION, description);
    }

    @Override
    public final void actionPerformed(ActionEvent event) {
        try {
            perform(event);
        } catch (Exception exception) {
            String message = exception.getMessage();

            if (message == null || message.isBlank()) {
                message = "Akcija nije uspela.";
            }

            JOptionPane.showMessageDialog(
                    MainFrame.getInstance(),
                    message,
                    "Greska",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    protected abstract void perform(ActionEvent event) throws Exception;
}