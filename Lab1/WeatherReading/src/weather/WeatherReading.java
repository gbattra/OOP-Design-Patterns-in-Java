package weather;

/**
 * Class representing a weather reading. Initialized properties are: temperature in deg C, dew
 * point, wind speed, and rain in mm. Computes: Relative humidity, head index and wind chill.
 */
public class WeatherReading implements IWeatherReading {
  private int temperature;
  private int dewPoint;
  private int windSpeed;
  private int totalRain;

  /**
   * Constructor for the weather.WeatherReading object. Takes temperature, dewPoint, windSpeed
   * and totalRain as params.
   *
   * @param temperature the temperature in deg C
   * @param dewPoint    the dew point temperature. must not be greater than temperature field
   * @param windSpeed   the non-negative wind speed
   * @param totalRain   the non-negative total rain
   * @throws IllegalArgumentException throws illegal argument exceptions
   */
  public WeatherReading(
          int temperature,
          int dewPoint,
          int windSpeed,
          int totalRain) {
    if (dewPoint > temperature) {
      throw new IllegalArgumentException("Dew point must not be greater than temperature.");
    }

    if (windSpeed < 0) {
      throw new IllegalArgumentException("Wind speed must be non-negative.");
    }

    if (totalRain < 0) {
      throw new IllegalArgumentException("Total rain must be non-negative.");
    }

    this.temperature = temperature;
    this.dewPoint = dewPoint;
    this.windSpeed = windSpeed;
    this.totalRain = totalRain;

    if (this.getRelativeHumidity() > 100) {
      throw new IllegalArgumentException(
              "Dew point cannot result in a relative humidity greater than 100.");
    }

    if (this.getRelativeHumidity() < 0) {
      throw new IllegalArgumentException(
              "Dew point cannot result in a relative humidity less than 0.");
    }
  }

  /**
   * Getter for the temperature property.
   *
   * @return this instances temperature property value
   */
  public int getTemperature() {
    return this.temperature;
  }

  /**
   * Getter for the dewPoint property.
   *
   * @return this instances dewPoint property value
   */
  public int getDewPoint() {
    return this.dewPoint;
  }

  /**
   * Getter for the windSpeed property.
   *
   * @return this instances windSpeed property value
   */
  public int getWindSpeed() {
    return this.windSpeed;
  }

  /**
   * Getter for the totalRain property.
   *
   * @return this instances totalRain property value
   */
  public int getTotalRain() {
    return this.totalRain;
  }

  /**
   * Computes heatIndex given the instance's relative humidity and temperature.
   *
   * @return the computed heatIndex value
   */
  public int getHeatIndex() {
    double c1 = -8.78469475556;
    double c2 = 1.61139411;
    double c3 = 2.33854883889;
    double c4 = -0.14611605;
    double c5 = -0.012308094;
    double c6 = -0.0164248277778;
    double c7 = 0.002211732;
    double c8 = 0.00072546;
    double c9 = -0.000003582;

    int relativeHumidity = this.getRelativeHumidity();

    double heatIndex = (c1
            + c2 * this.temperature
            + c3 * relativeHumidity
            + c4 * this.temperature * relativeHumidity
            + c5 * Math.pow(this.temperature, 2)
            + c6 * Math.pow(relativeHumidity, 2)
            + c7 * Math.pow(this.temperature, 2) * relativeHumidity
            + c8 * this.temperature * Math.pow(relativeHumidity, 2)
            + c9 * Math.pow(this.temperature, 2) * Math.pow(relativeHumidity, 2));

    return (int) heatIndex;
  }

  /**
   * Computes windChill given the instance's temperature and wind speed.
   *
   * @return the computed windChill value
   */
  public int getWindChill() {
    int tempInF = (int) ((9.0 / 5.0) * this.getTemperature() + 32);
    double vPow = Math.pow(this.windSpeed, 0.16);
    double windChill = 35.74 + 0.6215 * tempInF - 35.75 * vPow + 0.4275 * tempInF * vPow;

    return (int) windChill;
  }

  /**
   * Computes relativeHumidity given the instance's temperature and dew point.
   *
   * @return the computed relativeHumidity value
   */
  public int getRelativeHumidity() {
    return (5 * this.dewPoint) - (5 * this.temperature) + 100;
  }

  /**
   * Overrides the toString method.
   *
   * @return String the string representation of this instance
   */
  @Override
  public String toString() {
    return String.format(
            "Reading: T = %s, D = %s, v = %s, rain = %s",
            this.temperature,
            this.dewPoint,
            this.windSpeed,
            this.totalRain);
  }
}
