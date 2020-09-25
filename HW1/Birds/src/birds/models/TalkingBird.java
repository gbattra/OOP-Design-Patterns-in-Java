package birds.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import birds.enums.BirdClassification;
import birds.enums.BirdDiet;
import birds.enums.BirdType;
import birds.interfaces.ITalkingBird;

/**
 * Class representing the type of bird that can speak human language.
 */
public class TalkingBird extends Bird implements ITalkingBird {
  /**
   * Max number of words this bird can know.
   */
  private static final int VOCABULARY_MAX = 100;

  /**
   * Minimum number of words to provide constructor.
   */
  private static final int VOCABULARY_MIN = 0;
  /**
   * The list of permissible bird classifications. Used to validate BirdType passed into
   * constructor.
   */
  protected static final List<BirdClassification> PERMISSIBLE_CLASSIFICATIONS =
          new ArrayList<>(Arrays.asList(BirdClassification.PARROT));

  /**
   * The bird's favorite word.
   */
  private final String favoriteWord;

  /**
   * The list of words known by the bird.
   */
  private final List<String> vocabulary;

  /**
   * Constructor for the TalkingBird class. Passes some args up to AbstractBird class for
   * validation.
   *
   * @param name String the name of the bird
   * @param type BirdType the type of the bird
   * @param diet ArrayList<BirdDiet> the diet of the bird
   * @param wingCount int how many wings the bird has
   * @param favoriteWord String the bird's favorite word
   * @param vocabulary ArrayList<String> list of known words
   * @throws IllegalArgumentException when the provided inputs violate any constraints
   */
  public TalkingBird(
          String name,
          BirdType type,
          List<BirdDiet> diet,
          int wingCount,
          String favoriteWord,
          List<String> vocabulary) throws IllegalArgumentException {
    super(name, type, diet, wingCount);
    this.favoriteWord = favoriteWord;
    this.vocabulary = vocabulary;

    if (this.favoriteWord.isEmpty()) {
      throw new IllegalArgumentException("No favorite word provided.");
    }

    if (this.vocabulary.size() > TalkingBird.VOCABULARY_MAX
        || this.vocabulary.size() == TalkingBird.VOCABULARY_MIN) {
      throw new IllegalArgumentException("Vocabulary size must not be greater than 100.");
    }

    // enforces constraint that the provided BirdType belongs to a permissible classification
    if (!TalkingBird.PERMISSIBLE_CLASSIFICATIONS.contains(this.type.classification)) {
      throw new IllegalArgumentException(
              String.format(
                "Provided bird type must belong to a permissible classification." +
                "Provided bird type classification: %s. Permissible bird type classifications: %s",
                this.type.classification.label,
                TalkingBird.PERMISSIBLE_CLASSIFICATIONS.stream().map(
                        birdClassification -> birdClassification.label)));
    }
  }

  /**
   * Accessor for the bird instance favorite word.
   *
   * @return String the bird's favorite favorite word
   */
  public String getFavoriteWord() {
    return this.favoriteWord;
  }

  /**
   * Accessor for the bird instance vocabulary list.
   *
   * @return ArrayList<String> the bird's vocabulary list
   */
  public List<String> getVocabulary() {
    return this.vocabulary;
  }

  /**
   * Overrides the default descriptor method to incorporate unique attributes
   * (favoriteWord, vocabulary).
   *
   * @return String the instance description
   */
  @Override
  public String describe() {
    String description = super.describe();
    description += String.format(
            " %s knows %s words and its favorite word is: %s.",
            this.name,
            this.vocabulary.size(),
            this.favoriteWord);
    return description;
  }
}
