package generictree;

/**
 * This interface represents a single employee in the organization. Every
 * employee is guaranteed to have a name, gender and pay on record
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
