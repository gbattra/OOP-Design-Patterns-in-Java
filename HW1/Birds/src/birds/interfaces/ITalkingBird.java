package birds.interfaces;

import java.util.List;

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
   * @return the bird's vocabulary list
   */
  List<String> getVocabulary();
}
