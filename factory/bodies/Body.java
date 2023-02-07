package factory.bodies;

public class Body {
  private static int ID = 1000001;
  private int id;

  public synchronized void initId() {
    this.id = ID++;
  }

  public int getId() {
    return this.id;
  }
}