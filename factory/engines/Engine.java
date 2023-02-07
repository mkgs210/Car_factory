package factory.engines;

public class Engine {
  private static int ID = 2000001;
  private int id;

  public synchronized void initId() {
    this.id = ID++;
  }

  public int getId() {
    return this.id;
  }
}