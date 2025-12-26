package common;

import java.time.LocalDate;
import common.DataStore;
import employee.Employee;
import manager.Manager;

public class RandomSeed {

  public static void genRandom(){
    if (DataStore.seeded) {
      return;
    }
    DataStore.seeded = true;
    int empCount = DataStore.empCount;
    DataStore.employees[empCount] = new Employee(2867297,"Raja","AKSHay1",LocalDate.of(
      2003, 3, 3));
      empCount++;
    DataStore.employees[empCount] = new Employee(2867897,"Rxja","AKdHay1",LocalDate.of(
      2003, 5, 3));
      empCount++;
    DataStore.empCount = empCount;

    int mgCount = DataStore.managerCount;

    DataStore.managers[mgCount] = new Manager(2289, "Nikil", "Dymmy");
    mgCount++;
    DataStore.managers[mgCount] = new Manager(2280, "Nikil", "Dymmy");
    DataStore.managerCount = mgCount;

  }
  
}
