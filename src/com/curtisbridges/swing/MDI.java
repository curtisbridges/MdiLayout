package com.curtisbridges.swing;

import java.awt.Dimension;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;


/**
 * Test out a nicer internal frame layout thought.
 */
public class MDI {
    private JDesktopPane desktop;
    private JTextArea    windowList;

    public MDI() {
        desktop = new JDesktopPane();
        desktop.setPreferredSize(new Dimension(800, 400));

        windowList = new JTextArea();
        windowList.setPreferredSize(new Dimension(200, 200));
    }

    public void closeAll() {
        JInternalFrame[] allFrames = desktop.getAllFrames();
        for (int index = 0; index < allFrames.length; index++) {
            allFrames[index].dispose();
        }
        windowList = new JTextArea("");
    }

    public void add(JInternalFrame frame) {
        desktop.add(frame);
        windowList.append(frame.getTitle() + "\n");
    }

    public JDesktopPane getDesktop() {
        return desktop;
    }

    public JTextArea getTextArea() {
        return windowList;
    }
}
