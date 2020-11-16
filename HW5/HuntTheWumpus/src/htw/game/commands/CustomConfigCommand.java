package htw.game.commands;

import java.io.IOException;
import java.util.Scanner;

import htw.tools.IHtwConfigurationBuilder;

public class CustomConfigCommand implements ICommand<IHtwConfigurationBuilder> {
  private final Appendable out;
  private final Scanner in;

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

    this.out.append("Is room maze ('true' / 'false'): ");
    boolean isRoom = this.in.nextBoolean();
    receiver = (IHtwConfigurationBuilder) receiver.setIsRoomMaze(isRoom);
    if (isRoom) {
      this.out.append("Target edge count: ");
      receiver = (IHtwConfigurationBuilder) receiver.setTargetEdgeCount(this.in.nextInt());
    }

    this.out.append("Is wrapping maze ('true' / 'false'): ");
    receiver = (IHtwConfigurationBuilder) receiver.setIsWrappingMaze(this.in.nextBoolean());

    this.out.append("Pit frequency (double): ");
    receiver = receiver.setPitFrequency(this.in.nextDouble());

    this.out.append("Bat frequency (double): ");
    receiver = receiver.setBatFrequency(this.in.nextDouble());

    return receiver;
  }
}
