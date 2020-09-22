package models;

import java.util.ArrayList;
import java.util.Arrays;

import enums.BirdClassification;
import enums.BirdDiet;
import enums.BirdType;
import interfaces.ITalkingBird;

public class TalkingBird extends Bird implements ITalkingBird {
  protected static final ArrayList<BirdClassification> permissibleBirdClassifications =
          new ArrayList<>(Arrays.asList(BirdClassification.PARROT));

  private final String favoriteWord;
  private final ArrayList<String> vocabulary;

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

  public String getFavoriteWord() {
    return this.favoriteWord;
  }

  public ArrayList<String> getVocabulary() {
    return this.vocabulary;
  }

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
