package factory.bodies;

import factory.bodies.Body;
import factory.bodies.BodyStorage;

public class BodySupplier
        extends Thread {
  private int frequency;
  private final BodyStorage storage;

  public BodySupplier(int supplyFrequency, BodyStorage storage) {
    this.frequency = supplyFrequency;
    this.storage = storage;
  }

  public void setFrequency(int freq) {
    this.frequency = freq;
  }

  @Override
  public void run() {
    try {
      BodySupplier.sleep((int)(Math.random() * 200.0));
    }
    catch (InterruptedException interruptedException) {
      // empty catch block
    }
    while (!BodySupplier.interrupted()) {
      Body body = new Body();
      body.initId();
      this.storage.add(body);
      try {
        BodySupplier.sleep((int)(Math.random() * 200.0) - 100 + 1000 / this.frequency);
      }
      catch (InterruptedException e) {
        this.interrupt();
      }
    }
  }
}