package htw.game.commands;

import java.io.IOException;
import java.util.Scanner;

import htw.tools.IHtwConfigurationBuilder;

/**
 * Command for setting up a custom maze config.
 */
public class CustomConfigCommand implements ICommand<IHtwConfigurationBuilder> {
  private final Appendable out;
  private final Scanner in;

  /**
   * Constructor for the command.
   *
   * @param in scanner for reading inputs
   * @param out appendable for writing out logs
   * @throws IllegalArgumentException if params are null
   */
  public CustomConfigCommand(Scanner in, Appendable out) throws IllegalArgumentException {
    if (in == null || out == null) {
      throw new IllegalArgumentException("In and out cannot be null.");
    }

    this.in = in;
    this.out = out;
  }

  @Override
  public IHtwConfigurationBuilder execute(IHtwConfigurationBuilder receiver)
          throws IllegalArgumentException, IOException {
    if (receiver == null) {
      throw new IllegalArgumentException("Receiver cannot be null.");
    }
    this.out.append("Row count: ");
    receiver = (IHtwConfigurationBuilder) receiver.setRowCount(this.in.nextInt());

    this.out.append("Column count: ");
    receiver = (IHtwConfigurationBuilder) receiver.setColumnCount(this.in.nextInt());

    this.out.append("Pit frequency (double): ");
    receiver = receiver.setPitFrequency(this.in.nextDouble());

    this.out.append("Bat frequency (double): ");
    receiver = receiver.setBatFrequency(this.in.nextDouble());

    return receiver;
  }
}
