package exigentech.treasure.hunt.core.navigation;

public enum Direction {

  N("North"), S("South"), E("East"), W("West"),
  NW("North West"), NE("North East"), SW("South West"), SE("South East");

  public String describe() {
    return description;
  }

  public Direction opposite() {
    switch (this) {
      case N:
        return S;
      case S:
        return N;
      case E:
        return W;
      case W:
        return E;
      case NW:
        return SE;
      case NE:
        return SW;
      case SE:
        return NW;
      case SW:
        return NE;
    }
    throw new IllegalStateException("Unrecognized direction: " + this.name());
  }

  private String description;

  Direction(String description) {
    this.description = description;
  }
}
