package constant;

public enum ActionsEnum {
  EXIT(0),
  CREATE(1),
  LIST(2),
  UPDATE(3),
  REMOVE(4),
  SEARCH(5);

  private final int value;

  ActionsEnum(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
