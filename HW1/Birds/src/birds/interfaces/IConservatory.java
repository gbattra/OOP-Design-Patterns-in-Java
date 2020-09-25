package birds.interfaces;

import java.util.Hashtable;
import java.util.List;

import birds.enums.BirdDiet;

/**
 * Interface for a conservatory object. Conservatories are made up of aviaries, which are located
 * at unique sectors through the conservatory.
 */
public interface IConservatory {
  /**
   * Accessor to the list of aviaries in the conservatory.
   *
   * @return List<IAviary> the list of aviaries
   */
  List<IAviary> getAviaries();

  /**
   * Adds an aviary to the conservatory.
   *
   * @param aviary IAviary the aviary to add
   *
   * @return a new IConservatory instance with updated aviaries
   */
  IConservatory addAviary(IAviary aviary) throws IllegalStateException ;

  /**
   * Finds the aviary housing the provided bird.
   *
   * @param bird the birds to search by
   * @return the aviary housing that bird
   */
  IAviary getAviaryWithBird(IBird bird);

  /**
   * Finds the aviary located at the provided sector.
   *
   * @param sector the sector to search by
   * @return the aviary located at the sector
   */
  IAviary getAviaryAtSector(int sector);

  /**
   * Returns a list of food requirements based on the birds housed in the conservatory.
   *
   * @return Hashtable<BirdDiet, Integer> the list of food requirements
   */
  Hashtable<BirdDiet, Integer> getFoodRequirements();

  /**
   * Gets a Directory object for the aviaries in the conservatory and their sector.
   *
   * @return a directory instance
   */
  IConservatoryDirectory getDirectory();

  /**
   * Gets an Index object for the birds in the conservatory and their sector.
   *
   * @return a index instance
   */
  IConservatoryIndex getIndex();

  /**
   * Describes the contents of the conservatory in human-readable form.
   *
   * @return String describing the conservatory
   */
  String describe();
}
