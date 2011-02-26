package com.curtisbridges.swing;

import java.awt.Dimension;

import javax.swing.JInternalFrame;

/**
 * 
 */
public class TestInternalFrame extends JInternalFrame {
    private static int instances = 1;

    public TestInternalFrame() {
        super("Internal Frame - " + instances++, true, true, true, true);

        setPreferredSize(new Dimension(250, 100));
    }
}
