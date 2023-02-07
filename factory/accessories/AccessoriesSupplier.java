package factory.accessories;

import factory.accessories.Accessory;
import factory.accessories.AccessoryStorage;

public class AccessoriesSupplier
        extends Thread {
  private int frequency;
  private final AccessoryStorage storage;

  public AccessoriesSupplier(int supplyFrequency, AccessoryStorage storage) {
    this.frequency = supplyFrequency;
    this.storage = storage;
  }

  public void setFrequency(int freq) {
    this.frequency = freq;
  }

  @Override
  public void run() {
    try {
      AccessoriesSupplier.sleep((int)(Math.random() * 200.0));
    }
    catch (InterruptedException interruptedException) {
      // empty catch block
    }
    while (!AccessoriesSupplier.interrupted()) {
      Accessory accessory = new Accessory();
      accessory.initId();
      this.storage.add(accessory);
      try {
        AccessoriesSupplier.sleep((int)(Math.random() * 200.0) - 100 + 1000 / this.frequency);
      }
      catch (InterruptedException e) {
        this.interrupt();
      }
    }
  }
}