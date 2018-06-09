package exigentech.treasure.hunt.core;

import java.util.Collections;
import java.util.List;
import org.immutables.value.Value;

@Value.Immutable
public interface Journey {
  @Value.Default
  default List<Move> getMoveSequence() {
    return Collections.emptyList();
  }
}
