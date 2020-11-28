package htw.game.events;

import visitors.IGameEventVisitor;
import visitors.IVisitable;

public interface IGameEvent extends IVisitable<IGameEventVisitor> {
}
