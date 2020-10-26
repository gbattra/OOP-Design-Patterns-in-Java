package generictree;

import util.Gender;

/**
 * Representation of internal employees.
 */
public class InternalEmployee implements Employee {
  protected String name;
  protected double pay;
  protected Gender gender;

  public InternalEmployee(String name, double pay, Gender gender) {
    this.name = name;
    this.pay = pay;
    this.gender = gender;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Gender getGender() {
    return this.gender;
  }

  @Override
  public double getAnnualPay() {
    return this.pay;
  }

  /**
   * By default, there is no end date for an employee. Only contract employees
   * have an actual end date.
   * 
   * @return
   */
  @Override
  public String getEmploymentEndDate() {
    return "XXXXXXXX";
  }

}
