package tests.htw.game.commands;

import org.junit.Test;

import htw.game.commands.ICommand;
import htw.game.commands.StandardConfigCommand;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.IHtwConfigurationBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StandardConfigCommandTest {
  @Test
  public void testExecute() {
    try {
      ICommand<IHtwConfigurationBuilder> standard = new StandardConfigCommand();
      IHtwConfigurationBuilder builder = new HtwConfigurationBuilder();
      assertEquals(builder, standard.execute(builder));
    } catch (Exception e) {
      fail("Valid execute should not have failed.");
    }
  }
}
