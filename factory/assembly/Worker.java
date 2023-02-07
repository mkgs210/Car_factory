package factory.assembly;

import factory.accessories.AccessoryStorage;
import factory.assembly.Car;
import factory.bodies.BodyStorage;
import factory.engines.EngineStorage;
import factory.sellings.CarStorage;

public class Worker
        extends Thread {
  private int frequency;
  private final CarStorage storage;
  private final BodyStorage bodyStorage;
  private final EngineStorage engineStorage;
  private final AccessoryStorage accessoryStorage;

  public Worker(CarStorage storage, int frequency, BodyStorage bodyStorage, EngineStorage engineStorage, AccessoryStorage accessoryStorage) {
    this.frequency = frequency;
    this.storage = storage;
    this.bodyStorage = bodyStorage;
    this.engineStorage = engineStorage;
    this.accessoryStorage = accessoryStorage;
  }

  public void setFrequency(int freq) {
    this.frequency = freq;
  }

  @Override
  public void run() {
    try {
      Worker.sleep((int)(Math.random() * 200.0));
    }
    catch (InterruptedException interruptedException) {
      // empty catch block
    }
    while (!Worker.interrupted()) {
      Car car = new Car();
      car.initId();
      car.setBody(this.bodyStorage.get());
      car.setEngine(this.engineStorage.get());
      car.setAccessory1(this.accessoryStorage.get());
      car.setAccessory2(this.accessoryStorage.get());
      car.setAccessory3(this.accessoryStorage.get());
      this.storage.add(car);
      try {
        Worker.sleep((int)(Math.random() * 200.0) - 100 + 1000 / this.frequency);
      }
      catch (InterruptedException e) {
        this.interrupt();
      }
    }
  }
}