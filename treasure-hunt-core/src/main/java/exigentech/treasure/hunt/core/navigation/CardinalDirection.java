package exigentech.treasure.hunt.core.navigation;

public enum CardinalDirection implements Direction {

  N("North"), S("South"), E("East"), W("West"),
  NW("North West"), NE("North East"), SW("South West"), SE("South East");

  @Override
  public String describe() {
    return description;
  }

  @Override
  public CardinalDirection opposite() {
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

  CardinalDirection(String description) {
    this.description = description;
  }
}
