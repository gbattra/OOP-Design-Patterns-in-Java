#Documentation
### `IBird`

### `ITalkingBird`

### `IWaterBird`

### `IAviary`

### `IConservatory`

### `IConservatoryIndex`

### `IConservatoryDirectory`

# Design Discussion

#Demo Run
Here is the output of a demo run of the program (code found in Driver.c):
```
Welcome to our bird conservatory, where we rescue and house birds.

We house birds from the following classifications: Bird of Prey, Flightless Bird, Pigeon, Owl,
Parrot, Waterfowl, Shorebird

Some birds hunt.

Instantiating a Bird of Prey...

Description of this bird of prey:

Rex is a Eagle, which belongs to the classification Bird of Prey. Birds of prey all have sharp,
hooked beaks with visible nostrils. They include hawks, eagles, and osprey. Rex likes to eat Small
mammals, Fish, Other birds.

Some birds can talk.

Instantiating a Talking Bird...

This bird knows 5 words and its favorite word is: Flamingo.

Some birds live near water.

Instantiating a WaterBird...

The instantiated water bird lives near: Moosehead Lake

Birds have a diet. For example, the water bird we just initialized eats: Berries, Seeds, Insects

We house small groups of birds in aviaries, located in sectors.

Instantiating an aviary...

The aviary we just instantiated resides in sector 1. 

You may add up to 5 birds to an aviary.

Adding a Talking Bird to the aviary...

Description of the aviary:

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


But careful not to mix any types!

Adding a Bird of Prey to the same aviary...

Failed!

Bird of Prey, Flightless Bird, and Waterfowl cannot be mixed with other bird types.

And of course, no extinct birds either.

Adding an extinct bird to the same aviary...

Failed!

Extinct birds cannot be added to an aviary.

We can see the different bird types in the aviary:

The aviary houses birds of the follow types: Gray Parrot, Horned Puffin.

Aviaries can track how much food is needed to feed their birds on a daily basis.

For example, the aviary we just created computes that we need the following food:
Seeds - 2, Berries - 2, Insects - 2, 

We organize these aviaries inside a conservatory.

Instantiating a conservatory...

We can add aviaries to our conservatory.

Adding aviaries to the conservatory...

Conservatories can hold up to 20 aviaries. This can be hard to keep track of.

Conservatories can describe themselves briefly:

This conservatory has 3 aviaries located in sectors 1, 2, 3. These aviaries are home to the
following types of birds: Gray Parrot, Horned Puffin, Goose, Eagle. For more information please
read the conservatory directory or index.

We can query a conservatory to find the aviary for a specific bird.

Let's find our water bird, Lucy...

Lucy is located at the aviary in sector 1.

We can get the aviary at certain sector.

Finding aviary at sector 1.

The resulting aviary is located at 1

A conservatory can calculate the daily food requirements for all its aviaries.

For the conservatory we just created, we need the following food:
Berries - 3, Seeds - 3, Fish - 1, Small mammals - 1, Other birds - 1, Insects - 3, 

A conservatory can provide a directory linking aviaries to their sectors.

Generating a directory for the conservatory...

The resulting directory contents:

There are 3 aviaries in the conservatory:
- Sector 3 has an aviary with the bird types: Eagle
- Sector 2 has an aviary with the bird types: Goose
- Sector 1 has an aviary with the bird types: Gray Parrot, Horned Puffin

A conservatory can also provide an index linking birds to their sectors.

Generating a index for the conservatory...

The resulting index contents:

The following birds are housed in this conservatory:
- Rex the Eagle lives in the aviary at sector 3
- Sam the Horned Puffin lives in the aviary at sector 1
- Luke the Gray Parrot lives in the aviary at sector 1
- Lucy the Goose lives in the aviary at sector 2
```