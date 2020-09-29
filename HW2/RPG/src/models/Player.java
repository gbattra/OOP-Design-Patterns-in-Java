package models;

import java.util.ArrayList;
import java.util.List;

import interfaces.IFootGear;
import interfaces.IHandGear;
import interfaces.IHeadGear;
import interfaces.IPlayer;

public class Player implements IPlayer {
  private static final int HEAD_GEAR_COUNT = 1;
  private static final int HAND_GEAR_COUNT = 2;
  private static final int FOOT_GEAR_COUNT = 2;

  private final int number;
  private final int attack;
  private final int defense;
  private final List<IHeadGear> headGears;
  private final List<IHandGear> handGears;
  private final List<IFootGear> footGears;

  public Player(
          int number,
          int attack,
          int defense) {
    this.number = number;
    this.attack = attack;
    this.defense = defense;
    this.headGears = new ArrayList<>();
    this.handGears = new ArrayList<>();
    this.footGears = new ArrayList<>();
  }

  public Player(
          int number,
          int attack,
          int defense,
          List<IHeadGear> headGears,
          List<IHandGear> handGears,
          List<IFootGear> footGears) {
    this.number = number;
    this.attack = attack;
    this.defense = defense;
    this.headGears = headGears;
    this.handGears = handGears;
    this.footGears = footGears;
  }

  public IPlayer addHeadGear(IHeadGear gear) throws IllegalStateException {
    List<IHeadGear> headGearsCopy = new ArrayList<>(this.headGears);
    if (headGearsCopy.size() < HEAD_GEAR_COUNT) {
      headGearsCopy.add(gear);
      IPlayer player = new Player(
              this.number,
              this.attack,
              this.defense,
              headGearsCopy,
              this.handGears,
              this.footGears);
      return player;
    }

    boolean combined = false;
    List<IHeadGear> newHeadGears = new ArrayList<>(this.headGears);
    for (int i = 0; i < this.headGears.size(); i++) {
      if (combined) {
        break;
      }
      try {
        IHeadGear newGear = this.headGears.get(i).combine(gear);
        newHeadGears.set(i, newGear);
        combined = true;
      } catch (IllegalStateException e) {
        newHeadGears.set(i, this.headGears.get(i));
      }
    }

    if (!combined) {
      throw new IllegalStateException("Failed to add head gear.");
    }

    IPlayer player = new Player(
            this.number,
            this.attack,
            this.defense,
            newHeadGears,
            this.handGears,
            this.footGears);

    return player;
  }

  public IPlayer addHandGear(IHandGear gear) throws IllegalStateException {
    List<IHandGear> handGearsCopy = new ArrayList<>(this.handGears);
    if (handGearsCopy.size() < HAND_GEAR_COUNT) {
      handGearsCopy.add(gear);
      IPlayer player = new Player(
              this.number,
              this.attack,
              this.defense,
              this.headGears,
              handGearsCopy,
              this.footGears);
      return player;
    }

    boolean combined = false;
    List<IHandGear> newHandGears = new ArrayList<>(this.handGears);
    for (int i = 0; i < this.handGears.size(); i++) {
      if (combined) {
        break;
      }
      try {
        IHandGear newGear = this.handGears.get(i).combine(gear);
        newHandGears.set(i, newGear);
        combined = true;
      } catch (IllegalStateException e) {
        newHandGears.set(i, this.handGears.get(i));
      }
    }

    if (!combined) {
      throw new IllegalStateException("Failed to add hand gear.");
    }

    IPlayer player = new Player(
            this.number,
            this.attack,
            this.defense,
            this.headGears,
            newHandGears,
            this.footGears);

    return player;
  }

  public IPlayer addFootGear(IFootGear gear) throws IllegalStateException {
    List<IFootGear> footGearsCopy = new ArrayList<>(this.footGears);
    if (footGearsCopy.size() < FOOT_GEAR_COUNT) {
      footGearsCopy.add(gear);
      IPlayer player = new Player(
              this.number,
              this.attack,
              this.defense,
              this.headGears,
              this.handGears,
              footGearsCopy);
      return player;
    }

    boolean combined = false;
    List<IFootGear> newFootGears = new ArrayList<>(this.footGears);
    for (int i = 0; i < this.footGears.size(); i++) {
      if (combined) {
        break;
      }
      try {
        IFootGear newGear = this.footGears.get(i).combine(gear);
        newFootGears.set(i, newGear);
        combined = true;
      } catch (IllegalStateException e) {
        newFootGears.set(i, this.footGears.get(i));
      }
    }

    if (!combined) {
      throw new IllegalStateException("Failed to add foot gear.");
    }

    IPlayer player = new Player(
            this.number,
            this.attack,
            this.defense,
            this.headGears,
            this.handGears,
            newFootGears);

    return player;
  }
}
