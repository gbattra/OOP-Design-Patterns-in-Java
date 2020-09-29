package interfaces;

import java.util.List;
import java.util.Optional;

public interface IGear<T> {
  int getAttack();
  int getDefense();
  String getAdjective();
  String getNoun();
  String getName();
  Optional<List<IGear<T>>> combinedWith();
  IGear<T> combine(IGear<T> gear) throws IllegalStateException;
  boolean isCombined();
}
