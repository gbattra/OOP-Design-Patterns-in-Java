package birds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

import birds.enums.BirdClassification;
import birds.enums.BirdDiet;
import birds.enums.BirdType;
import birds.interfaces.IAviary;
import birds.interfaces.IBird;
import birds.interfaces.IConservatory;
import birds.interfaces.IConservatoryDirectory;
import birds.interfaces.IConservatoryIndex;
import birds.interfaces.ITalkingBird;
import birds.interfaces.IWaterBird;
import birds.models.Aviary;
import birds.models.Conservatory;
import birds.models.StandardBird;
import birds.models.TalkingBird;
import birds.models.WaterBird;

public class Demo {
  public static void main(String[] args) {
    System.out.println("Instantiating a Bird of Prey...\n");
    IBird birdOfPrey = new StandardBird(
            "Rex",
            BirdType.EAGLE,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.SMALL_MAMMALS,
                    BirdDiet.FISH,
                    BirdDiet.OTHER_BIRDS)),
            2);
    System.out.println("Description of this bird of prey:\n");
    System.out.println(birdOfPrey.describe() + "\n");

    System.out.println("Instantiating a Talking Bird...\n");
    ITalkingBird talkingBird = new TalkingBird(
            "Luke",
            BirdType.GRAY_PARROT,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.BERRIES,
                    BirdDiet.SEEDS,
                    BirdDiet.INSECTS)),
            2,
            "Flamingo",
            new ArrayList<>(Arrays.asList("Hello", "Goodbye", "Okay", "Love", "Sorry")));
    System.out.println(String.format(
            "This bird knows %s words and its favorite word is: %s.\n",
            talkingBird.getVocabulary().size(),
            talkingBird.getFavoriteWord()));

    System.out.println("Instantiating a WaterBird...\n");
    IWaterBird waterBird = new WaterBird(
            "Lucy",
            BirdType.GOOSE,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.BERRIES,
                    BirdDiet.SEEDS,
                    BirdDiet.INSECTS)),
            2,
            "Moosehead Lake");
    System.out.println(String.format(
            "The instantiated water bird lives near: %s\n",
            waterBird.getNearestWaterBody()));

    IWaterBird shorebird = new WaterBird(
            "Sam",
            BirdType.HORNED_PUFFIN,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.BERRIES,
                    BirdDiet.SEEDS,
                    BirdDiet.INSECTS)),
            2,
            "Bar Harbor");

    System.out.println(String.format(
            "Birds have a diet. For example, the water bird we just initialized eats: %s\n",
            waterBird.getDiet().stream().map(birdDiet -> birdDiet.label)
                                        .collect(Collectors.joining(", "))));

    System.out.println("Instantiating an aviary...\n");
    IAviary aviary = new Aviary(1);
    System.out.println(String.format(
            "The aviary we just instantiated resides in sector %s. \n",
            aviary.getSector()));

    System.out.println("Adding a Talking Bird to the aviary...\n");
    aviary = aviary.addBird(talkingBird);
    aviary = aviary.addBird(shorebird);
    System.out.println(String.format("%s\n", aviary.describe()));

    System.out.println("Adding a Bird of Prey to the same aviary...\n");
    try {
      aviary.addBird(birdOfPrey);
    } catch (Exception e) {
      System.out.println("Failed!\n");
      System.out.println(e.getMessage() + "\n");
    }

    System.out.println("Adding an extinct bird to the same aviary...\n");
    IBird extinctBird = new StandardBird(
            "Ancient",
            BirdType.MOA,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.INSECTS,
                    BirdDiet.LARVAE)),
            2);
    try {
      aviary.addBird(extinctBird);
    } catch (Exception e) {
      System.out.println("Failed!\n");
      System.out.println(e.getMessage() + "\n");
    }
    System.out.println(String.format(
            "The aviary houses birds of the follow types: %s.\n",
            aviary.getBirdTypes().stream().map(birdType -> birdType.label)
                                          .collect(Collectors.joining(", "))));

    Hashtable<BirdDiet, Integer> foodRequirements = aviary.getFoodRequirements();
    String foodRequirementsStr = "";
    for (Map.Entry<BirdDiet, Integer> entry : foodRequirements.entrySet()) {
      foodRequirementsStr += String.format("%s - %s, ", entry.getKey().label, entry.getValue());
    }
    System.out.println(String.format(
            "The aviary we just created computes that we need the following food: %s\n",
            foodRequirementsStr));

    System.out.println("Instantiating a conservatory...\n");
    IConservatory conservatory = new Conservatory().addAviary(aviary);
    System.out.println("Adding aviaries to the conservatory...\n");
    conservatory = conservatory
                      .addAviary(new Aviary(2).addBird(waterBird))
                      .addAviary(new Aviary(3).addBird(birdOfPrey));

    System.out.println(conservatory.describe() + "\n");

    System.out.println("Let's find our water bird, Lucy...\n");
    IAviary locatedAviary = conservatory.getAviaryWithBird(waterBird).get();
    System.out.println(String.format(
            "%s is located at the aviary in sector %s.\n",
            waterBird.getName(),
            locatedAviary.getSector()));

    System.out.println("Finding aviary at sector 1.\n");
    IAviary sectorAviary = conservatory.getAviaryAtSector(1).get();
    System.out.println(String.format(
            "The resulting aviary is located at %s\n",
            sectorAviary.getSector()));

    Hashtable<BirdDiet, Integer> totalFoodRequirements = conservatory.getFoodRequirements();
    String totalFoodRequirementsStr = "";
    for (Map.Entry<BirdDiet, Integer> entry : totalFoodRequirements.entrySet()) {
      totalFoodRequirementsStr += String.format("%s - %s, ", entry.getKey().label, entry.getValue());
    }
    System.out.println(String.format(
            "For the conservatory we just created, we need the following food: %s\n",
            totalFoodRequirementsStr));

    System.out.println("Generating a directory for the conservatory...\n");
    IConservatoryDirectory directory = conservatory.getDirectory();
    System.out.println(directory.describe());

    System.out.println("Generating a index for the conservatory...\n");
    IConservatoryIndex index = conservatory.getIndex();
    System.out.println(index.describe() + "\n");
  }
}
