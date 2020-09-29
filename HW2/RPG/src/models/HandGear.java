package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import interfaces.IGear;
import interfaces.IHandGear;

public class HandGear extends AbstractGear<IHandGear> implements IHandGear {
  public HandGear(
          int attack,
          int defense,
          String adjective,
          String noun) {
    super(attack, defense, adjective, noun);
  }

  public HandGear(
          int attack,
          int defense,
          String adjective,
          String noun,
          List<IGear<IHandGear>> combinedGears) {
    super(attack, defense, adjective, noun, combinedGears);
  }

  public IGear<IHandGear> combine(IGear<IHandGear> gear) throws IllegalStateException {
    if (this.isCombined) {
      throw new IllegalStateException(
              "Cannot combine self to gear. Self is already combined with another gear.");
    }

    if (gear.isCombined()) {
      throw new IllegalStateException(
              "Cannot combine self to gear. Gear is already combined with another gear.");
    }

    IGear<IHandGear> newGear = new HandGear(
            this.attack + gear.getAttack(),
            this.defense + gear.getDefense(),
            String.format("%s, %s", this.adjective, gear.getAdjective()),
            this.noun,
            new ArrayList<>(Arrays.asList(this, gear)));

    return newGear;
  }
}
