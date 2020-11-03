package tests.codes.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import codes.utils.StringHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the string helper class.
 */
public class StringHelperTest {
  @Test
  public void testDistinctCharacters() {
    String str = "abccddef";
    String dist = StringHelper.distinctCharacters(str);
    assertEquals(8, str.length());
    assertEquals(6, dist.length());
    for (Character c: str.toCharArray()) {
      assertTrue(dist.contains(String.valueOf(c)));
    }

    Map<Character, Integer> charMap = new HashMap<>();
    for (Character c : dist.toCharArray()) {
      charMap.merge(c, 1, Integer::sum);
    }

    for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
      assertEquals(1, (int) entry.getValue());
    }
  }
}
