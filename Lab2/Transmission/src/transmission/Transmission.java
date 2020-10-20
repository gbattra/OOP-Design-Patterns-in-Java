package transmission;

/**
 * Interface for a transmission object. transmission.models.Transmission objects are expected to
 * increase and decrease speed, and change gears.
 */
public interface Transmission {
  /**
   * Copies the current transmission.models.Transmission object, increments the speed by 2, and
   * returns the new instance.
   *
   * @return transmission.ITransmission the new transmission instance
   * @throws IllegalStateException throws when resulting speed value is greater than engine limit
   */
  Transmission increaseSpeed();

  /**
   * Copies the current transmission.models.Transmission object, decrements the speed by 2, and
   * returns the new instance.
   *
   * @return transmission.ITransmission the new transmission instance
   * @throws IllegalStateException throws when resulting speed value is less than 0
   */
  Transmission decreaseSpeed() throws IllegalStateException;

  /**
   * Accessor for the transmission's speed attribute.
   *
   * @return int the current speed value
   */
  int getSpeed();

  /**
   * Accessor for the transmission's gear attribute.
   *
   * @return int the current gear value
   */
  int getGear();
}
