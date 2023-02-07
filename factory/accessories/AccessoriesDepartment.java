package factory.accessories;

import java.util.ArrayList;

public class AccessoriesDepartment {
  private ArrayList<AccessoriesSupplier> dep = new ArrayList();

  public AccessoriesDepartment(int suppliers, int supplyFrequency, AccessoryStorage storage) {
    for (int i = 0; i < suppliers; ++i) {
      this.dep.add(new AccessoriesSupplier(supplyFrequency, storage));
    }
  }

  public void start() {
    for (AccessoriesSupplier accessoriesSupplier : this.dep) {
      accessoriesSupplier.start();
    }
  }

  public void cont() {
    for (AccessoriesSupplier accessoriesSupplier : this.dep.subList(this.dep.size()-1, this.dep.size())) {
      accessoriesSupplier.start();
    }
  }

  public void stop() {
    for (AccessoriesSupplier accessoriesSupplier : this.dep) {
      accessoriesSupplier.interrupt();
    }
  }

  public void setFrequency(int freq) {
    for (int i = 0; i < this.dep.size(); ++i) {
      this.dep.get(i).setFrequency(freq);
    }
  }
}