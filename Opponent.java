import java.util.*;

class Opponent {

  // Fields //
  private ArrayList<Card> hand; // opponent's hand //
  private int handValue = 0; // value of the hand //
  private int numOfAces; // counts the aces in the dealer's hand to determine whether the opponent's hand needs to be decremented in its point value //

  // Constructor //
  public Opponent(Deck deck) {
    hand = new ArrayList<>(); // initializing an ArrayList of hand //
    numOfAces = 0;
    for(int i = 0; i < 2; i++) { // adding two cards to an opponent's hand //
      hand.add(deck.getCard());
    }
    determineCurrentValue(); // determining the value opponent withdrew from the deck //
  }

  // Instance Methods //
  
  public void determineCurrentValue() { 
    handValue = 0;
    for(Card card : hand) {
      handValue += card.getValue();
      if(card.getValue() == 11) {
        numOfAces++;
      }
      while (numOfAces > 0 && handValue > 21) {
        handValue -= 10;
        numOfAces--;
      }
    }
  }

  public void firstCard() { // prints the dealer's first card //
    System.out.println("[" + hand.get(0) + "]");
  }

  public boolean hasBlackJack() { // determining whether the opponent has BlackJack in the first two cards withdrown //
    if(hand.size() == 2 && handValue == 21) {
      return true;
    }
    return false;
  }

  public void hit(Deck deck) { // gives the dealer another card and updates the value of his hand //
    hand.add(deck.getCard());
    determineCurrentValue();
  }

  public ArrayList<Card> showHand() { // shows opponent's hand //
    return hand;
  }

  public int getHandValue() { // gets current opponent's point value of hand //
    return handValue;
  }

  // In BlackJack, the opponent must continue to take cards until the total is 17 or more, at which point the opponent must stand //

  public int opponentsTurn(Deck deck) { // takes the turn for the opponent and returns the value of his hand //
  int num = 0;
    while(handValue < 17) {
      if(num > 0) {
        System.out.println("\nThe opponent hits again");
        num++;
      }
      if(num == 0) {
        System.out.println("\nThe opponent hits");
        num++;
      }
      hit(deck); // takes another card from the deck //
      if(handValue > 21) { // over 21, the game is over //
        System.out.println("\nThe opponent has over 21!");
        break;
      }
    }
    if(handValue <= 21) { 
      System.out.print("\nThe opponent stands.");
    }
    return handValue;
  }

}