package rpg.enums;

public enum GearClass {
  HEADGEAR(1),
  HANDGEAR(2),
  FOOTGEAR(2);

  public final int count;

  GearClass(int count) {
    this.count = count;
  }
}
