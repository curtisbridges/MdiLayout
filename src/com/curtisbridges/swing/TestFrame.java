package com.curtisbridges.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The main frame for this test.
 */
public class TestFrame extends JFrame {
    private MDI      leftMDI;
    private MDI      rightMDI;

    public TestFrame() {
        super("Test DesktopPanes");
        Container content = getContentPane();
        content.setLayout(new BorderLayout());

        leftMDI = new MDI();
        leftMDI.getDesktop().setLayout(new FlowLayout(FlowLayout.CENTER));

        rightMDI = new MDI();
        rightMDI.getDesktop().setLayout(new FlowLayout(FlowLayout.LEFT));

        content.add(BorderLayout.NORTH, createToolbar());
        content.add(BorderLayout.CENTER, createContent());
        content.add(BorderLayout.SOUTH, createStatusBar());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JToolBar createToolbar() {
        JToolBar tool = new JToolBar();
        tool.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton leftButton = new JButton("Desktop 1");
        leftButton.addActionListener(new InternalFrameCreatorListener(leftMDI));

        JButton rightButton = new JButton("Desktop 2");
        rightButton.addActionListener(new InternalFrameCreatorListener(rightMDI));

        JButton closeLeftButton = new JButton("Close All 1");
        closeLeftButton.addActionListener(new InternalFrameCloseListener(leftMDI));

        JButton closeRightButton = new JButton("Close All 2");
        closeRightButton.addActionListener(new InternalFrameCloseListener(rightMDI));

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        tool.add(leftButton);
        tool.add(rightButton);
        tool.add(new JSeparator());
        tool.add(closeLeftButton);
        tool.add(closeRightButton);
        tool.add(new JSeparator());
        tool.add(exitButton);

        return tool;
    }

    private Container createContent() {
        JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitpane.setLeftComponent(leftMDI.getDesktop());
        splitpane.setRightComponent(rightMDI.getDesktop());
        splitpane.resetToPreferredSizes();

        Container container = new Container();
        container.setLayout(new BorderLayout());

        container.add(BorderLayout.WEST, createDock());
        container.add(BorderLayout.CENTER, splitpane);

        return container;
    }

    private JPanel createDock() {
        JPanel dock = new JPanel();
        dock.setLayout(new BoxLayout(dock, BoxLayout.Y_AXIS));

        JPanel leftDesk = new JPanel();
        leftDesk.setLayout(new BorderLayout());
        leftDesk.setBorder(BorderFactory.createTitledBorder("Left Desktop"));
        leftDesk.add(BorderLayout.CENTER, leftMDI.getTextArea());

        JPanel rightDesk = new JPanel();
        rightDesk.setLayout(new BorderLayout());
        rightDesk.setBorder(BorderFactory.createTitledBorder("Right Desktop"));
        rightDesk.add(BorderLayout.CENTER, rightMDI.getTextArea());

        dock.add(leftDesk);
        dock.add(rightDesk);

        return dock;
    }

    private JPanel createStatusBar() {
        JPanel status = new JPanel();
        status.add(new JLabel("Here is the status bar."));

        return status;
    }

    private class InternalFrameCloseListener implements ActionListener {
        private MDI mdi;

        public InternalFrameCloseListener(MDI m) {
            mdi = m;
        }

        public void actionPerformed(ActionEvent event) {
            mdi.closeAll();
            mdi.getDesktop().revalidate();
        }
    }

    private class InternalFrameCreatorListener implements ActionListener {
        private MDI mdi;

        public InternalFrameCreatorListener(MDI m) {
            mdi = m;
        }

        public void actionPerformed(ActionEvent event) {
            TestInternalFrame internalFrame = new TestInternalFrame();

            mdi.add(internalFrame);

            internalFrame.pack();
            internalFrame.setVisible(true);
        }
    }

    static public void main(String[] argv) {
        TestFrame frame = new TestFrame();

        frame.pack();
        frame.setVisible(true);
    }
}
