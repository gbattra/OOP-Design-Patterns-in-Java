package models;

import java.util.ArrayList;
import java.util.List;

import interfaces.IHandGear;
import interfaces.IPlayer;

public class Player implements IPlayer {
  private final List<IHandGear> handGears;

  public Player(List<IHandGear> handGears) {
    this.handGears = handGears;
  }

  public IPlayer addHandGear(IHandGear gear) throws IllegalStateException {
    List<IHandGear> handGearsCopy = new ArrayList<>(this.handGears);
    if (handGearsCopy.size() < 2) {
      handGearsCopy.add(gear);
      IPlayer player = new Player(handGearsCopy);
      return player;
    }


  }
}
