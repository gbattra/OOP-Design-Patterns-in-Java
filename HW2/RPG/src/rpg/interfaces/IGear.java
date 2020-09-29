package rpg.interfaces;

import rpg.enums.GearType;

public interface IGear {
  GearType getType();
  int getAttack();
  int getDefense();
  String getAdjective();
  String getNoun();
  String getName();
}
