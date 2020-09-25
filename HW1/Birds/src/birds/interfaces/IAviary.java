package birds.interfaces;

import java.util.Hashtable;
import java.util.List;

import birds.enums.BirdDiet;
import birds.enums.BirdType;

/**
 * Interface for an Aviary object. Aviaries house birds and track food requirements.
 */
public interface IAviary extends IDescribable {
  /**
   * Accessor for the birds in this aviary.
   *
   * @return List<IBird> the list of birds in the aviary
   */
  List<IBird> getBirds();

  /**
   * Accessor for the types of birds in the aviary.
   *
   * @return List<BirdType> the list of bird types in the aviary
   */
  List<BirdType> getBirdTypes();

  /**
   * Adds a bird to the aviary.
   *
   * @param bird IBird to add to the aviary
   * @return IAviary new Aviary instance with updated birds list
   * @throws IllegalArgumentException when any constructor constraint is violated
   */
  IAviary addBird(IBird bird) throws IllegalStateException ;

  /**
   * Accessor for the aviary's sector id.
   *
   * @return int the sector id
   */
  int getSector();

  /**
   * Returns a list of food requirements based on the birds housed in the aviary.
   *
   * @return Hashtable<BirdDiet, Integer> the list of food requirements
   */
  Hashtable<BirdDiet, Integer> getFoodRequirements();
}
