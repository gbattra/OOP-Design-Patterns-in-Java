package transmission;

/**
 * Enum encapsulating the different gears of a transmission.
 */
public enum GearRank {
  ZERO(0),
  FIRST(1),
  SECOND(2),
  THIRD(3),
  FOURTH(4),
  FIFTH(5),
  SIXTH(6);

  /**
   * An int representation of the gear rank.
   */
  public final int id;

  /**
   * Constructor for gear enum. All enum constructors are private.
   *
   * @param id int integer representation of each gear rank
   */
  private GearRank(int id) {
    this.id = id;
  }
}
