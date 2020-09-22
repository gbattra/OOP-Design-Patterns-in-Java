package interfaces;

import java.util.ArrayList;

/**
 * Interface extending IBird for birds that have the ability to speak. These birds have a
 * favorite word and a list of other known words.
 */
public interface ITalkingBird extends IBird {
  /**
   * Accessor for the bird instance favorite word.
   *
   * @return String the bird's favorite favorite word
   */
  String getFavoriteWord();

  /**
   * Accessor for the bird instance vocabulary list.
   *
   * @return ArrayList<String> the bird's vocabulary list
   */
  ArrayList<String> getVocabulary();
}
