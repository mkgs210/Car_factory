package factory.accessories;

import factory.FactoryWindow;
import java.util.LinkedList;
import java.util.Queue;

public class AccessoryStorage {
  private final int maxSize;
  private final Queue<Accessory> storage;
  private FactoryWindow frame;

  public AccessoryStorage(int maxSize, FactoryWindow window) {
    this.maxSize = maxSize;
    this.storage = new LinkedList<Accessory>();
    this.frame = window;
  }

  public synchronized void add(Accessory el) {
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
    this.frame.setAccessories(this.storage.size());
  }

  public synchronized Accessory get() {
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
    this.frame.setAccessories(this.storage.size() - 1);
    return this.storage.poll();
  }
}