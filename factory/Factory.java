package factory;

import factory.accessories.AccessoriesDepartment;
import factory.accessories.AccessoryStorage;
import factory.assembly.AssemblyDepartment;
import factory.bodies.BodyStorage;
import factory.bodies.BodySupplier;
import factory.engines.EngineStorage;
import factory.engines.EngineSupplier;
import factory.sellings.CarStorage;
import factory.sellings.SellingDepartment;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Factory {
  private AssemblyDepartment assemblyDepartment;
  private BodySupplier bodySupplier;
  private BodyStorage bodyStorage;
  private EngineSupplier engineSupplier;
  private EngineStorage engineStorage;
  private AccessoriesDepartment accessoriesDepartment;
  private AccessoryStorage accessoryStorage;
  private SellingDepartment sellingDepartment;
  private CarStorage carStorage;
  private int bodyStorageSize;
  private int engineStorageSize;
  private int accessoryStorageSize;
  private int carStorageSize;
  private int accessorySuppliersAmount;
  private int workersAmount;
  private int dealersAmount;
  private boolean loggingEnabled;
  private FactoryWindow window;
  private int bodySupplyFrequency = 5;
  private int engineSupplyFrequency = 5;
  private int accessorySupplyFrequency = 5;
  private int carCreateFrequency = 1;
  private int carRequestFrequency = 1;
  private static final Logger logger = Logger.getLogger(Factory.class.getName());

  public Factory(FactoryWindow frame) {
    this.window = frame;
    this.init();
  }

  private void init() {
    Properties props = new Properties();
    try {
      props.load(new FileReader("C:\\Java\\factor\\factory\\config\\factory.properties"));
    }
    catch (IOException e) {
      System.out.println("factory.Factory properties file not found! The default values will be used.");
    }
    this.bodyStorageSize = Integer.parseInt(props.getProperty("bodyStorageSize", "50"));
    this.engineStorageSize = Integer.parseInt(props.getProperty("engineStorageSize", "50"));
    this.accessoryStorageSize = Integer.parseInt(props.getProperty("accessoryStorageSize", "50"));
    this.carStorageSize = Integer.parseInt(props.getProperty("carStorageSize", "50"));
    this.accessorySuppliersAmount = Integer.parseInt(props.getProperty("accessorySuppliersAmount", "3"));
    this.workersAmount = Integer.parseInt(props.getProperty("workersAmount", "5"));
    this.dealersAmount = Integer.parseInt(props.getProperty("dealersAmount", "5"));
    this.loggingEnabled = Boolean.parseBoolean(props.getProperty("loggingEnabled", "true"));
    try {
      if (this.loggingEnabled) {
        LogManager.getLogManager().readConfiguration(Factory.class.getResourceAsStream("config/logging_true.properties"));
      } else {
        LogManager.getLogManager().readConfiguration(Factory.class.getResourceAsStream("config/logging_false.properties"));
      }
    }
    catch (IOException e) {
      System.out.println("Logging properties file not found!");
    }
    this.bodyStorage = new BodyStorage(this.bodyStorageSize, this.window);
    this.engineStorage = new EngineStorage(this.engineStorageSize, this.window);
    this.accessoryStorage = new AccessoryStorage(this.accessoryStorageSize, this.window);
    this.bodySupplier = new BodySupplier(this.bodySupplyFrequency, this.bodyStorage);
    this.engineSupplier = new EngineSupplier(this.engineSupplyFrequency, this.engineStorage);
    this.carStorage = new CarStorage(this.carStorageSize, this.window);
    this.accessoriesDepartment = new AccessoriesDepartment(this.accessorySuppliersAmount, this.accessorySupplyFrequency, this.accessoryStorage);
    this.assemblyDepartment = new AssemblyDepartment(this.workersAmount, this.carStorage, this.carCreateFrequency, this.bodyStorage, this.engineStorage, this.accessoryStorage);
    this.sellingDepartment = new SellingDepartment(this.dealersAmount, this.carRequestFrequency, this.carStorage, logger, this.window);
  }

  public void start() {
    this.accessoriesDepartment.start();
    this.bodySupplier.start();
    this.engineSupplier.start();
    try {
      Thread.sleep(1000L);
    }
    catch (InterruptedException interruptedException) {
      // empty catch block
    }
    this.assemblyDepartment.start();
    this.sellingDepartment.start();
  }

  public void stop() {
    this.accessoriesDepartment.stop();
    this.bodySupplier.interrupt();
    this.engineSupplier.interrupt();
    this.sellingDepartment.stop();
    this.assemblyDepartment.stop();
  }

  public void setValues(int first, int second, int third, int fourth, int fifth) {
    this.bodySupplyFrequency = first;
    this.bodySupplier.setFrequency(first);
    this.engineSupplyFrequency = second;
    this.engineSupplier.setFrequency(second);
    this.accessorySupplyFrequency = third;
    this.accessoriesDepartment.setFrequency(third);
    this.carCreateFrequency = fourth;
    this.assemblyDepartment.setFrequency(fourth);
    this.carRequestFrequency = fifth;
    this.sellingDepartment.setFrequency(fifth);
  }
}