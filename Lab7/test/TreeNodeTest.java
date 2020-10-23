import org.junit.Before;
import org.junit.Test;

import java.util.List;

import generictree.Gender;
import generictree.Organization;
import generictree.OrganizationImpl;

import static org.junit.Assert.*;

/**
 * Test cases for the tree.
 */
public class TreeNodeTest {
  private Organization ccis;
  private Organization startup;

  @Before
  public void setup() {

    ccis = new OrganizationImpl("Carla Brodley", 400000, Gender.Female);

    ccis.addEmployee("Jay Aslam", 200000, Gender.Male, "Carla Brodley");

    ccis.addEmployee("Rajmohan Rajaraman", 200000, Gender.Male, "Jay Aslam");

    ccis.addEmployee("Frank Tip", 200000, Gender.Male, "Jay Aslam");

    ccis.addEmployee("Alan Mislove", 100000, Gender.Male, "Jay Aslam");

    ccis.addEmployee("Martin Schedlbauer", 100000, Gender.Male, "Alan Mislove");

    ccis.addEmployee("Amit Shesh", 100000, Gender.Male, "Frank Tip");

    ccis.addEmployee("Leena Razzaq", 120000, Gender.Female, "Frank Tip");

    ccis.addEmployee("Bryan Lackaye", 150000, Gender.Male, "Carla Brodley");

    ccis.addEmployee("Michelle Omerod", 100000, Gender.Female, "Bryan Lackaye");

    ccis.addEmployee("Sarah Gale", 100000, Gender.Female, "Bryan Lackaye");

    ccis.addEmployee("Catherine Gill", 200000, Gender.Female, "Carla Brodley");

    ccis.addEmployee("Megan Barry", 100000, Gender.Female, "Catherine Gill");

    startup = new OrganizationImpl("Bob", 50000, Gender.UnDisclosed);

    startup.addEmployee("Bill", 20000, Gender.Male, "Bob");
    startup.addEmployee("Michelle", 30000, Gender.Female, "Bob");
    startup.addContractEmployee("Mark", 10000, Gender.Male, 1, 9, 2018, "Bill");
    startup.addEmployee("Amit", 10000, Gender.Male, "Bill");
    startup.addContractEmployee("Chuck", 10000, Gender.UnDisclosed, 1, 12, 2018,
        "Michelle");

    startup.addContractEmployee("Tom", 10000, Gender.Male, 15, 10, 2018,
        "Bill");

    startup.addContractEmployee("Tim", 5000, Gender.Male, 15, 9, 2018,
        "Michelle");

  }

  @Test
  public void testGetSize() {
    assertEquals(13, ccis.getSize());
    assertEquals(6, ccis.getSizeByGender(Gender.Female));
    assertEquals(7, ccis.getSizeByGender(Gender.Male));

    assertEquals(8, startup.getSize());
    assertEquals(1, startup.getSizeByGender(Gender.Female));
    assertEquals(5, startup.getSizeByGender(Gender.Male));
    assertEquals(2, startup.getSizeByGender(Gender.UnDisclosed));
  }

  @Test
  public void testAllEmployees() {
    List<String> actualResult = ccis.allEmployees();
    String expected = "[Carla Brodley, Jay Aslam, Rajmohan Rajaraman, Frank "
        + "Tip, Amit Shesh, Leena Razzaq, Alan Mislove, Martin "
        + "Schedlbauer, Bryan Lackaye, Michelle Omerod, Sarah "
        + "Gale, Catherine Gill, Megan Barry]";
    assertEquals(expected, actualResult.toString());

    actualResult = startup.allEmployees();
    expected = "[Bob, Bill, Mark, Amit, Tom, Michelle, Chuck, Tim]";
    assertEquals(expected, actualResult.toString());
  }

  @Test
  public void testEmployeePay() {
    assertEquals(5, ccis.countPayAbove(150000));
    assertEquals(1, ccis.countPayAbove(300000));
  }

  @Test
  public void testTerminatedBy() {
    for (int i = 0; i < 1000; i++) {
      assertEquals(0, ccis.terminatedBefore((int) (Math.random() * 30) + 1,
          (int) (Math.random() * 11) + 1, (int) (Math.random() * 10 + 2000)));
    }

    assertEquals(1, startup.terminatedBefore(2, 9, 2018));
    assertEquals(2, startup.terminatedBefore(16, 9, 2018));
    assertEquals(3, startup.terminatedBefore(31, 10, 2018));
    assertEquals(4, startup.terminatedBefore(31, 12, 2018));
  }
}