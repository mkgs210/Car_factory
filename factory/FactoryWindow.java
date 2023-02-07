package factory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactoryWindow extends JFrame implements ActionListener {
    private JList<String> list;
    private JPanel content;
    private JPanel leftPanel;
    private JPanel results;
    private JPanel conf;
    private final Logger logger;
    private ValuePanel[] miniPanels;
    private StopPanel stopPanel;
    private StartPanel startPanel;
    private Factory factory;
    private Queue<String> queue;
    private JLabel engines;
    private JLabel bodies;
    private JLabel accessories;
    private JLabel cars;
    private JLabel sold;

    public FactoryWindow th;

    public FactoryWindow() {
        ActionListener stop = new stopButton();
        ActionListener start = new startButton();
        this.setSize(900, 350);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.content = new JPanel();
        this.content.setLayout(new BoxLayout(this.content, 0));
        //this.content.setBackground(Color.green);
        this.leftPanel = new JPanel();
        this.leftPanel.setLayout(new BoxLayout(this.leftPanel, 1));
        this.leftPanel.setMinimumSize(new Dimension(600, 350));
        this.leftPanel.setMaximumSize(new Dimension(600, 350));
        //this.leftPanel.setBackground(Color.yellow);
        this.results = new JPanel();
        this.results.setLayout(new BoxLayout(this.results, 1));
        this.results.setSize(270, 350);
        this.conf = new JPanel();
        this.conf.setLayout(new BoxLayout(this.conf, 1));
        this.conf.setSize(900, 250);
        this.miniPanels = new ValuePanel[]{
                new ValuePanel("Bodies supply frequency: "),
                new ValuePanel("Engines supply frequency: "),
                new ValuePanel("Accessories supply frequency: "),
                new ValuePanel("Car create frequency: "),
                new ValuePanel("Car request frequency: ")};
        this.startPanel = new StartPanel("Start the factory: ");
        this.stopPanel = new StopPanel("Stop the factory: ");
        this.miniPanels[0].setValue(5);
        this.miniPanels[0].button.addActionListener(this);
        this.miniPanels[0].button.setActionCommand("first");
        this.miniPanels[1].setValue(5);
        this.miniPanels[1].button.addActionListener(this);
        this.miniPanels[1].button.setActionCommand("second");
        this.miniPanels[2].setValue(5);
        this.miniPanels[2].button.addActionListener(this);
        this.miniPanels[2].button.setActionCommand("third");
        this.miniPanels[3].setValue(1);
        this.miniPanels[3].button.addActionListener(this);
        this.miniPanels[3].button.setActionCommand("fourth");
        this.miniPanels[4].setValue(1);
        this.miniPanels[4].button.addActionListener(this);
        this.miniPanels[4].button.setActionCommand("fifth");
        this.startPanel.button.addActionListener(start);
        this.stopPanel.button.addActionListener(stop);
        for (int i = 0; i < 5; ++i) {
            this.conf.add((Component)this.miniPanels[i]);
        }
        conf.add((Component) this.startPanel);
        conf.add((Component) this.stopPanel);
        this.leftPanel.add(this.conf);
        this.list = new JList();
        this.queue = new LinkedList<String>();
        JPanel panel = new JPanel();
        panel.add(this.list);
        this.content.add(this.leftPanel);
        this.bodies = new JLabel("Bodies: 0");
        this.engines = new JLabel("Engines: 0");
        this.accessories = new JLabel("Accessories: 0");
        this.cars = new JLabel("Cars: 0");
        this.sold = new JLabel("Sold: 0");
        Font font = new Font(null, 1, 20);
        this.bodies.setMaximumSize(new Dimension(300, 50));
        this.bodies.setFont(font);
        this.engines.setMaximumSize(new Dimension(300, 50));
        this.engines.setFont(font);
        this.accessories.setMaximumSize(new Dimension(300, 50));
        this.accessories.setFont(font);
        this.cars.setMaximumSize(new Dimension(300, 50));
        this.cars.setFont(font);
        this.sold.setMaximumSize(new Dimension(300, 50));
        this.sold.setFont(font);
        this.results.add(this.bodies);
        this.results.add(this.engines);
        this.results.add(this.accessories);
        this.results.add(this.cars);
        this.results.add(this.sold);
        this.content.add(this.results);
        this.add(this.content);
        this.factory = new Factory(this);
        this.th = this;
        this.factory.start();
        logger = Logger.getLogger("null");
    }
    boolean stop = false;
    public class stopButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            logger.log(Level.INFO, "Factory has stopped.");
            System.out.println("Time: " + new Date() + " | Factory has stopped.");
            factory.stop();
        }
    }
    boolean start = false;
    public class startButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            logger.log(Level.INFO, "Factory has started.");
            System.out.println("Time: " + new Date() + " | Factory has started.");
            content.add(results);
            add(content);
            factory = new Factory(th);
            factory.start();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.changeValue(e.getActionCommand());
    }

    private void changeValue(String event) {
        int i;
        for (i = 0; i < 5 && !this.miniPanels[i].button.getActionCommand().equals(event); ++i) {
        }
        JPanel myPanel = new JPanel();
        JSlider slider = new JSlider(SwingConstants.HORIZONTAL,1,10,this.miniPanels[i].getValue());
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(1);
        myPanel.add(new JLabel("New value: "));
        myPanel.add(slider);
        JOptionPane.showMessageDialog(this, myPanel, "Change value", 3);
        int value = slider.getValue();
        this.miniPanels[i].setValue(value);
        this.factory.setValues(this.miniPanels[0].getValue(),
                this.miniPanels[1].getValue(),
                this.miniPanels[2].getValue(),
                this.miniPanels[3].getValue(),
                this.miniPanels[4].getValue());
    }

    public void setEngines(int value) {
        this.engines.setText("Engines: " + value);
    }

    public void setBodies(int value) {
        this.bodies.setText("Bodies: " + value);
    }

    public void setAccessories(int value) {
        this.accessories.setText("Accessories: " + value);
    }

    public void setCars(int value) {
        this.cars.setText("Cars: " + value);
    }

    public void setSoldCars() {
        this.sold.setText("Sold: " + (Integer.parseInt(this.sold.getText().split(" ")[1]) + 1));
    }
}