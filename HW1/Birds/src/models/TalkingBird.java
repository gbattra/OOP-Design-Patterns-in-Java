package models;

import java.util.ArrayList;
import java.util.Arrays;

import enums.BirdClassification;
import enums.BirdDiet;
import enums.BirdType;
import interfaces.ITalkingBird;

/**
 * Class representing the type of bird that can speak human language.
 */
public class TalkingBird extends Bird implements ITalkingBird {
  /**
   * The list of permissible bird classifications. Used to validate BirdType passed into
   * constructor.
   */
  protected final ArrayList<BirdClassification> permissibleBirdClassifications =
          new ArrayList<>(Arrays.asList(BirdClassification.PARROT));

  /**
   * The bird's favorite word.
   */
  private final String favoriteWord;

  /**
   * The list of words known by the bird.
   */
  private final ArrayList<String> vocabulary;

  /**
   * Constructor for the TalkingBird class. Passes some args up to AbstractBird class for validation.
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
          ArrayList<BirdDiet> diet,
          int wingCount,
          String favoriteWord,
          ArrayList<String> vocabulary) {
    super(name, type, diet, wingCount);
    this.favoriteWord = favoriteWord;
    this.vocabulary = vocabulary;
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
  public ArrayList<String> getVocabulary() {
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
            "%s knows %s words and its favorite word is: %s",
            this.name,
            this.vocabulary.size(),
            this.favoriteWord);
    return description;
  }
}
