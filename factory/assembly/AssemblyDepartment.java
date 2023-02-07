package factory.assembly;

import factory.accessories.AccessoryStorage;
import factory.bodies.BodyStorage;
import factory.engines.EngineStorage;
import factory.sellings.CarStorage;
import java.util.ArrayList;

public class AssemblyDepartment {
  private ArrayList<Worker> dep = new ArrayList();

  public AssemblyDepartment(int workers, CarStorage storage, int createFrequency, BodyStorage bodyStorage, EngineStorage engineStorage, AccessoryStorage accessoryStorage) {
    for (int i = 0; i < workers; ++i) {
      this.dep.add(new Worker(storage, createFrequency, bodyStorage, engineStorage, accessoryStorage));
    }
  }

  public void start() {
    for (Worker worker : this.dep) {
      worker.start();
    }
  }

  public void stop() {
    for (Worker worker : this.dep) {
      worker.interrupt();
    }
  }

  public void setFrequency(int freq) {
    for (int i = 0; i < this.dep.size(); ++i) {
      this.dep.get(i).setFrequency(freq);
    }
  }
}