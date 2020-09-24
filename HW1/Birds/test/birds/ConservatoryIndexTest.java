package birds;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import birds.enums.BirdDiet;
import birds.enums.BirdType;
import birds.interfaces.IBird;
import birds.interfaces.IConservatoryIndex;
import birds.models.Bird;
import birds.models.ConservatoryIndex;

import static org.junit.Assert.assertEquals;

public class ConservatoryIndexTest {
  private IConservatoryIndex conservatoryIndex;

  @Before
  public void setup() {
    Hashtable<IBird, Integer> index = new Hashtable<>();
    Bird bird = new Bird(
            "Rex",
            BirdType.EAGLE,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.SMALL_MAMMALS,
                    BirdDiet.FISH,
                    BirdDiet.OTHER_BIRDS)),
            2);
    index.put(bird, 1);
    this.conservatoryIndex = new ConservatoryIndex(index);
  }

  @Test
  public void testAccessors() {
    Hashtable<IBird, Integer> index = new Hashtable<>();
    Bird bird = new Bird(
            "Rex",
            BirdType.EAGLE,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.SMALL_MAMMALS,
                    BirdDiet.FISH,
                    BirdDiet.OTHER_BIRDS)),
            2);
    index.put(bird, 1);
    assertEquals(index.get(bird), this.conservatoryIndex.getIndex().get(bird));
  }

  @Test
  public void testDescribe() {
    String expectedDescription =
            "The following birds are housed in this conservatory:\n" +
            "- Rex the Eagle lives in the aviary at sector 1\n";
    assertEquals(expectedDescription, this.conservatoryIndex.describe());
  }
}
