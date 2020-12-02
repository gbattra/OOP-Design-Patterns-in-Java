package tests.htw;

import org.junit.Test;

import htw.game.IHtwGame;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwGameBuilder;
import htw.tools.IHtwConfiguration;

import static org.junit.Assert.fail;

public class HtwGameBuilderTest {
  @Test
  public void testValidBuild() {
    try {
      IHtwConfiguration configuration = new HtwConfigurationBuilder().build();
      IHtwGame game = new HtwGameBuilder(configuration).build();
    } catch (Exception e){
      fail("Valid build() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    IHtwGame game = new HtwGameBuilder(null).build();
    fail("Invalid constructor should have failed.");
  }
}
