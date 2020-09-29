package rpg.interfaces;

public interface IPlayer {
  int getNumber();
  int getAttack();
  int getDefense();
  IPlayer addHeadGear(IHeadGear handGear);
  IPlayer addHandGear(IHandGear handGear);
  IPlayer addFootGear(IFootGear handGear);
}
