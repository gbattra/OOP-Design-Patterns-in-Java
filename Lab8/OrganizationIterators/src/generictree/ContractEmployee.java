package generictree;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import util.Gender;

/**
 * Class that represents a contract employee.
 */
public class ContractEmployee extends InternalEmployee {
  private LocalDate contractEndDate;

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
