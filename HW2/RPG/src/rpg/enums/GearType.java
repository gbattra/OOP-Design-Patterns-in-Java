package rpg.enums;

public enum GearType {
  HAT(GearClass.HEADGEAR),
  HELMET(GearClass.HEADGEAR),
  VISOR(GearClass.HEADGEAR),
  GLOVE(GearClass.HANDGEAR),
  SWORD(GearClass.HANDGEAR),
  SHIELD(GearClass.HANDGEAR),
  BOOT(GearClass.FOOTGEAR),
  SNEAKER(GearClass.FOOTGEAR),
  HOVERBOARD(GearClass.FOOTGEAR);

  public final GearClass gearClass;

  GearType(GearClass gearClass) {
    this.gearClass = gearClass;
  }
}
