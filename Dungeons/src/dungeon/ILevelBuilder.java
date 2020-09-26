package dungeon;

public interface ILevelBuilder {
  ILevelBuilder addRoom(String description);
  ILevelBuilder addGoblin(int room, int count);
  ILevelBuilder addOrc(int room, int count);
  ILevelBuilder addOgre(int room, int count);
  ILevelBuilder addHuman(int roomNumber,
                          String name,
                          String description,
                          int hitPoints);
  ILevelBuilder addPotion(int room);
  ILevelBuilder addGold(int room, int value);
  ILevelBuilder addWeapon(int room, String description);
  ILevelBuilder addSpecial(int room, String description, int value);
}
