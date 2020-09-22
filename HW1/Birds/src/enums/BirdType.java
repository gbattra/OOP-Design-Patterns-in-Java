package enums;

/**
 * Enum for each type of bird. Each BirdType is linked to a classification and is either extinct
 * or not extinct.
 */
public enum BirdType {
  HAWK("Hawk", BirdClassification.BIRD_OF_PREY, false),
  EAGLE("Eagle", BirdClassification.BIRD_OF_PREY, false),
  OSPREY("Osprey", BirdClassification.BIRD_OF_PREY, false),
  EMU("Emu", BirdClassification.FLIGHTLESS_BIRD, false),
  KIWIS("Kiwis", BirdClassification.FLIGHTLESS_BIRD, false),
  MOA("Moa", BirdClassification.FLIGHTLESS_BIRD, true),
  OWL("Owl", BirdClassification.OWL, false),
  ROSE_RING_PARAKEET("Rose Ring Parakeet", BirdClassification.PARROT, false),
  GRAY_PARROT("Gray Parrot", BirdClassification.PARROT, false),
  SULFUR_CRESTED_COCKATOO("Sulfur Crested Cockatoo", BirdClassification.PARROT, false),
  PASSENGER_PIGEON("Passenger Pigeon", BirdClassification.PIGEON, true),
  HOMING_PIGEON("Homing Pigeon", BirdClassification.PIGEON, false),
  GREAT_AUK("Great Auk", BirdClassification.SHOREBIRD, true),
  HORNED_PUFFIN("Horned Puffin", BirdClassification.SHOREBIRD, false),
  AFRICAN_JACANA("African Jacana", BirdClassification.SHOREBIRD, false),
  DUCK("Duck", BirdClassification.WATERFOWL, false),
  SWAN("Swan", BirdClassification.WATERFOWL, false),
  GOOSE("Goose", BirdClassification.WATERFOWL, false);

  /**
   * A string representation of the enum type
   */
  public final String label;

  /**
   * The classification enum linked to the bird type.
   */
  public final BirdClassification classification;

  /**
   * A boolean indicating whether or not the bird type is extinct.
   */
  public final boolean isExtinct;

  /**
   * The private constructor of the BirdType enum.
   *
   * @param classification BirdClassification enum linked to the bird type
   * @param isExtinct      boolean indicating whether or not the bird type is extinct
   */
  private BirdType(
          String label,
          BirdClassification classification,
          boolean isExtinct) {
    this.label = label;
    this.classification = classification;
    this.isExtinct = isExtinct;
  }
}
