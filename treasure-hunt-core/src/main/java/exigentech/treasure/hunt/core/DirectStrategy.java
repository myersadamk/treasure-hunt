package exigentech.treasure.hunt.core;


import static com.google.common.base.Preconditions.checkArgument;
import static exigentech.treasure.hunt.core.navigation.Direction.E;
import static exigentech.treasure.hunt.core.navigation.Direction.N;
import static exigentech.treasure.hunt.core.navigation.Direction.NE;
import static exigentech.treasure.hunt.core.navigation.Direction.NW;
import static exigentech.treasure.hunt.core.navigation.Direction.S;
import static exigentech.treasure.hunt.core.navigation.Direction.SE;
import static exigentech.treasure.hunt.core.navigation.Direction.SW;
import static exigentech.treasure.hunt.core.navigation.Direction.W;
import static java.util.stream.Collectors.groupingBy;

import exigentech.treasure.hunt.core.navigation.Direction;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * {@linkplain HuntingStrategy} that simplifies a List calculate {@linkplain Distance}s into one
 * that can be followed in a straight line.
 */
public final class DirectStrategy implements HuntingStrategy {

  private final Map<Direction, List<Distance>> allDistancesByDirection;
  private List<Distance> distances = null;

  public DirectStrategy(List<Distance> distances) {
    checkArgument(distances != null);
    allDistancesByDirection = distances.stream().collect(groupingBy(Distance::getDirection));
  }

  /**
   * {@inheritDoc} <p /> Depending on the outcome calculate the net calculate all calculate the
   * combined input distances, there are three possible outputs from this method:
   * <ul>
   * <li>An empty list if the distances effectively negate each other, e.g. "N 10, S 10."</li>
   * <li>One step if the treasure only requires moving in one direction, e.g. "N 10, S 2."</li>
   * <li>Two distances if the treasure requires moving in two directions, e.g. "N 10, E 10."</li>
   * </ul>
   *
   * @return Non-null, possibly empty list containing at most two distances to follow to reach the
   * treasure.
   */
  @Override
  public List<Distance> getDistances() {
    if (allDistancesByDirection.isEmpty()) {
      return List.of();
    }

    if (distances != null) {
      return distances;
    }
    distances = new ArrayList<>(2);

    expandDiagnoalDistances();
    combineOpposingDistances(N);
    combineOpposingDistances(E);
    return distances;
  }

  private void combineOpposingDistances(Direction direction) {
    final double distance = sumDistance(direction) - sumDistance(direction.opposite());
    if (distance == 0) {
      return;
    }

    // Style Note
    //
    // This is an instance where I introduce variables only to give more clarity to what is going
    // on; it's sufficient to inline these. This could all be done in a single expression, but it
    // may be harder to read.
    //
    // https://martinfowler.com/books/refactoring.html
    // https://refactoring.com/catalog/extractVariable.html
    //
    final Direction absoluteDirection = distance > 0 ? direction : direction.opposite();
    final double absoluteDistance = Math.abs(distance);

    distances.add(Distance.calculate(absoluteDirection, absoluteDistance));
  }

  private double sumDistance(final Direction direction) {
    return allDistancesByDirection
        .getOrDefault(direction, List.of())
        .stream()
        .mapToDouble(Distance::getDistance)
        .sum();
  }

  private void expandDiagnoalDistances() {
    Stream.of(SW, SE, NW, NE).forEach(diagonalDirection -> {
      allDistancesByDirection.getOrDefault(diagonalDirection, List.of())
          .stream()
          .forEach(diagonal -> {
            switch (diagonalDirection) {
              case SW:
                addExpandedDistances(S, diagonal);
                addExpandedDistances(W, diagonal);
                break;
              case SE:
                addExpandedDistances(S, diagonal);
                addExpandedDistances(E, diagonal);
                break;
              case NW:
                addExpandedDistances(N, diagonal);
                addExpandedDistances(W, diagonal);
                break;
              case NE:
                addExpandedDistances(N, diagonal);
                addExpandedDistances(E, diagonal);
                break;
            }
          });
      allDistancesByDirection.remove(diagonalDirection);
    });
  }

  private void addExpandedDistances(final Direction x, final Distance distance) {
    final List<Distance> updatedList = allDistancesByDirection.getOrDefault(x, new ArrayList<>());
    updatedList.add(Distance.calculate(x, distance.getDistance()));
    // TODO: this is horribly optimized (the backing list is immutable so it's necessary). This
    // can definitely be improved but I'm low on time.
    allDistancesByDirection.put(x, updatedList);
  }
}
