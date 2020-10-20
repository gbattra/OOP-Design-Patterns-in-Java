# Documentation
### Interfaces

### `ICombinable<T>`
Interface packaging functionality to combine objects of type `T`.
- `T combine(T other)` <br>
Combines called instance `T` (`this`) with provided instance `T` (`other`)
and returns a new `T`.

- `boolean isCombined()` <br>
Indicates whether this instance of `T` has been combined with another instance of `T`.

- `Optional<List<T>> combinedWith()` <br>
Returns the list of `T` objects combined to create this instance. Optional
since instances of `ICombinable<T>` may not be combined yet.

### `IGear`
- `extends ICombinable<IHeadGear>` <br>

Interface for a gear that can be worn by an `IPlayer` instance. Gear (such as headgear,
handgear, and footgear) modifies a player's `attack` and `defense` values.

- `GearType getType()` <br>
Returns the `IGear` instance's `GearType` property.

- `int getAttack()` <br>
Returns the attack value for this gear.

- `int getDefense()` <br>
Returns the defense value for this gear.

- `String getAdjective()` <br>
Returns the adjective descriptor of this gear.

- `String getNoun()` <br> 
Returns the noun descriptor of this gear.

- `String getName()` <br>
Returns the full name of the gear. `name` is a combination
of the`adjective` and `noun` properties.

Gears are combinable with other instances of the same type. Combining gear results in a new
`IGear` instance with the `attack` and `defense` values from each original gear
added together. A gear can only be combined once.

### `IPlayer`
Interface for a player in the RPG. Players have base `attack` and `defense`
values, and can wear gear to augment those values: **1** piece of `IHeaderGear`,
**2** pieces of `IHandGear` and **2** pieces of `IFootGear`.

- `int getNumber()` <br>
Returns the ID number for the player. Set by constructor typically.

- `int getAttack()` <br>
Returns the total attack value for this player: the player's base attack value +
the attack value of any gear it is wearing.

- `IPlayer addGear(IGear gear)` <br>
Factory method which adds the `gear` to the player if possible and returns a
new updated player instance.

### `IPlayerBuilder`
Builder class for the player object. Facilitates initializing and adding
`IGear` items to the player.

- `IPlayerBuilder addGear(GearType type, int defense, String adjective, String noun)` <br>
Methods to add `IGear` to the player. Takes a `GearType`, the initial
`defense` and `attack` values for the gear, and the `adjective` and `noun` descriptors
of the gear.

- `IPlayer build()` <br>
Builds and returns the final `IPlayer` instance.

### `IBattle`
Interface to manage battles between players.
- `IBattle addPlayer(IPlayer player)` <br>
Adds a player to the battle and returns an updated `IBattle` instance.

- `List<IPlayer> getPlayers()` <br>
Returns the list of players in the battle.

- `IPlayer fight()` <br>
Fights the players against one another and returns the victor.

### Enums

### `GearClass`
Enum representing the different classes of gear.
```
HEADGEAR,
HANDGEAR,
FOOTGEAR
```

### `GearType`
Enum representing the different types of gear. Each `GearType` is linked
to a `GearClass` via the property:
- `GearClass gearClass`

```
// headgear
HAT
HELMET
VISOR

// handgear
GLOVE
SWORD
SHIELD

// footgear
BOOT
SNEAKER
HOVERBOARD
```

# Implementation

### The Player
The center-piece of any RPG is the player. Eeach player is constructed with
an initial `number`, `attack` and `defense`.
```
public Player(
          int number,
          int attack,
          int defense)
```
Invariants and argument constraints are enforced by the constructor:
```
if (number < 0 || attack < 0 || defense < 0) {
  throw new IllegalArgumentException("Number, attack and defense must be non-negative");
}
```
As all fields are `final`, it suffices to enforce these invariants in the constructor alone.

A player can "don" `gear` which augment
these base values.

```
public int getAttack() {
    int aggregateAttack = this.attack;
    for (IGear gear : this.gears) {
        aggregateAttack += gear.getAttack();
    }
    return aggregateAttack;
}
```

A player can hold `1` piece of `GearClass.HEADGEAR`, `2` pieces of `GearClass.HANDGEAR`
and `2` pieces of `GearClass.FOOTGEAR`. These constraints are enforced by the constructor.

If a player tries to add a `gear` belonging to a class for which it is already at capacity,
it will try to combine that `gear` with one of those gear. If the `gear` to combine
does not belong to the same `GearType`, it cannot be merged. If a `gear` is already
combined with another `gear`, it cannot be combined. If the player is, as a result,
unable to combine the gear, an `IllegalStateException` is thrown and the call to `addGear()`
fails.

i.e. for `GearClass.HEADGEAR`, the following code can be found in the constructor:
```
if (gears.stream().filter(gear -> gear.getType().gearClass == GearClass.HEADGEAR).count()
    > GearClass.HEADGEAR.count) {
  throw new IllegalStateException(
          String.format(
                  "Too many head gear items provided. Max: %s",
                  GearClass.HEADGEAR.count));
}
```

When a `player` tries to add a `gear` that violates the above constraints, an `IllegalStateException`
or `IllegalArgumentException` is thrown.

```
IPlayer addGear(IGear gear) throws IllegalStateException, IllegalArgumentException
```

### The Gear
There are three different classes of gear: `HEADGEAR`, `HANDGEAR`, and `FOOTGEAR`.
Within those classes are various `GearType`s such as `SHIELD` or `SWORD`. Each
instance of `IGear` is constructed with an `attack` and `defense` value, and an
`adjective` and `noun` value:
```
public Gear(
          GearType type,
          int attack,
          int defense,
          String adjective,
          String noun
```
If it is a combined gear, the constructor takes also the list of `gear`s combined to
make it:
```
public Gear(
          GearType type,
          int attack,
          int defense,
          String adjective,
          String noun,
          List<IGear> combinedGears)
```
The constructor in the abstract class `AbstractGear` enforces constraints and invariants
on the arguments and state of the `gear` instance:
```
if (type.gearClass == GearClass.HEADGEAR && attack > 0) {
  throw new IllegalArgumentException("Headgear cannot have an attack value great than zero.");
}

if (attack < 0 || defense < 0) {
  throw new IllegalArgumentException("Attack and defense values must be non-negative.");
}

if (adjective.isEmpty() || noun.isEmpty()) {
  throw new IllegalArgumentException("Adjective and noun must not be empty.");
}
```
As all fields are `final`, it suffices to enforce these invariants in the constructor alone.

Gear may be combined with other gear. Only non-combined gear of the same `GearType` can
be combined. These constraints are enforced by the `IGear combine(IGear gear)` method in
the abstract class `AbstractGear`: 
```
if (this.isCombined) {
  throw new IllegalStateException(
          "Cannot combine self to gear. Self is already combined with another gear.");
}

if (gear.isCombined()) {
  throw new IllegalArgumentException(
          "Cannot combine self to gear. Gear is already combined with another gear.");
}

if (this.type != gear.getType()) {
  throw new IllegalArgumentException("Cannot combine gears of two different types.");
}
```

When a gear is combined with another, their `attack` and `defense` values are summed,
and the `adjectives` concatenated. The `noun` of the called `gear` instance is kept.

```
IGear newGear = new Gear(
            this.type,
            this.attack + gear.getAttack(),
            this.defense + gear.getDefense(),
            String.format("%s, %s", gear.getAdjective(), this.adjective),
            this.noun,
            new ArrayList<>(Arrays.asList(this, gear)));

return newGear;
```

### The Battle
In an RPG the `player`s typically fight one another, using the gear they've collected.

For that, I have implemented a `Battle` class, which is instantiated with two `int`
arguments: `playerCount` and `gearCount` (the former determines how many players are
in the battle, the latter how many `gear` items they will have to chose from before 
the fight).

In the constructor, if the `players` list is bigger than the `playerCount` or the `gear`
list bigger than the `gearCount`, it throws `IllegalArgumentException`s:
```
if (playerCount <= 0) {
  throw new IllegalArgumentException("Player count must be greater than zero.");
}

if (players.size() > playerCount) {
  throw new IllegalArgumentException("Provided players list exceeds player count for battle.");
}

if (gears.size() > gearCount) {
  throw new IllegalArgumentException("Provided gear list exceeds gear count for battle.");
}
```
`gear` instances can be added to the `battle` instance:
```
public IBattle addGear(GearType type,
                         int attack,
                         int defense,
                         String adjective,
                         String noun) throws IllegalStateException, IllegalArgumentException
```
This factory method instantiates a new `battle` instance with the updated `gears` list.
In this way, the constructor constraints and invariants are again enforced.
```
List<IGear> updatedGears = new ArrayList<>(this.gears);
updatedGears.add(gear);

return new Battle(
        this.playerCount,
        this.gearCount,
        this.players,
        updatedGears);
```

Once the players and gear have been added, it is time to "dress" the players. Each
player alternates taking the best item from the remaining `gear` in the list. When a selction
is made, that gear is removed from the list and the next player makes their selection
from that smaller list.

To dress the players, call `battle.dressPlayers()`. This public method calls an internal
overloaded method `dressPlayers(List<IPlayer> players, List<IGear> gear)`.

This method is a recursive function which pops the first player off the list, determines the best gear for
that player. Adds that gear to the player and removes it from the list. Then adds the player
back to the list. If more gears remain, call itself with updated lists.
Else it returns `players`.

```
IPlayer player = playersCopy.get(0);
playersCopy.remove(0);

int bestGearIndex = this.getBestGearIndex(player, gearsCopy);
IGear bestGear = gearsCopy.get(bestGearIndex);
gearsCopy.remove(bestGearIndex);

// in the event that no player can aquire any of the remaining gear, we catch and return
try {
  IPlayer updatedPlayer = player.addGear(bestGear);
  playersCopy.add(updatedPlayer);
  if (!gearsCopy.isEmpty()) {
    return this.dressPlayers(playersCopy, gearsCopy);
  }

  return playersCopy;
} catch (Exception e) {
  if (!gearsCopy.isEmpty()) {
    return this.dressPlayers(playersCopy, gearsCopy);
  }

  return playersCopy;
}
```
The class determines the best gear for the player using the following criteria:
- If the `gear` item can be combined with a `gear` already worn by the player,
choose that `gear` item index.
```
IPlayer tmpPlayer = player.addGear(gear);
// if this gear was combined with another worn by player, choose it and break out of the loop
if (tmpPlayer.getGear().size() == player.getGear().size()) {
  bestGearIndex = i;
  break;
}
```
- If not that, then choose the `gear` which gives the `player` the highest `attack`
bump.
```
// if this gear provides the highest increase to attack, choose it
if (tmpPlayer.getAttack() > highestAttack) {
  highestAttack = tmpPlayer.getAttack();
  bestGearIndex = i;
  continue;
}
```
- If no `attack` gear remains (only `GearClass.HEADGEAR`), choose the `gear` with the
highest defense.
```
// while no gear with attack has been set, choose this gear if it has the highest defense
if (tmpPlayer.getDefense() > highestDefense && highestAttack <= player.getAttack()) {
  highestDefense += tmpPlayer.getDefense();
  bestGearIndex = i;
}
```
Once the players have been dressed with gear, it is time to fight. To initiate a fight,
call `battle.fight()`.

If `fight()` is called when the `players` list size is not equal to the`playerCount`
`int`, an `IllegalStateException` is thrown:
```
if (this.players.size() < this.playerCount) {
  throw new IllegalStateException(
          "Cannot fight until all players have been added to the battle");
}
```

The `fight()` method calls an internal overloaded method: `IPlayer fight(List<IPlayer> players)`.
This is a recursive function which pops the first two players off the list, fights them against
one another, and determines a victor. If no more players remain, the victor from the fight
is returned. Otherwise, the victor is added to the end of the players list, and the function
calls itself with the new list until all players have fought and only one player remains.
```
List<IPlayer> playersCopy = new ArrayList<>(players);

IPlayer playerOne = playersCopy.get(0);
IPlayer playerTwo = playersCopy.get(1);
playersCopy.remove(1);
playersCopy.remove(0);

IPlayer victor = this.duel(playerOne, playerTwo);

if (!playersCopy.isEmpty()) {
  playersCopy.add(victor);
  return this.fight(playersCopy);  // recursive call
}

return victor;
```

The `duel()` method returns the `player` that received the least damage as the victor
of that duel.

```
private IPlayer duel(IPlayer playerOne, IPlayer playerTwo) {
   int playerOneDamage = playerOne.getAttack() - playerTwo.getDefense();
   int playerTwoDamage = playerTwo.getAttack() - playerOne.getDefense();

   return playerOneDamage > playerTwoDamage ? playerOne : playerTwo;
}
```
# Demo
Below is a walk through of `Demo.java` and the corresponding outputs.
```
IPlayer playerOne = new PlayerBuilder(1, 10, 5).build();

System.out.println(playerOne.toString() + "\n");
```
```
Player 1:
- Total Attack: 10,
- Total Defense: 5,
- Base Attack: 10,
- Base Defense: 5,
- HeadGear: ,
- Handgear: ,
- Footgear: 
```

```
IPlayer playerTwo = new PlayerBuilder(2, 5, 10).build();

System.out.println(playerTwo.toString() + "\n");
```

```
Player 2:
- Total Attack: 5,
- Total Defense: 10,
- Base Attack: 5,
- Base Defense: 10,
- HeadGear: ,
- Handgear: ,
- Footgear: 
```

```
IBattle battle = new Battle(2, 10)
        .addPlayer(playerOne)
        .addPlayer(playerTwo);

System.out.println("Adding gear to the battlefield...");

battle = battle.addGear(GearType.HELMET, 0, 10, "steel", "helmet")
        .addGear(GearType.HELMET, 0, 5, "weak", "helmet")
        .addGear(GearType.HELMET, 0, 7, "kevlar", "hat")
        .addGear(GearType.SWORD, 15, 5, "steel", "sword")
        .addGear(GearType.SHIELD, 10, 15, "steel", "shield")
        .addGear(GearType.SHIELD, 10, 15, "steel", "shield")
        .addGear(GearType.GLOVE, 0, 3, "soft", "glove")
        .addGear(GearType.SHIELD, 2, 10, "light", "shield")
        .addGear(GearType.SNEAKER, 5, 5, "light-up", "sneaker")
        .addGear(GearType.BOOT, 7, 5, "steel-toed", "boot");

for (IGear gear : battle.getGears()) {
  System.out.println(gear.toString() + "\n");
}
```
```
Adding gear to the battlefield...
Gear - Type: HELMET, Name: steel helmet, Attack: 0, Defense: 10.

Gear - Type: HELMET, Name: weak helmet, Attack: 0, Defense: 5.

Gear - Type: HELMET, Name: kevlar hat, Attack: 0, Defense: 7.

Gear - Type: SWORD, Name: steel sword, Attack: 15, Defense: 5.

Gear - Type: SHIELD, Name: steel shield, Attack: 10, Defense: 15.

Gear - Type: SHIELD, Name: steel shield, Attack: 10, Defense: 15.

Gear - Type: GLOVE, Name: soft glove, Attack: 0, Defense: 3.

Gear - Type: SHIELD, Name: light shield, Attack: 2, Defense: 10.

Gear - Type: SNEAKER, Name: light-up sneaker, Attack: 5, Defense: 5.

Gear - Type: BOOT, Name: steel-toed boot, Attack: 7, Defense: 5.
```
```
System.out.println("Dressing players...");

battle = battle.dressPlayers();

System.out.println(battle.getPlayers().get(0).toString() + "\n");
System.out.println(battle.getPlayers().get(1).toString() + "\n");
```
```
Dressing players...
Player 1:
- Total Attack: 37,
- Total Defense: 52,
- Base Attack: 10,
- Base Defense: 5,
- HeadGear: Gear - Type: HELMET, Name: kevlar, steel helmet, Attack: 0, Defense: 17. Combined with: Gear - Type: HELMET, Name: steel helmet, Attack: 0, Defense: 10.Gear - Type: HELMET, Name: kevlar hat, Attack: 0, Defense: 7.,
- Handgear: Gear - Type: SWORD, Name: steel sword, Attack: 15, Defense: 5.; Gear - Type: SHIELD, Name: light, steel shield, Attack: 12, Defense: 25. Combined with: Gear - Type: SHIELD, Name: steel shield, Attack: 10, Defense: 15.Gear - Type: SHIELD, Name: light shield, Attack: 2, Defense: 10.,
- Footgear: 

Player 2:
- Total Attack: 27,
- Total Defense: 43,
- Base Attack: 5,
- Base Defense: 10,
- HeadGear: Gear - Type: HELMET, Name: weak helmet, Attack: 0, Defense: 5.,
- Handgear: Gear - Type: SHIELD, Name: steel shield, Attack: 10, Defense: 15.; Gear - Type: GLOVE, Name: soft glove, Attack: 0, Defense: 3.,
- Footgear: Gear - Type: BOOT, Name: steel-toed boot, Attack: 7, Defense: 5.; Gear - Type: SNEAKER, Name: light-up sneaker, Attack: 5, Defense: 5.
```
```
System.out.println("Fight!" + "\n");

IPlayer victor = battle.fight();

System.out.println("And the winner is...\n");

System.out.println(victor.toString() + "\n");
```
```
Fight!

And the winner is...

Player 1:
- Total Attack: 37,
- Total Defense: 52,
- Base Attack: 10,
- Base Defense: 5,
- HeadGear: Gear - Type: HELMET, Name: kevlar, steel helmet, Attack: 0, Defense: 17. Combined with: Gear - Type: HELMET, Name: steel helmet, Attack: 0, Defense: 10.Gear - Type: HELMET, Name: kevlar hat, Attack: 0, Defense: 7.,
- Handgear: Gear - Type: SWORD, Name: steel sword, Attack: 15, Defense: 5.; Gear - Type: SHIELD, Name: light, steel shield, Attack: 12, Defense: 25. Combined with: Gear - Type: SHIELD, Name: steel shield, Attack: 10, Defense: 15.Gear - Type: SHIELD, Name: light shield, Attack: 2, Defense: 10.,
- Footgear: 
```