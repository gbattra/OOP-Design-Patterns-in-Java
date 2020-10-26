package generictree;

import util.Gender;

/**
 * Interface for all employees.
 */
public interface Employee {
  /**
   * Get the name of this employee.
   * 
   * @return the name of this employee as a string
   */
  String getName();

  /**
   * Get the gender of this employee as per the organization records.
   * 
   * @return the gender of this employee as an enum {@link Gender}
   */
  Gender getGender();

  /**
   * Get the annual pay of this employee as per the organization records.
   * 
   * @return the annual pay of this employee
   */
  double getAnnualPay();

  /**
   * Returns the date of the end of employment of this employee.
   * 
   * @return the date in MMDDYYYY format
   */
  String getEmploymentEndDate();
}
