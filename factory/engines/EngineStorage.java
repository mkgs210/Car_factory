package factory.engines;

import factory.FactoryWindow;
import java.util.LinkedList;
import java.util.Queue;

public class EngineStorage {
  private final int maxSize;
  private final Queue<Engine> storage;
  private FactoryWindow frame;

  public EngineStorage(int maxSize, FactoryWindow window) {
    this.maxSize = maxSize;
    this.storage = new LinkedList<Engine>();
    this.frame = window;
  }

  public synchronized void add(Engine el) {
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
    this.frame.setEngines(this.storage.size());
  }

  public synchronized Engine get() {
    try {
      if (this.storage.isEmpty()) {
        this.wait();
      } else if (this.storage.size() > 1) {
        this.notify();
      }
    }
    catch (Exception exception) {
      // empty catch block
    }
    this.frame.setEngines(this.storage.size() - 1);
    return this.storage.poll();
  }

  public int getSize() {
    return this.storage.size();
  }

  public int getMaxSize() {
    return this.maxSize;
  }
}