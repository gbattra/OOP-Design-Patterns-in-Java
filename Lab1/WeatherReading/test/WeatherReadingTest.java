import org.junit.Test;

import weather.IWeatherReading;
import weather.WeatherReading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * JUnit test for the weather.WeatherReading class.
 */
public class WeatherReadingTest {
  @Test
  public void testInitializedPropertyGetters() {
    int temperature = 45;
    int dewPoint = 40;
    int windSpeed = 40;
    int totalRain = 25;

    IWeatherReading weatherReading =
            new WeatherReading(temperature, dewPoint, windSpeed, totalRain);
    assertEquals(temperature, weatherReading.getTemperature());
    assertEquals(dewPoint, weatherReading.getDewPoint());
    assertEquals(windSpeed, weatherReading.getWindSpeed());
    assertEquals(totalRain, weatherReading.getTotalRain());
  }

  @Test
  public void testToString() {
    IWeatherReading weatherReading = new WeatherReading(5, 0, 10, 0);
    assertEquals("Reading: T = 5, D = 0, v = 10, rain = 0", weatherReading.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDewPoint() {
    IWeatherReading weatherReading = new WeatherReading(50, 60, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDewPointVisaVisRelativeHumidity() {
    int temperature = 50;
    int dewPoint = -50;
    IWeatherReading weatherReading = new WeatherReading(temperature, dewPoint, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWindSpeed() {
    IWeatherReading weatherReading = new WeatherReading(0, 0, -10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTotalRain() {
    IWeatherReading weatherReading = new WeatherReading(0, 0, 0, -10);
  }

  @Test
  public void testValidConstructorArgs() {
    try {
      IWeatherReading weatherReading1 = new WeatherReading(10, 10, 10, 10);
      IWeatherReading weatherReading2 = new WeatherReading(-10, -20, 10, 10);
    } catch (IllegalArgumentException e) {
      fail("Valid constructor args should not have thrown exception.");
    }
  }

  @Test
  public void testGetRelativeHumidity() {
    for (int i = -50; i < 50; i++) {
      int temperature = i;
      int dewPoint = i - 5;
      // formula for relative humidity is: 5D - 5T + 100 = R
      int expectedRelativeHumidity = (5 * dewPoint) - (5 * temperature) + 100;
      IWeatherReading weatherReading = new WeatherReading(temperature, dewPoint, 0, 0);
      assertEquals(expectedRelativeHumidity, weatherReading.getRelativeHumidity());
    }
  }

  @Test
  public void testGetWindChill() {
    int temperature = 1;
    int windSpeed = 15;
    int expectedWindChill = 22;
    IWeatherReading weatherReading = new WeatherReading(temperature, 0, windSpeed, 0);
    assertEquals(expectedWindChill, weatherReading.getWindChill());

    temperature = 10;
    windSpeed = 25;
    expectedWindChill = 42;
    weatherReading = new WeatherReading(temperature, 0, windSpeed, 0);
    assertEquals(expectedWindChill, weatherReading.getWindChill());
  }

  @Test
  public void testGetHeatIndex() {
    int temperature = 10;
    int dewPoint = 10;
    int expectedHeatIndex = 20;
    IWeatherReading weatherReading = new WeatherReading(temperature, dewPoint, 0, 0);
    assertEquals(expectedHeatIndex, weatherReading.getHeatIndex());
  }
}
