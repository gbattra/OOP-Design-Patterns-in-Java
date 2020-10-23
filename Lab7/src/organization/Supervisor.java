package organization;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * This class represents an employee in a supervisory role. This means that this
 * employee supervises at least one other employee
 */
public class Supervisor extends GenericEmployee {

  private List<Employee> supervisee;

  /**
   * Constructor.
   * 
   * @param name   the name of this supervisor
   * @param pay    the pay for this supervisor
   * @param gender the gender of the supervisor
   */
  public Supervisor(String name, double pay, Gender gender) {
    super(name, pay, gender);
    supervisee = new LinkedList<Employee>();
  }

  @Override
  public Employee addSupervisee(String supervisorName, Employee supervisee) {
    if (this.name.equals(supervisorName)) {
      this.supervisee.add(supervisee);
      return this;
    }
    for (int i = 0; i < this.supervisee.size(); i++) {
      this.supervisee.set(i,
          this.supervisee.get(i).addSupervisee(supervisorName, supervisee));
    }
    return this;
  }

  @Override
  public int count(Predicate<Employee> condition) {
    Stream<Employee> stream = this.supervisee.stream();
    return stream.mapToInt(b -> b.count(condition)).sum()
        + super.count(condition);
  }

  @Override
  public List<Employee> toList() {
    List<Employee> result = new ArrayList<Employee>();
    result.add(this);
    for (Employee e : supervisee) {
      result.addAll(e.toList());
    }
    return result;
  }
}
