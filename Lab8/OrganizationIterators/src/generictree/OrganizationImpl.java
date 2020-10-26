package generictree;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import util.Gender;

/**
 * This class represents an organization with employees.
 *
 * This usage also shows the redundancy of the count method in the tree. The
 * count operation can be framed as a map and a reduce.
 */

public class OrganizationImpl implements IterableOrganization {
  private TreeNode<Employee> root;

  public OrganizationImpl(String nameCEO, double pay, Gender gender) {
    root = new LeafNode<>(new InternalEmployee(nameCEO, pay, gender));
  }

  @Override
  public void addEmployee(String name, double pay, Gender gender,
      String supervisorName) {
    Employee newEmployee = new InternalEmployee(name, pay, gender);
    TreeNode<Employee> newNode = new LeafNode<>(newEmployee);
    root = root.addChild(e -> e.getName().equals(supervisorName), newNode);
  }

  @Override
  public void addContractEmployee(String name, double pay, Gender gender,
      int endDate, int endMonth, int endYear, String supervisorName) {
    Employee newEmployee = new ContractEmployee(name, pay, gender, endDate,
        endMonth, endYear);
    TreeNode<Employee> newNode = new LeafNode<>(newEmployee);
    root = root.addChild(e -> e.getName().equals(supervisorName), newNode);
  }

  @Override
  public int getSize() {
    return root.count(b -> true);
//    return root.map(e -> new Integer(1)).reduce(0, (a, b) -> a + b);
  }

  @Override
  public int getSizeByGender(Gender gender) {
//    return root.count(b -> b.getGender() == gender);
    return root.map(e -> {
      if (e.getGender() == gender) {
        return new Integer(1);
      } else {
        return new Integer(0);
      }
    }).reduce(0, (a, b) -> a + b);
  }

  @Override
  public List<String> allEmployees() {
    return root.map(e -> e.getName()).toList();
  }

  @Override
  public int countPayAbove(double amount) {
    return root.map(e -> {
      if (e.getAnnualPay() > amount) {
        return new Integer(1);
      } else {
        return new Integer(0);
      }
    }).reduce(0, (a, b) -> a + b);
  }

  @Override
  public int terminatedBefore(int date, int month, int year) {
    LocalDate threshold;

    try {
      threshold = LocalDate.of(year, month, date);
    } catch (DateTimeException e) {
      return 0;
    }

    return root.map(e -> {
      if (e.getEmploymentEndDate().equals("XXXXXXXX")) {
        return new Integer(0);
      } else {
        LocalDate d = LocalDate.parse(e.getEmploymentEndDate(),
            DateTimeFormatter.ofPattern("MMddyyyy"));
        if (d.compareTo(threshold) < 0) {
          return new Integer(1);
        } else {
          return new Integer(0);
        }
      }
    }).reduce(0, (a, b) -> a + b);

//    return root.count(b -> {
//      if (b.getEmploymentEndDate().equals("XXXXXXXX")) {
//        return false;
//      } else {
//        LocalDate d = LocalDate.parse(b.getEmploymentEndDate(),
//            DateTimeFormatter.ofPattern("MMddyyyy"));
//        return d.compareTo(threshold) < 0;
//      }
//    });
  }

  @Override
  public Iterator<Employee> iterator() {
    return new OrganizationIterator(root);
  }

  /**
   * An iterator for this organization.
   */
  private static class OrganizationIterator implements Iterator<Employee> {
    private Stack<TreeNode<Employee>> stack;

    /**
     * Constructor.
     * 
     * @param root the starting point for this iterator.
     */
    private OrganizationIterator(TreeNode<Employee> root) {
      stack = new Stack<>();
      stack.push(root);
    }

    @Override
    public boolean hasNext() {
      return !stack.isEmpty();
    }

    @Override
    public Employee next() {
      if (!stack.isEmpty()) {
        TreeNode<Employee> curr = stack.pop();

        Employee e = curr.getData();
        List<TreeNode<Employee>> children = curr.children();
        // as we want to visit the children in order we insert them in
        // reverse order in the stack
        Collections.reverse(children);
        for (TreeNode<Employee> c : children) {
          stack.push(c);
        }
        return e;
      }
      return null;
    }
  }
}
