package main.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

public class JTabbedPaneClosable extends JTabbedPane {
    private Consumer<Integer> closeTabConsumer;

    public JTabbedPaneClosable() {
        super();
        this.closeTabConsumer = (i) -> {};
    }

    public void setCloseTabConsumer(Consumer<Integer> closeTabConsumer) {
        this.closeTabConsumer = closeTabConsumer;
    }

    @Override
    public void addTab(String title, Component component) {
        this.addTab(title, null, component);
    }

    @Override
    public void addTab(String title, Icon icon, Component component) {
        this.addTab(title, icon, component, null);
    }

    @Override
    public void addTab(String title, Icon icon, Component component, String tip) {
        super.addTab(title, icon, component, tip);
        int index = this.getTabCount() - 1;
        this.setTabComponentAt(index, new CloseTabButton(component, title, icon, index));
    }

    public void addUnclosableTab(String title, Component component) {
        addUnclosableTab(title, null, component);
    }

    public void addUnclosableTab(String title, Icon icon, Component component) {
        addUnclosableTab(title, icon, component, null);
    }

    public void addUnclosableTab(String title, Icon icon, Component component, String tip) {
        super.addTab(title, icon, component, tip);
    }

    private class CloseTabButton extends JPanel {
        private Component tab;

        public CloseTabButton(Component tab, String title, Icon icon, int index) {
            this.tab = tab;
            setOpaque(false);

            FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
            setLayout(flowLayout);

            JLabel iconLabel = new JLabel(title);
            iconLabel.setIcon(icon);
            this.add(iconLabel);

            JButton closeButton = new JButton("X");
            closeButton.setMargin(new Insets(0, 0, 0, 0));
            closeButton.addMouseListener(new CloseTabListener(tab, index));
            this.add(closeButton);
        }
    }

    public class CloseTabListener implements MouseListener {
        private Component tab;
        private int index;

        public CloseTabListener(Component tab, int index){
            this.tab = tab;
            this.index = index;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            JTabbedPane tabbedPane = (JTabbedPane) clickedButton.getParent().getParent().getParent();
            tabbedPane.remove(tab);
            closeTabConsumer.accept(index);
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
}
