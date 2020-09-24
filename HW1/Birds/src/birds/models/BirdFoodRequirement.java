package birds.models;

import birds.enums.BirdDiet;
import birds.interfaces.IFoodRequirement;

public class BirdFoodRequirement implements IFoodRequirement<BirdDiet> {
  private final BirdDiet food;
  private final int count;

  public BirdFoodRequirement(
          BirdDiet food,
          int count) {
    this.food = food;
    this.count = count;
  }
  public int getCount() {
    return this.count;
  }

  public BirdDiet getFood() {
    return this.food;
  }
}
