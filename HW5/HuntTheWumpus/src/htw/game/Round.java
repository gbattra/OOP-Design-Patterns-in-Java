package htw.game;

import java.util.ArrayList;
import java.util.List;

public class Round implements IRound {
  private List<String> logs;

  public Round() {
    this.logs = new ArrayList<>();
  }

  @Override
  public void addLog(String log) {
    this.logs.add(log);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (String log : this.logs) {
      builder.append(log).append("\n");
    }

    return builder.toString();
  }
}
