package birds.models;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import birds.enums.BirdDiet;
import birds.interfaces.IAviary;
import birds.interfaces.IBird;
import birds.interfaces.IConservatory;
import birds.interfaces.IConservatoryDirectory;
import birds.interfaces.IConservatoryIndex;

public class Conservatory implements IConservatory {
  private static final int AVIARY_LIMIT = 20;

  private final List<IAviary> aviaries;

  public Conservatory() {
    this.aviaries = new ArrayList<>();
  }

  public Conservatory(
          List<IAviary> aviaries) throws IllegalArgumentException {
    this.aviaries = aviaries;

    if (aviaries.stream().anyMatch(aviary -> aviary.getSector() > AVIARY_LIMIT)) {
      throw new IllegalArgumentException(
              "Invalid aviary sector. Sector value greater than aviary limit.");
    }

    if (aviaries.size() !=
        aviaries.stream().map(IAviary::getSector).count()) {
      throw new IllegalArgumentException("Each aviary must reside in unique sector.");
    }

    if (this.aviaries.size() > Conservatory.AVIARY_LIMIT) {
      throw new IllegalArgumentException("Conservatory cannot have more than 20 aviaries.");
    }
  }

  public IConservatory addAviary(IAviary aviary) throws IllegalStateException {
    List<IAviary> aviaries = new ArrayList<>(this.aviaries);
    aviaries.add(aviary);
    try {
      return new Conservatory(aviaries);
    } catch (IllegalArgumentException e)
    {
      throw new IllegalStateException(e.getMessage());
    }
  }

  public List<IAviary> getAviaries() {
    return this.aviaries;
  }

  public IConservatoryDirectory getDirectory() throws IllegalArgumentException {
    Hashtable<Integer, IAviary> directory = new Hashtable<>();
    for (IAviary aviary : this.aviaries) {
      directory.put(aviary.getSector(), aviary);
    }

    return new ConservatoryDirectory(directory);
  }

  public IConservatoryIndex getIndex() throws IllegalArgumentException {
    Hashtable<IBird, Integer> index = new Hashtable<>();
    for (IAviary aviary : this.aviaries) {
      for (IBird bird : aviary.getBirds()) {
        index.put(bird, aviary.getSector());
      }
    }

    return new ConservatoryIndex(index);
  }

  public IAviary getAviaryWithBird(IBird bird) {
    Optional<IAviary> aviary = this.aviaries.stream()
            .filter(a -> a.getBirds().stream().anyMatch(b -> b.equals(bird)))
            .findFirst();

    return aviary.orElse(null);
  }

  public IAviary getAviaryAtSector(int sector) {
    Optional<IAviary> aviary = this.aviaries.stream()
            .filter(a -> a.getSector() == sector)
            .findFirst();

    return aviary.orElse(null);
  }

  public Hashtable<BirdDiet, Integer> getFoodRequirements() {
    Hashtable<BirdDiet, Integer> requirements = new Hashtable<>();

    for (IAviary aviary : this.aviaries) {
      Hashtable<BirdDiet, Integer> aviaryFoodRequirements = aviary.getFoodRequirements();
      for (Map.Entry<BirdDiet, Integer> entry : aviaryFoodRequirements.entrySet()) {
        if (requirements.containsKey(entry.getKey())) {
          requirements.replace(
                  entry.getKey(), requirements.get(entry.getKey()) + entry.getValue());
        } else {
          requirements.put(entry.getKey(), entry.getValue());
        }
      }
    }

    return requirements;
  }
}
