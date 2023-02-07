package factory;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class ValuePanel
        extends JPanel {
    public JLabel label;
    public JButton button;
    public String text;
    public int value;

    public ValuePanel(String text) {
        this.text = text;
        this.label = new JLabel();
        this.button = new JButton("Change value");
        this.button.setSize(100, 80);
        this.add(this.label);
        this.add(this.button);
        this.label.setText(text + this.value);
    }

    public void setValue(int value) {
        this.value = value;
        this.label.setText(this.text + value);
    }

    public int getValue() {
        return this.value;
    }
}