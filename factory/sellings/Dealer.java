package factory.sellings;

import factory.FactoryWindow;
import factory.assembly.Car;
import factory.sellings.CarStorage;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dealer extends Thread {
  private int frequency;
  private final CarStorage storage;
  private final Logger logger;
  private FactoryWindow frame;

  public Dealer(int supplyFrequency, CarStorage storage, Logger log, FactoryWindow window) {
    this.frequency = supplyFrequency;
    this.storage = storage;
    this.logger = log;
    this.frame = window;
  }

  public void setFrequency(int freq) {
    this.frequency = freq;
  }

  @Override
  public void run() {
    try {
      Dealer.sleep((int)(Math.random() * 200.0));
    }
    catch (InterruptedException interruptedException) {
      // empty catch block
    }
    while (!Dealer.interrupted()) {
      try {
        StringBuilder sb = new StringBuilder();
        Car car = this.storage.get();
        sb.append("Time: ");
        sb.append(new Date());
        sb.append(" | Car: ");
        sb.append(car.getId());
        sb.append(" (Body: ");
        sb.append(car.getBody().getId());
        sb.append("; Engine: ");
        sb.append(car.getEngine().getId());
        sb.append("; Accessory1: ");
        sb.append(car.getAccessory1().getId());
        sb.append("; Accessory2: ");
        sb.append(car.getAccessory2().getId());
        sb.append("; Accessory3: ");
        sb.append(car.getAccessory3().getId());
        sb.append(")\n");
        this.logger.log(Level.INFO, sb.toString());
        System.out.println(sb);
        this.frame.setSoldCars();
        Dealer.sleep((int)(Math.random() * 200.0) - 100 + 1000 / this.frequency);
      }
      catch (InterruptedException | NullPointerException e) {
        this.interrupt();
      }
    }
  }
}