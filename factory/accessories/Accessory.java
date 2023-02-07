package factory.accessories;

public class Accessory {
  private static int ID = 3000001;
  private int id;

  public synchronized void initId() {
    this.id = ID++;
  }

  public int getId() {
    return this.id;
  }
}