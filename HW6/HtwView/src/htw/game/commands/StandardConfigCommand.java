package htw.game.commands;

import java.io.IOException;

import htw.tools.IHtwConfigurationBuilder;

/**
 * Command for a standard maze for the Hunt the Wumpus game.
 */
public class StandardConfigCommand implements ICommand<IHtwConfigurationBuilder> {
  @Override
  public IHtwConfigurationBuilder execute(IHtwConfigurationBuilder receiver)
          throws IllegalArgumentException, IOException {
    if (receiver == null) {
      throw new IllegalArgumentException("Receiver cannot be null.");
    }

    return receiver;
  }
}
