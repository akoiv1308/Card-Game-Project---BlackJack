class Card {

  // Fields //
  private String rank; // card's rank //
  private String suit; // card's suit //
  private int pointValue; // card's point value //

  // Constructor //
  public Card(String rank, String suit, int pointValue) {
    this.rank = rank;
    this.suit = suit;
    this.pointValue = pointValue;
  }

  // Accessor Methods //
  public String getRank() {
    return rank;
  }
  public String getSuit() {
    return suit;
  }
  public int getValue() {
    return pointValue;
  }

  // Instance Methods //

  // equalCards - comparing whether two instance cards are equal. If not, then determining which card is greater and returning a string message //
  public boolean equalCards(Card a, Card b) {
    if(a.getValue() > b.getValue()) {
      return false;
    }
    else if(a.getValue() < b.getValue()) {
      return false;
    }
    else {
      return true;
    }
  }

  @Override
  public String toString() {
    return rank + " of " + suit + " (" + pointValue + ")";
  }

}