package factory.assembly;

import factory.accessories.Accessory;
import factory.bodies.Body;
import factory.engines.Engine;

public class Car {
  private Engine engine;
  private Body body;
  private Accessory accessory1;
  private Accessory accessory2;
  private Accessory accessory3;
  private static int ID = 5000001;
  private int id;

  public synchronized void initId() {
    this.id = ID++;
  }

  public int getId() {
    return this.id;
  }

  public void setEngine(Engine detail) {
    this.engine = detail;
  }

  public void setBody(Body detail) {
    this.body = detail;
  }

  public void setAccessory1(Accessory detail) {
    this.accessory1 = detail;
  }

  public void setAccessory2(Accessory detail) {
    this.accessory2 = detail;
  }

  public void setAccessory3(Accessory detail) {
    this.accessory3 = detail;
  }

  public Engine getEngine() {
    return this.engine;
  }

  public Body getBody() {
    return this.body;
  }

  public Accessory getAccessory1() {
    return this.accessory1;
  }

  public Accessory getAccessory2() {
    return this.accessory2;
  }

  public Accessory getAccessory3() {
    return this.accessory3;
  }
}