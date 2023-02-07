package factory;

import javax.swing.*;

public class StartPanel extends JPanel{
    public JLabel label;
    public JButton button;
    public String text;

    public StartPanel(String text) {
        this.text = text;
        this.label = new JLabel();
        this.button = new JButton("Start");
        this.button.setSize(100, 45);
        this.add(this.label);
        this.add(this.button);
        this.label.setText(text);
    }
}
