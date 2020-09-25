package birds.enums;

/**
 * Enum representing the different classifications of birds. Each BirdType is linked to one of
 * these.
 */
public enum BirdClassification {
  BIRD_OF_PREY(
          "Bird of Prey",
          "Birds of prey all have sharp, hooked beaks with visible nostrils. " +
          "They include hawks, eagles, and osprey."),
  FLIGHTLESS_BIRD(
          "Flightless Bird",
          "Flightless birds live on the ground and have no (or undeveloped) wings. " +
          "They include emus, kiwis, and moas. Some (but not all) of these birds are extinct."),
  OWL(
          "Owl",
          "Owls are distinguished by the facial disks that frame the eyes and bill."),
  PARROT(
          "Parrot",
          "Parrots have a short, curved beak and are known for their intelligence and ability " +
          "to mimic sounds. Many pet parrots can learn a vocabulary of up to 100 words and " +
          "often adopt a single \"favorite\" saying.  They include the rose-ring parakeet, " +
          "gray parrot, and sulfur-crested cockatoo."),
  PIGEON(
          "Pigeon",
          "Pigeons (or doves) are known for feeding their young \"bird milk\" very similar to " +
          "the milk of mammals. Found all over the world, there are several varieties that are " +
          "extinct."),
  SHOREBIRD(
          "Shorebird",
          "Shorebirds include the great auk, horned puffin, and African Jacana. They live near " +
          "water sources including wetlands, freshwater and saltwater shorelands, even the ocean."),
  WATERFOWL(
          "Waterfowl",
          "Waterfowl live near water sources (fresh or salt) and include ducks, swans, and geese.");

  /**
   * A string representation of the enum type.
   */
  public final String label;

  /**
   * A description providing info about the classification.
   */
  public final String description;

  /**
   * Private constructor for each enum type.
   *
   * @param description String description of info about the classification type
   */
  private BirdClassification(
          String label,
          String description) {
    this.label = label;
    this.description = description;
  }
}
