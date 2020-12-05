package htw;

import java.io.IOException;

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
