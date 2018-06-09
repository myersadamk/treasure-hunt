package exigentech.treasure.hunt.core;

import static exigentech.treasure.hunt.core.Direction.EAST;
import static exigentech.treasure.hunt.core.Direction.NORTH;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StraightLineStrategy implements TreasureHuntingStrategy {

  private final List<Move> moveSequence;
  private Journey journey = null;

  public StraightLineStrategy(List<Move> moveSequence) {
    this.moveSequence = ImmutableList.copyOf(moveSequence);
  }

  @Override
  public Journey getJourney() {
    if (journey != null) {
      return journey;
    }

    final Map<Direction, List<Move>> movesByDirection = moveSequence.stream().collect(
        Collectors.groupingBy(Move::getDirection)
    );
    journey = ImmutableJourney.builder()
        .moveSequence(
            Stream.of(
                calculateNetOfMoves(NORTH, movesByDirection),
                calculateNetOfMoves(EAST, movesByDirection)
            ).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList())
        ).build();
    return journey;
  }

  private Optional<Move> calculateNetOfMoves(Direction ascendingDir, Map<Direction, List<Move>> movesByDir) {
    final double relativeDistance = Stream.concat(
        movesByDir.get(ascendingDir).stream(),
        movesByDir.get(ascendingDir.opposite()).stream()
    ).mapToDouble(Move::getRelativeDistance).sum();

    if (relativeDistance == 0) {
      return Optional.empty();
    }

    final double absoluteDistance = Math.abs(relativeDistance);
    if (relativeDistance < 0) {
      return Optional.of(Move.of(ascendingDir.opposite(), absoluteDistance));
    } else {
      return Optional.of(Move.of(ascendingDir, absoluteDistance));
    }
  }
}
