package birds.interfaces;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import birds.enums.BirdDiet;
import birds.enums.BirdType;

public interface IAviary {
  List<IBird> getBirds();
  List<BirdType> getBirdTypes();
  IAviary addBird(IBird bird);
  int getSector();
  String describe();
  List<IFoodRequirement<BirdDiet>> getFoodRequirements();
}
