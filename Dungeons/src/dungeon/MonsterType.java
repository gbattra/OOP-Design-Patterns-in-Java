package dungeon;

public enum MonsterType {
  GOBLIN(
          "Goblin",
          "Goblins are mischievous and very unpleasant, vengeful, and greedy creature whose"
          + "primary purpose is to cause trouble to humankind.",
          7),
  ORC(
          "Orc",
          "Orcs are brutish, aggressive, malevolent being serving evil but tend to be more of a"
          + "loner than the goblins.",
          20),
  OGRE(
          "Ogre",
          "Ogres are large, hideous man-like being that likes to eat humans for lunch.",
          50);

  public final String name;
  public final String description;
  public final int hitPoints;

  MonsterType(
          String name,
          String description,
          int hitPoints) {
    this.name = name;
    this.description = description;
    this.hitPoints = hitPoints;
  }
}
