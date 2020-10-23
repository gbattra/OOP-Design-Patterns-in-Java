package organization;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents an employee who works on a term-limited contract with
 * the organization. Contract employees cannot manage other employees within
 * this organization.
 */
public class ContractEmployee extends NonManagerEmployee {
  private LocalDate contractEndDate;

  /**
   * Constructor.
   * 
   * @param name   the name for this employee
   * @param pay    the pay for this employee
   * @param gender the gender for this employee
   * @param date   the day of the start of employment for this employee
   * @param month  the month of the start of employment for this employee
   * @param year   the year of the start of employment for this employee
   * @throws IllegalArgumentException if the year is invalid
   */
  public ContractEmployee(String name, double pay, Gender gender, int date,
      int month, int year) throws IllegalArgumentException {
    super(name, pay, gender);
    // validate our date
    try {
      contractEndDate = LocalDate.of(year, month, date);
    } catch (DateTimeException e) {
      throw new IllegalArgumentException("Invalid contract end date");
    }
  }

  @Override
  public String getEmploymentEndDate() {
    return DateTimeFormatter.ofPattern("MMddyyyy").format(contractEndDate);
  }
}
