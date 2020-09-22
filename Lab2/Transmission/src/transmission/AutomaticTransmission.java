package transmission;

/**
 * Transmission object representing an engine transmission. Can increase speed
 * and automatically change gear depending on speed.
 */
public class AutomaticTransmission implements Transmission {
  /**
   * Constant representing the first gear speed threshold.
   */
  private static final int FIRST_GEAR_THRESHOLD = 1;

  /**
   * The current speed as set by constructor.
   */
  private final int speed;

  /**
   * The second gear speed threshold.
   */
  private final int secondGearThreshold;

  /**
   * The third gear speed threshold.
   */
  private final int thirdGearThreshold;

  /**
   * The fourth gear speed threshold.
   */
  private final int fourthGearThreshold;

  /**
   * The fifth gear speed threshold.
   */
  private final int fifthGearThreshold;

  /**
   * The sixth gear speed threshold.
   */
  private final int sixthGearThreshold;

  /**
   * Constructor for AutomaticTransmission object. Takes speed thresholds for each of the six
   * possible gears.
   *
   * @param secondGearThreshold int the second gear speed threshold
   * @param thirdGearThreshold  int the third gear speed threshold
   * @param fourthGearThreshold int the fourth gear speed threshold
   * @param fifthGearThreshold  int the fifth gear speed threshold
   * @param sixthGearThreshold  int the sixth gear speed threshold
   * @throws IllegalArgumentException throws when thresholds aren't in ascending order
   */
  public AutomaticTransmission(
          int secondGearThreshold,
          int thirdGearThreshold,
          int fourthGearThreshold,
          int fifthGearThreshold,
          int sixthGearThreshold) throws IllegalArgumentException {
    this.speed = 0;
    this.secondGearThreshold = secondGearThreshold;
    this.thirdGearThreshold = thirdGearThreshold;
    this.fourthGearThreshold = fourthGearThreshold;
    this.fifthGearThreshold = fifthGearThreshold;
    this.sixthGearThreshold = sixthGearThreshold;

    this.validateGearSpeedThresholds();
  }

  /**
   * Constructor for AutomaticTransmission object with current speed. Takes current speed and speed
   * thresholds for each of the six possible gears.
   *
   * @param currentSpeed        int the current speed (cannot be negative)
   * @param secondGearThreshold int the second gear speed threshold
   * @param thirdGearThreshold  int the third gear speed threshold
   * @param fourthGearThreshold int the fourth gear speed threshold
   * @param fifthGearThreshold  int the fifth gear speed threshold
   * @param sixthGearThreshold  int the sixth gear speed threshold
   * @throws IllegalArgumentException when speed is negative or gear thresholds aren't ascending
   */
  public AutomaticTransmission(
          int currentSpeed,
          int secondGearThreshold,
          int thirdGearThreshold,
          int fourthGearThreshold,
          int fifthGearThreshold,
          int sixthGearThreshold) throws IllegalArgumentException {
    this.speed = currentSpeed;
    this.secondGearThreshold = secondGearThreshold;
    this.thirdGearThreshold = thirdGearThreshold;
    this.fourthGearThreshold = fourthGearThreshold;
    this.fifthGearThreshold = fifthGearThreshold;
    this.sixthGearThreshold = sixthGearThreshold;

    this.validateGearSpeedThresholds();
    this.validateSpeed();
  }

  /**
   * Validation function for gear speed thresholds used in constructor.
   *
   * @throws IllegalArgumentException throws when gear speed thresholds are not in ascending order
   */
  private void validateGearSpeedThresholds() throws IllegalArgumentException {
    if (this.secondGearThreshold <= FIRST_GEAR_THRESHOLD
            || this.thirdGearThreshold <= this.secondGearThreshold
            || this.fourthGearThreshold <= this.thirdGearThreshold
            || this.fifthGearThreshold <= this.fourthGearThreshold
            || this.sixthGearThreshold <= this.fifthGearThreshold) {
      throw new IllegalArgumentException(
              "Each gear speed threshold must be greater than the previous gear speed threshold.");
    }
  }

  /**
   * Validation function for current speed used in constructor.
   *
   * @throws IllegalArgumentException throws when the current speed is negative
   */
  private void validateSpeed() {
    if (this.speed < 0) {
      throw new IllegalArgumentException("Speed must be non-negative.");
    }
  }

  /**
   * Accessor for the currentSpeed attribute.
   *
   * @return int the currentSpeed attribute value
   */
  public int getSpeed() {
    return this.speed;
  }

  /**
   * Accessor for the currentGear attribute. Gear value computed in terms of current speed and gear
   * speed thresholds.
   *
   * @return int the computed currentGear value
   */
  public int getGear() {
    if (this.speed >= this.sixthGearThreshold) {
      return GearRank.SIXTH.id;
    }
    if (this.speed >= this.fifthGearThreshold) {
      return GearRank.FIFTH.id;
    }
    if (this.speed >= this.fourthGearThreshold) {
      return GearRank.FOURTH.id;
    }
    if (this.speed >= this.thirdGearThreshold) {
      return GearRank.THIRD.id;
    }
    if (this.speed >= this.secondGearThreshold) {
      return GearRank.SECOND.id;
    }
    if (this.speed >= FIRST_GEAR_THRESHOLD) {
      return GearRank.FIRST.id;
    }

    return GearRank.ZERO.id;
  }

  /**
   * Instantiates new AutomaticTransmission object with speed of called instance incremented by 2.
   *
   * @return AutomaticTransmission the new AutomaticTransmission object with incremented speed
   */
  public AutomaticTransmission increaseSpeed() {
    int newSpeed = speed + 2;

    return new AutomaticTransmission(
            newSpeed,
            this.secondGearThreshold,
            this.thirdGearThreshold,
            this.fourthGearThreshold,
            this.fifthGearThreshold,
            this.sixthGearThreshold);
  }

  /**
   * Instantiates new AutomaticTransmission object with speed of called instance decremented by 2.
   *
   * @return AutomaticTransmission the new AutomaticTransmission object with decremented speed
   * @throws IllegalStateException when decremented speed is less than zero
   */
  public AutomaticTransmission decreaseSpeed() throws IllegalStateException {
    int newSpeed = speed - 2;
    if (newSpeed < 0) {
      throw new IllegalStateException("Transmission cannot have negative speed value.");
    }

    return new AutomaticTransmission(
            newSpeed,
            this.secondGearThreshold,
            this.thirdGearThreshold,
            this.fourthGearThreshold,
            this.fifthGearThreshold,
            this.sixthGearThreshold);
  }

  /**
   * Overrides toString() method.
   *
   * @return String representation of AutomaticTransmission instance
   */
  @Override
  public String toString() {
    return String.format(
            "Transmission (speed = %s, gear = %s)",
            this.speed, this.getGear());
  }
}
