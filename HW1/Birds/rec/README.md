#Documentation
### Abstractions
### `IBird`
`String getName()`


`String BirdType getType()`

### `ITalkingBird`

### `IWaterBird`

### `IAviary`

### `IConservatory`

### `IConservatoryIndex`

### `IConservatoryDirectory`


# Implementation
###The Bird

The program centers around the concept of a bird. Birds have various features such
wing count, diet, type and classification, and extinction status.

As such, the constructor to the base `Bird` class is as follows:
```
public Bird(
          String name,
          BirdType type,
          List<BirdDiet> diet,
          int wingCount) throws IllegalArgumentException {
    this.name = name;
    this.type = type;
    this.diet = diet;
    this.wingCount = wingCount;
```
The `wingCount` is represented as an `int` and the birds are also provided a
`String name`. To track its diet, the program uses a custon `BirdDiet` enum,
each with a `String label`.

```
BERRIES("Berries"),
SEEDS("Seeds"),
FRUIT("Fruit"),
...
```
To encapsulate the types of the bird and its classifications, the program
and uses custom enums.

First, the `BirdClassification` enum represents the classifications of each
bird type. Each `BirdClassification` enum has the following properties:
- `String label`
- `String description`.
```
System.out.println(String.format(
           "The conservatory can house birds from the following classifications: %s\n",
           String.join(", ", Arrays.asList(
                   BirdClassification.BIRD_OF_PREY.label,
                   BirdClassification.FLIGHTLESS_BIRD.label,
                   BirdClassification.PIGEON.label,
                   BirdClassification.OWL.label,
                   BirdClassification.PARROT.label,
                   BirdClassification.WATERFOWL.label,
                   BirdClassification.SHOREBIRD.label))));
```
```
The conservatory can house  birds from the following classifications: Bird of Prey,
Flightless Bird, Pigeon, Owl, Parrot, Waterfowl, Shorebird
```
An example `BirdClassification` description would be accessed like so:
```
System.out.println(BirdClassification.BIRD_OF_PREY.description);
```
```
Birds of prey all have sharp, hooked beaks with visible nostrils. They include hawks,
eagles, and osprey.
```

Linked to each classification is a `BidType` enum. Each `BirdType` has the following properties:
- `String label`
- `boolean isExtinct`
- `BirdClassification classification`

```
System.out.println(BirdType.ROSE_RING_PARAKEET.label);
System.out.println(BirdType.ROSE_RING_PARAKEET.isExtinct);
System.out.println(BirdType.ROSE_RING_PARAKEET.classification);
```
```
Rose Ring Parakeet
false
Parrot
```
**Putting it all together**, we can instantiate a `Bird` and access all of this
information:
```
IBird bird = new Bird(
  "Rex",
  BirdType.EAGLE,
  new ArrayList<>(Arrays.asList(
                  BirdDiet.SMALL_MAMMALS,
                  BirdDiet.FISH,
                  BirdDiet.OTHER_BIRDS)),
  2);
```
```
bird.getName();  // "Rex"
bird.getType().label;  // "Eagle" (bird.type.label)
bird.getClassification().label;  // "Bird of Prey" (bird.type.classification.label)
bird.getExtinct();  // false (bird.type.isExtinct)
bird.getDiet();  // ArrayList<BirdDiet>
bird.getWingCount();  // 2
```

##### Unique birds
Some birds have unique features that other birds do not. Some birds can talk,
others live near water. To manage these differences programmatically, there are
three subclasses of the `Bird` base class.
- `StandardBird`
- `TalkingBird`
- `WaterBird`

The constructors of each ensure that they can only be instantiated with certain
`BirdType` enum values.

For example, `StandardBird` can only accept `BirdTypes` linked to the following `PERMISSIBLE_CLASSIFICATIONS`:

```
protected static final List<BirdClassification> PERMISSIBLE_CLASSIFICATIONS =
      new ArrayList<>(Arrays.asList(
              BirdClassification.BIRD_OF_PREY,
              BirdClassification.FLIGHTLESS_BIRD,
              BirdClassification.OWL,
              BirdClassification.PIGEON));
```
And then in the constructor:
```
if (!StandardBird.PERMISSIBLE_CLASSIFICATIONS.contains(this.type.classification)) {
  throw new IllegalArgumentException(
      String.format(
        "Provided bird type must belong to a permissible classification." +
        "Provided bird type classification: %s. Permissible bird type classifications: %s",
        this.type.classification.label,
        StandardBird.PERMISSIBLE_CLASSIFICATIONS.stream().map(
                birdClassification -> birdClassification.label)));
}
```
The program also exposes unique functionality for each of these subclasses.

`TalkingBird` instances have a `String favoriteWord` and a `List<String> vocabulary`.
It's constructor enforces that these be set on instantiation. To expose these properties,
the `TalkingBird` class implements `ITalkingBird`.

```
IBird luke = new TalkingBird(
    "Axel",
    BirdType.GRAY_PARROT,
    new ArrayList<>(Arrays.asList(
            BirdDiet.FISH,
            BirdDiet.EGGS)),
    2,
    "Flamingo",
    new ArrayList<>(Arrays.asList("Hello", "Goodbye", "Okay", "Love", "Sorry")))
```
```
luke.getFavoriteWord(); // "Flamingo"
```

`WaterBird` instances live near a `String nearestWaterBody`. The constructor of
the `WaterBird` classes enforces a valid `String` be passed in for the `nearestWaterBody`
attribute. To expose this unique property, the `WaterBird` class implements `IWaterBird`.
```
WaterBird bird = new WaterBird(
    "Rex",
    BirdType.DUCK,
    new ArrayList<>(Arrays.asList(
            BirdDiet.FISH,
            BirdDiet.INSECTS,
            BirdDiet.LARVAE)),
    2,
    "Moosehead Lake");
```
```
bird.getNearestWaterBody();  // "Moosehead Lake"
```

**Finally**, a succinct human-readable description of the bird instance
can be generated by calling `bird.describe()`:

```
Rex is a Eagle, which belongs to the classification Bird of Prey. Birds of prey all have
sharp, hooked beaks with visible nostrils. Rex likes to eat Small mammals, Fish,
Other birds.
```

###The Aviary
To manage small groups of birds, conservatories typically have multiple "aviaries",
small  structures that can house several birds. There are restrictions, however,
in terms of the quantity of birds in a given aviary, as well as the type of each bird
(some cannot be mixed with other bird types in the same aviary).

The program represents all this in the `Aviary` class, which implements `IAviary`.
`Aviary`s have the following attributes:
- `ArrayList<IBird> birds`
- `int sector`

The constructor enforces that no `Aviary` is instantiated with more than 5 birds.
The `ArrayList<IBird> birds` is final which ensures this invariant holds true at
all times. The constructor also checks that a valid `int sector` was provided,
that no bird provided `isExtinct()`, and that the names of each bird are unique.

```
if (this.birds.size() > Aviary.BIRD_COUNT_MAX) {
  throw new IllegalStateException("Aviary cannot house more than 5 birds at a time.");
}

if (this.sector < Aviary.SECTOR_ID_MIN) {
  throw new IllegalStateException("Aviary must have valid sector number.");
}

if (this.birds.stream().anyMatch(IBird::isExtinct)) {
  throw new IllegalArgumentException("Extinct birds cannot be added to an aviary.");
}

List<String> birdNames = this.birds.stream().map(IBird::getName)
                                            .collect(Collectors.toList());
if (!(birdNames.size() == birdNames.stream().distinct().count())) {
  throw new IllegalArgumentException("All birds must have unique names.");
}
```
Finally, the constructor enforces constraints on which bird types may be added based
on whether they can be mixed or not.
```
if ((currentBirdClasses.contains(BirdClassification.BIRD_OF_PREY)
    || currentBirdClasses.contains(BirdClassification.FLIGHTLESS_BIRD)
    || currentBirdClasses.contains(BirdClassification.WATERFOWL))
    && currentBirdClasses.size() > 1) {
  throw new IllegalArgumentException(
          String.format(
                  "%s, %s, and %s cannot be mixed with other bird types.",
                  BirdClassification.BIRD_OF_PREY.label,
                  BirdClassification.FLIGHTLESS_BIRD.label,
                  BirdClassification.WATERFOWL.label));
}
```
A new bird may be added to an existing aviary, though the same checks above with
be made to ensure it is a valid entry. To do this, the program implements a factory
method, passing the current state, plus the new bird, into the constructor of a new
`Aviary` instance:
```
IAviary aviary = new Aviary(1);
aviary.add(waterBird);  // waterBird is of type BirdType.DUCK
try {
  aviary.addBird(birdOfPrey);  // birdOfPrey is of BirdType.EAGLE
} catch (Exception e) {
  System.out.println("Failed!\n");
  System.out.println(e.getMessage() + "\n");
}
```
```
Failed!
Bird of Prey, Flightless Bird, and Waterfowl cannot be mixed with other bird types.
```
Aviaries can help track what food is required on a daily basis to feed the birds
it houses: `Hashtable<BirdDiet, Integer> getFoodRequirements()`

**Finally**, we can generate a human-reable description of the aviary and the birds
within it by calling `aviary.describe()`:
```
The aviary in sector 1 houses 2 birds of types Gray Parrot, Horned Puffin. Below are descriptions of
each bird living in this aviary:
- Luke is a Gray Parrot, which belongs to the classification Parrot. Parrots have a short, curved
  beak and are known for their intelligence and ability to mimic sounds. Many pet parrots can learn
  a vocabulary of up to 100 words and often adopt a single "favorite" saying.  They include the
  rose-ring parakeet, gray parrot, and sulfur-crested cockatoo. Luke likes to eat Berries, Seeds,
  Insects. Luke knows 5 words and its favorite word is: Flamingo.
- Sam is a Horned Puffin, which belongs to the classification Shorebird. Shorebirds include the
  great auk, horned puffin, and African Jacana. They live near water sources including wetlands,
  freshwater and saltwater shorelands, even the ocean. Sam likes to eat Berries, Seeds, Insects.
  Sam lives near the water body Bar Harbor.
``` 
###The Conservatory
The last significant structure used by the program is the `Conservatory` class,
which implements `IConservatory`. Conservatories organize aviaries into sectors,
help manage food requirements, and can provide a directory and index for its birds and
aviaries. They can also query their aviaries for a specific bird, or find an aviary
at a given sector.

The `Conservatory` constructor enforces that no more than 20 aviaries are provided,
that each aviary belongs to a unique sector, and that the sector ids are not out
of bounds.

```
if (aviaries.stream().anyMatch(aviary -> aviary.getSector() > AVIARY_LIMIT)) {
  throw new IllegalArgumentException("Sector value out of bounds.");
}

if (aviaries.size() !=
    aviaries.stream().map(IAviary::getSector).distinct().count()) {
  throw new IllegalArgumentException("Each aviary must reside in unique sector.");
}

if (this.aviaries.size() > Conservatory.AVIARY_LIMIT) {
  throw new IllegalArgumentException("Conservatory cannot have more than 20 aviaries.");
}
```

We can add aviaries to an existing conservatory by calling: 
`IConservatory addAviary(IAviary aviary)`. This will run through the above
constructor checks again, instantiatng and returning a new `Conservatory` instance with the
updated state. Another factory method, like the `Aviary` `addBird()` method.
```
List<IAviary> aviaries = new ArrayList<>(this.aviaries);
    aviaries.add(aviary);
    try {
      return new Conservatory(aviaries);
    } catch (IllegalArgumentException e)
    {
      throw new IllegalStateException(e.getMessage());
    }
}
```

As with aviaries, conservatories also compute the daily food requirements for
all of the birds it houses. It does this by aggregating the results of each
of its aviaries `getFoodRequirements()` method.
```
for (IAviary aviary : this.aviaries) {
  Hashtable<BirdDiet, Integer> aviaryFoodRequirements = aviary.getFoodRequirements();
  for (Map.Entry<BirdDiet, Integer> entry : aviaryFoodRequirements.entrySet()) {
    if (requirements.containsKey(entry.getKey())) {
      requirements.replace(
              entry.getKey(), requirements.get(entry.getKey()) + entry.getValue());
    } else {
      requirements.put(entry.getKey(), entry.getValue());
    }
  }
}
```
The conservatory is able to query its aviaries for a given bird:
 `Optional<IAviary> getAviaryWithBird(IBird bird)`. It does so using each bird
instances `equals()` method, which is overridden in the base `Bird` class:
```
// equals(Object other)
if (!(other instanceof Bird)) {
  return false;
}

Bird bird = (Bird) other;
return bird.hashCode() == other.hashCode();
```
The `hashCode()` method is override to return the `String.hashCode()` of the
`Bird` instance's `toString()` method, which is also overriden:
```
// toString()
return String.format(
        "%s_%s_%s_%s",
        this.name,
        this.type.label,
        this.wingCount,
        this.diet.stream().map(diet -> diet.label).collect(Collectors.joining("_")));
```

A conservatory is also able to query its aviaries by `sector` and return the
corresponding `Aviary` instance, if found: `Optional<IAviary> getAviaryAtSector(int sector)`

A conservatory can produce a `ConservatoryDirectory` object which holds a 
`Hashtable<Integer, IAviary> directory` of the aviaries at each sector. A
`ConservatoryDirectory` object can describe its contents in human-readable form:
```
IConservatoryDirectory directory = conservatory.getDirectory();
System.out.println(directory.describe());
```
```
There are 3 aviaries in the conservatory:
- Sector 3 has an aviary with the bird types: Eagle
- Sector 2 has an aviary with the bird types: Goose
- Sector 1 has an aviary with the bird types: Gray Parrot, Horned Puffin
```

Similarly, a `Conservatory` instance can produce a `ConservatoryDirectory` tracking
the sector location of each bird:
```
IConservatoryIndex index = conservatory.getIndex();
System.out.println(index.describe());
```
```
The following birds are housed in this conservatory:
- Rex the Eagle lives in the aviary at sector 3
- Sam the Horned Puffin lives in the aviary at sector 1
- Luke the Gray Parrot lives in the aviary at sector 1
- Lucy the Goose lives in the aviary at sector 2
```
And like `Bird` and `Aviary` instances, a `Conservatory` instance can generate
a human-readable description of its contents:
```
conservatory.describe();
```
```
This conservatory has 3 aviaries located in sectors 1, 2, 3. These aviaries are home to the
following types of birds: Gray Parrot, Horned Puffin, Goose, Eagle. For more information please
read the conservatory directory or index.
```