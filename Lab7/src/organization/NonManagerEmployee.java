package organization;

/**
 * This class represents an employee that does not have any managerial
 * responsibilities.
 */

public class NonManagerEmployee extends GenericEmployee {
  /**
   * Constructor.
   * 
   * @param name   the name for this employee
   * @param pay    the pay for this employee
   * @param gender the gender for this employee
   */
  public NonManagerEmployee(String name, double pay, Gender gender) {
    super(name, pay, gender);
  }

  @Override
  public Employee addSupervisee(String supervisorName, Employee supervisee) {
    if (this.name.equals(supervisorName)) {
      // must first "promote" this employee
      Supervisor newSelf = new Supervisor(this.name, this.pay, this.gender);
      newSelf.addSupervisee(supervisorName, supervisee);
      return newSelf;
    }
    return this;
  }
}
