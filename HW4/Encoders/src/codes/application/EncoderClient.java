package codes.application;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import codes.application.commands.Command;
import codes.application.commands.DecodeCommand;
import codes.application.commands.EncodeCommand;
import codes.application.commands.LoadCommand;
import codes.application.commands.NewCommand;
import codes.application.commands.SaveCommand;

/**
 * Client object for an encoder controller. Implements a set of commands which interact with
 * the controller, allowing the user to create encoders, save or load encoders, and encode/decode
 * sequences.
 */
public class EncoderClient implements Client {
  private final Map<String,
                    Function<Scanner, Command<Controller<String, String>>>> commands;
  private final Controller<String, String> controller;
  private final Readable in;
  private final Appendable out;
  private final Scanner scanner;

  /**
   * Constructor for the encoder client. Takes a controller instance and abstract readable &
   * appendable objects (could be sys.out or file, etc).
   *
   * @param controller the controller which loads and operates the encoder
   * @param in where the input to the client comes from
   * @param out where the output from the client goes
   */
  public EncoderClient(
          Controller<String, String> controller,
          Readable in,
          Appendable out) {
    this.controller = controller;
    this.in = in;
    this.out = out;
    this.scanner = new Scanner(this.in);
    this.commands = new HashMap<>() {{
        put("new", s -> newCommand(s));
        put("load", s -> loadCommand(s));
        put("save", s -> saveCommand(s));
        put("encode", s -> encodeCommand(s));
        put("decode", s -> decodeCommand(s));
    }};
  }

  @Override
  public int run() {
    int status = 1;
    while (true) {
      try {
        this.out.append(
                "Enter: 'new', 'load', 'save', 'encode', or 'decode' ('q' or 'quit' to exit):\n");
        String next = this.scanner.nextLine();
        if (next.equalsIgnoreCase("q") || next.equalsIgnoreCase("quit")) {
          this.out.append("Quitting...\n");
          break;
        }

        Function<Scanner, Command<Controller<String, String>>> entry =
                commands.getOrDefault(next, null);

        if (entry == null) {
          this.out.append("Command not found. Try again.\n");
          continue;
        }

        Command<Controller<String, String>> cmd = entry.apply(this.scanner);
        cmd.execute(this.controller);
      } catch (Exception e) {
        status = 0;
        break;
      }
    }

    return status;
  }

  /**
   * Instantiates a NewCommand.
   *
   * @param scanner the scanner to read inputs from
   * @return the newCommand instance
   */
  private Command<Controller<String, String>> newCommand(Scanner scanner) {
    try {
      this.out.append("Enter codes:\n");
      String codes = scanner.nextLine();

      this.out.append("Enter symbols:\n");
      String symbols = scanner.nextLine();

      return new NewCommand(codes, symbols, this.out);
    } catch (IOException e) {
      throw new IllegalStateException(
              String.format("Failed to instantiate newCommand. %s", e.getMessage()));
    }
  }

  /**
   * Creates a LoadCommand.
   *
   * @param scanner the scanner to read inputs from
   * @return the new LoadCommand instance
   */
  private Command<Controller<String, String>> loadCommand(Scanner scanner) {
    try {
      this.out.append("Enter filename:\n");
      String filename = scanner.nextLine();

      return new LoadCommand(filename, this.out);
    } catch (IOException e) {
      throw new IllegalStateException(
              String.format("Failed to instantiate loadCommand. %s", e.getMessage()));
    }
  }

  /**
   * Creates a SaveCommand.
   *
   * @param scanner the scanner to read inputs from
   * @return the new SaveCommand instance
   */
  private Command<Controller<String, String>> saveCommand(Scanner scanner) {
    try {
      this.out.append("Enter filename:\n");
      String filename = scanner.nextLine();

      return new SaveCommand(filename, this.out);
    } catch (IOException e) {
      throw new IllegalStateException(
      String.format("Failed to instantiate saveCommand. %s", e.getMessage()));
    }
  }

  /**
   * Creates a EncodeCommand.
   *
   * @param scanner the scanner to read inputs from
   * @return the new EncodeCommand instance
   */
  private Command<Controller<String, String>> encodeCommand(Scanner scanner) {
    try {
      this.out.append("Enter sequence:\n");
      String sequence = scanner.nextLine();

      return new EncodeCommand(sequence, this.out);
    } catch (IOException e) {
      throw new IllegalStateException(
      String.format("Failed to instantiate encodeCommand. %s", e.getMessage()));
    }
  }

  /**
   * Creates a DecodeCommand.
   *
   * @param scanner the scanner to read inputs from
   * @return the new DecodeCommand instance
   */
  private Command<Controller<String, String>> decodeCommand(Scanner scanner) {
    try {
      this.out.append("Enter sequence:\n");
      String sequence = scanner.nextLine();

      return new DecodeCommand(sequence, this.out);
    } catch (IOException e) {
      throw new IllegalStateException(
      String.format("Failed to instantiate decodeCommand. %s", e.getMessage()));
    }
  }
}
