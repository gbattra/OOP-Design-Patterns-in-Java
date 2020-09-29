package models;

import interfaces.AbstractGear;
import interfaces.IGear;
import interfaces.IHandGear;

public class HandGear implements IHandGear {
  public IGear<IHandGear> combine(IGear<IHandGear> gear) {
    return new HandGear();
  }
}
