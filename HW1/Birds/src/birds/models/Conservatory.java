package birds.models;

import java.util.ArrayList;
import java.util.List;

import birds.interfaces.IAviary;
import birds.interfaces.IConservatory;
import birds.interfaces.IConservatoryDirectory;
import birds.interfaces.IConservatoryIndex;

public class Conservatory {
  private final List<IAviary> aviaries;
//  private final IConservatoryDirectory directory;
//  private final IConservatoryIndex index;

  public Conservatory() {
    aviaries = new ArrayList<>();
  }
}
