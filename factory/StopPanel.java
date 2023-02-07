package factory;

import javax.swing.*;

public class StopPanel extends JPanel{
    public JLabel label;
    public JButton button;
    public String text;

    public StopPanel(String text) {
        this.text = text;
        this.label = new JLabel();
        this.button = new JButton("Stop");
        this.button.setSize(100, 45);
        this.add(this.label);
        this.add(this.button);
        this.label.setText(text);
    }
}
