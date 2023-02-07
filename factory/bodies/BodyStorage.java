package factory.bodies;

import factory.FactoryWindow;
import factory.bodies.Body;
import java.util.LinkedList;
import java.util.Queue;

public class BodyStorage {
  private final int maxSize;
  private final Queue<Body> storage;
  private FactoryWindow frame;

  public BodyStorage(int maxSize, FactoryWindow window) {
    this.maxSize = maxSize;
    this.storage = new LinkedList<Body>();
    this.frame = window;
  }

  public synchronized void add(Body el) {
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
    this.frame.setBodies(this.storage.size());
  }

  public synchronized Body get() {
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
    this.frame.setBodies(this.storage.size() - 1);
    return this.storage.poll();
  }

  public int getSize() {
    return this.storage.size();
  }

  public int getMaxSize() {
    return this.maxSize;
  }
}