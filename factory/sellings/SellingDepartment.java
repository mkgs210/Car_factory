package factory.sellings;

import factory.FactoryWindow;
import java.util.ArrayList;
import java.util.logging.Logger;

public class SellingDepartment {
  private ArrayList<Dealer> dep = new ArrayList();

  public SellingDepartment(int dealers, int frequency, CarStorage storage, Logger log, FactoryWindow window) {
    for (int i = 0; i < dealers; ++i) {
      this.dep.add(new Dealer(frequency, storage, log, window));
    }
  }

  public void start() {
    for (Dealer dealer : this.dep) {
      dealer.start();
    }
  }

  public void stop() {
    for (Dealer dealer : this.dep) {
      dealer.interrupt();
    }
  }

  public void setFrequency(int freq) {
    for (int i = 0; i < this.dep.size(); ++i) {
      this.dep.get(i).setFrequency(freq);
    }
  }
}