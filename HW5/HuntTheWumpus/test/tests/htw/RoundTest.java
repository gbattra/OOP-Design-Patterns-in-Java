package tests.htw;

import org.junit.Test;

import htw.game.IRound;
import htw.game.Round;

import static org.junit.Assert.assertEquals;

public class RoundTest {
  @Test
  public void testAddLog() {
    IRound round = new Round();
    round.addLog("This happened.");
    round.addLog("Then this happened.");
    assertEquals("This happened.\nThen this happened.\n", round.toString());
  }
}
