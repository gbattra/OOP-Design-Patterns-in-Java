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

### `IHeadGear`, `IHandGear`, `IFootGear`
Interfaces for the subtypes of gears. Each interface extends
`IGear` and `ICombinable<T>`:
- `extends IGear, ICombinable<IHeadGear>` <br>
- `extends IGear, ICombinable<IHandGear>` <br>
- `extends IGear, ICombinable<IFootGear>` <br>

So these gears are combinable with other instances of their subtypes.
Combining gear results in a new `IGear` instance with the `attack` and
`defense` values from each original gear added together. A gear can only be
combined once.

### `IPlayer`
Interface for a player in the RPG. Players have base `attack` and `defense`
values, and can wear gear to augment those values: **1** piece of `IHeaderGear`,
**2** pieces of `IHandGear` and **2** pieces of `IFootGear`.

- `int getNumber()` <br>
Returns the ID number for the player. Set by constructor typically.

- `int getAttack()` <br>
Returns the total attack value for this player: the player's base attack value +
the attack value of any gear it is wearing.

- `IPlayer addHeadGear(IHeadGear gear)` <br>
Factory method which adds the `gear` to the player if possible and returns a
new updated player instance.

- `IPlayer addHeadGear(IHandGear gear)` <br>
Factory method which adds the `gear` to the player if possible and returns a
new updated player instance.

- `IPlayer addHeadGear(IFootGear gear)` <br>
Factory method which adds the `gear` to the player if possible and returns a
new updated player instance.

### `IPlayerBuilder`
Builder class for the player object. Facilitates initializing and adding
`IGear` items to the player.

- `IPlayerBuilder addHeadGear(GearType type, int defense, String adjective, String noun)` <br>
- `IPlayerBuilder addHanddGear(GearType type, int defense, String adjective, String noun)` <br>
- `IPlayerBuilder addFootGear(GearType type, int defense, String adjective, String noun)` <br>
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
 
# Demo