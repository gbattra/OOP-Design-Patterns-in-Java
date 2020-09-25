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

public class Driver {
  public static void main(String[] args) {
    System.out.println("Welcome to our bird conservatory, where we rescue and house birds.\n");

    System.out.println(String.format(
            "We house birds from the following classifications: %s\n",
            String.join(", ", Arrays.asList(
                    BirdClassification.BIRD_OF_PREY.label,
                    BirdClassification.FLIGHTLESS_BIRD.label,
                    BirdClassification.PIGEON.label,
                    BirdClassification.OWL.label,
                    BirdClassification.PARROT.label,
                    BirdClassification.WATERFOWL.label,
                    BirdClassification.SHOREBIRD.label))));

    System.out.println("Some birds hunt.\n");
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

    System.out.println("Some birds can talk.\n");
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

    System.out.println("Some birds live near water.\n");
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

    System.out.println("We house small groups of birds in aviaries, located in sectors.\n");
    System.out.println("Instantiating an aviary...\n");
    IAviary aviary = new Aviary(1);
    System.out.println(String.format(
            "The aviary we just instantiated resides in sector %s. \n",
            aviary.getSector()));

    System.out.println("You may add up to 5 birds to an aviary.\n");
    System.out.println("Adding a Talking Bird to the aviary...\n");
    aviary = aviary.addBird(talkingBird);
    aviary = aviary.addBird(shorebird);
    System.out.println("Description of the aviary:\n");
    System.out.println(String.format("%s\n", aviary.describe()));

    System.out.println("But careful not to mix any types!\n");
    System.out.println("Adding a Bird of Prey to the same aviary...\n");
    try {
      aviary.addBird(birdOfPrey);
    } catch (Exception e) {
      System.out.println("Failed!\n");
      System.out.println(e.getMessage() + "\n");
    }

    System.out.println("And of course, no extinct birds either.\n");
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

    System.out.println("We can see the different bird types in the aviary:\n");
    System.out.println(String.format(
            "The aviary houses birds of the follow types: %s.\n",
            aviary.getBirdTypes().stream().map(birdType -> birdType.label)
                                          .collect(Collectors.joining(", "))));

    System.out.println(
            "Aviaries can track how much food is needed to feed their birds on a daily basis.\n");
    Hashtable<BirdDiet, Integer> foodRequirements = aviary.getFoodRequirements();
    String foodRequirementsStr = "";
    for (Map.Entry<BirdDiet, Integer> entry : foodRequirements.entrySet()) {
      foodRequirementsStr += String.format("%s - %s, ", entry.getKey().label, entry.getValue());
    }
    System.out.println(String.format(
            "For example, the aviary we just created computes that we need the following food: %s\n",
            foodRequirementsStr));

    System.out.println("We organize these aviaries inside a conservatory.\n");
    System.out.println("Instantiating a conservatory...\n");
    IConservatory conservatory = new Conservatory().addAviary(aviary);
    System.out.println("We can add aviaries to our conservatory.\n");
    System.out.println("Adding aviaries to the conservatory...\n");
    conservatory = conservatory
                      .addAviary(new Aviary(2).addBird(waterBird))
                      .addAviary(new Aviary(3).addBird(birdOfPrey));
    System.out.println(
            "Conservatories can hold up to 20 aviaries. This can be hard to keep track of.\n");

    System.out.println("Conservatories can describe themselves briefly:\n");
    System.out.println(conservatory.describe() + "\n");

    System.out.println("We can query a conservatory to find the aviary for a specific bird.\n");
    System.out.println("Let's find our water bird, Lucy...\n");
    IAviary locatedAviary = conservatory.getAviaryWithBird(waterBird).get();
    System.out.println(String.format(
            "%s is located at the aviary in sector %s.\n",
            waterBird.getName(),
            locatedAviary.getSector()));

    System.out.println("We can get the aviary at certain sector.\n");
    System.out.println("Finding aviary at sector 1.\n");
    IAviary sectorAviary = conservatory.getAviaryAtSector(1).get();
    System.out.println(String.format(
            "The resulting aviary is located at %s\n",
            sectorAviary.getSector()));

    System.out.println(
            "A conservatory can calculate the daily food requirements for all its aviaries.\n");
    Hashtable<BirdDiet, Integer> totalFoodRequirements = conservatory.getFoodRequirements();
    String totalFoodRequirementsStr = "";
    for (Map.Entry<BirdDiet, Integer> entry : totalFoodRequirements.entrySet()) {
      totalFoodRequirementsStr += String.format("%s - %s, ", entry.getKey().label, entry.getValue());
    }
    System.out.println(String.format(
            "For the conservatory we just created, we need the following food: %s\n",
            totalFoodRequirementsStr));

    System.out.println(
            "A conservatory can provide a directory linking aviaries to their sectors.\n");
    System.out.println("Generating a directory for the conservatory...\n");
    IConservatoryDirectory directory = conservatory.getDirectory();

    System.out.println("The resulting directory contents:\n");
    System.out.println(directory.describe());

    System.out.println(
            "A conservatory can also provide an index linking birds to their sectors.\n");
    System.out.println("Generating a index for the conservatory...\n");
    IConservatoryIndex index = conservatory.getIndex();
    System.out.println("The resulting index contents:\n");
    System.out.println(index.describe() + "\n");

  }
}
