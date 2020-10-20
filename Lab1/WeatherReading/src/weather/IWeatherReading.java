package weather;

/**
 * Interface for weather.WeatherReading objects.
 */
public interface IWeatherReading {
  /**
   * Converts instance to string encoding.
   *
   * @return the string encoding
   */
  String toString();

  /**
   * Gets the raw value of the temperature property.
   *
   * @return the raw value of the temperature property
   */
  int getTemperature();

  /**
   * Gets the raw value of the temperature property.
   *
   * @return the raw value of the temperature property
   */
  int getDewPoint();

  /**
   * Gets the raw value of the temperature property.
   *
   * @return the raw value of the temperature property
   */
  int getWindSpeed();

  /**
   * Gets the raw value of the temperature property.
   *
   * @return the raw value of the temperature property
   */
  int getTotalRain();

  /**
   * Gets the computed value for heat index.
   *
   * @return the computed value for heat index
   */
  int getHeatIndex();

  /**
   * Gets the computed value for wind chill.
   *
   * @return the computed value for wind chill
   */
  int getWindChill();

  /**
   * Gets the computed value for relative humidity.
   *
   * @return the computed value for relative humidity
   */
  int getRelativeHumidity();
}
