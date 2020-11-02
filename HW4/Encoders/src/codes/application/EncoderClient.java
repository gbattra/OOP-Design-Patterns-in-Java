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

public class EncoderClient implements Client {
  private final Map<String,
                    Function<Scanner, Command<EncoderController<String, String>>>> commands;
  private final EncoderController<String, String> controller;
  private final Readable in;
  private final Appendable out;
  private final Scanner scanner;

  public EncoderClient(
          EncoderController<String, String> controller,
          Readable in,
          Appendable out) {
    this.controller = controller;
    this.in = in;
    this.out = out;
    this.scanner = new Scanner(this.in);
    this.commands =  new HashMap<>() {{
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
    while (this.scanner.hasNext()) {
      try {
        String next = this.scanner.next();
        if (next.equalsIgnoreCase("q") || next.equalsIgnoreCase("quit")) {
          this.out.append("Quitting...\n");
          break;
        }

        Function<Scanner, Command<EncoderController<String, String>>> entry =
                commands.getOrDefault(next, null);

        if (entry == null) {
          this.out.append("Command not found. Try again.\n");
          continue;
        }

        Command<EncoderController<String, String>> cmd = entry.apply(this.scanner);
        cmd.execute(this.controller);
      } catch (Exception e) {
        status = 0;
        break;
      }
    }

    return status;
  }

  private Command<EncoderController<String, String>> newCommand(Scanner scanner) {
    try {
      this.out.append("Enter codes:\n");
      String codes = scanner.next();

      this.out.append("Enter symbols:\n");
      String symbols = scanner.next();

      return new NewCommand(codes, symbols);
    } catch (IOException e) {
      throw new IllegalStateException(
              String.format("Failed to instantiate newCommand. %s", e.getMessage()));
    }
  }

  private Command<EncoderController<String, String>> loadCommand(Scanner scanner) {
    try {
      this.out.append("Enter filename:\n");
      String filename = scanner.next();

      return new LoadCommand(filename);
    } catch (IOException e) {
      throw new IllegalStateException(
              String.format("Failed to instantiate loadCommand. %s", e.getMessage()));
    }
  }

  private Command<EncoderController<String, String>> saveCommand(Scanner scanner) {
    try {
      this.out.append("Enter filename:\n");
      String filename = scanner.next();

      return new SaveCommand(filename);
    } catch (IOException e) {
      throw new IllegalStateException(
      String.format("Failed to instantiate saveCommand. %s", e.getMessage()));
    }
  }

  private Command<EncoderController<String, String>> encodeCommand(Scanner scanner) {
    try {
      this.out.append("Enter sequence:\n");
      String sequence = scanner.next();

      return new EncodeCommand(sequence);
    } catch (IOException e) {
      throw new IllegalStateException(
      String.format("Failed to instantiate encodeCommand. %s", e.getMessage()));
    }
  }

  private Command<EncoderController<String, String>> decodeCommand(Scanner scanner) {
    try {
      this.out.append("Enter sequence:\n");
      String sequence = scanner.next();

      return new DecodeCommand(sequence);
    } catch (IOException e) {
      throw new IllegalStateException(
      String.format("Failed to instantiate decodeCommand. %s", e.getMessage()));
    }
  }
}
