package birds.interfaces;

import java.util.List;

import birds.enums.BirdClassification;
import birds.enums.BirdDiet;
import birds.enums.BirdType;

/**
 * Interface for a bird object. Birds have names, a type which is linked to a classification, a
 * wing count, a diet, and a description. They may or may not be extinct.
 */
public interface IBird extends IDescribable {
  /**
   * Accessor for the bird instance name.
   *
   * @return String the bird's name
   */
  String getName();

  /**
   * Accessor for the bird instance type.
   *
   * @return BirdType the type of the bird
   */
  BirdType getType();

  /**
   * Accessor for the bird instance classification. Determined by type.
   *
   * @return BirdClassification the bird instance classification
   */
  BirdClassification getClassification();

  /**
   * Accessor for the bird instance wing count.
   *
   * @return int the bird instance wing count
   */
  int getWingCount();

  /**
   * Accessor for the bird instance diet list.
   *
   * @return the list of BirdDiet types consumed by this bird instance
   */
  List<BirdDiet> getDiet();

  /**
   * Accessor for the bird instance extinct attribute.
   *
   * @return boolean is bird extinct?
   */
  boolean isExtinct();
}
