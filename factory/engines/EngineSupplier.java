package factory.engines;

public class EngineSupplier
        extends Thread {
  private int frequency;
  private final EngineStorage storage;

  public EngineSupplier(int supplyFrequency, EngineStorage storage) {
    this.frequency = supplyFrequency;
    this.storage = storage;
  }

  public void setFrequency(int freq) {
    this.frequency = freq;
  }

  @Override
  public void run() {
    try {
      EngineSupplier.sleep((int)(Math.random() * 200.0));
    }
    catch (InterruptedException interruptedException) {
      // empty catch block
    }
    while (!EngineSupplier.interrupted()) {
      Engine engine = new Engine();
      engine.initId();
      this.storage.add(engine);
      try {
        EngineSupplier.sleep((int)(Math.random() * 200.0) - 100 + 1000 / this.frequency);
      }
      catch (InterruptedException e) {
        this.interrupt();
      }
    }
  }
}