package factory.sellings;

import factory.FactoryWindow;
import factory.assembly.Car;
import java.util.LinkedList;
import java.util.Queue;

public class CarStorage {
  private final int maxSize;
  private final Queue<Car> storage;
  private FactoryWindow frame;

  public CarStorage(int maxSize, FactoryWindow window) {
    this.maxSize = maxSize;
    this.storage = new LinkedList<Car>();
    this.frame = window;
  }

  public synchronized void add(Car el) {
    if (this.maxSize - this.storage.size() < 3) {
      try {
        this.wait();
      }
      catch (InterruptedException e) {
        this.notifyAll();
      }
    } else {
      try {
        this.notify();
      }
      catch (IllegalMonitorStateException e) {
        this.notifyAll();
      }
    }
    this.storage.add(el);
    this.frame.setCars(this.storage.size());
  }

  public synchronized Car get() {
    try {
      if (this.storage.isEmpty()) {
        this.wait();
      } else if (this.storage.size() > 1) {
        this.notify();
      }
    }
    catch (Exception e) {
      this.notifyAll();
    }
    this.frame.setCars(this.storage.size() - 1);
    return this.storage.poll();
  }
}