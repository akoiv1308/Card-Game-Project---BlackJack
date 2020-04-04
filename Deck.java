import java.util. *;

class Deck {

  // Fields //
  private ArrayList<Card> cards;
  private int size;

  // Constructor //
  public Deck(String[] ranks, String[] suits, int[] values) {
    this.cards = new ArrayList<Card>(); // Initializing field variable //
    // Looping through arrays passed as parameters in the Deck constructor method and adding them consequently to ArrayList called "cards" //
    for(String suit : suits){ 
      for(String rank : ranks){
        // determineValue(rank) - since both for loops are for "suits" and "ranks" array, I added another instance method as a third parameter for a new Card object that represents a card's point value //
        this.cards.add(new Card(rank, suit, determineValue(rank)));
      }
    }
    // assigning variable "size" to the size of the "cards" //
    size = cards.size();
    // calling shuffle method in order to randomly shuffle the deck //
    shuffle();
  }

  // determineValue - an instance method that simply determines the card's point value and returns it //
  public int determineValue(String rank) {
    int value = 0;
    switch(rank) {
      case "Two" : return value = 2;
      case "Three" : return value = 3;
      case "Four" : return value = 4;
      case "Five" : return value = 5;
      case "Six" : return value = 6;
      case "Seven" : return value = 7;
      case "Eight" : return value = 8;
      case "Nine" : return value = 9;
      case "Ten" : return value = 10;
      case "Jack" : return value = 10;
      case "Queen" : return value = 10;
      case "King" : return value = 10;
      case "Ace" : return value = 11;
      // if none of the following is the case, then value of 0 is assigned to the variable value //
      default : return value = 0;
    }
  }

  // shuffle - method that shuffles the deck randomly //
  public void shuffle() {
		for (int k = cards.size() - 1; k > 0; k--) { // starting from the index of the last element //
			int howMany = k + 1; // variable responsible for the size of the deck; it is determined by adding one to the variable k which has been set to the size of the deck - 1 //
			int start = 0; // starting from the first element in ArrayList //
			int randPos = (int) (Math.random() * howMany) + start;  // variable that randomly chooses the element of the deck by randomly selecting from the size of the deck //
			Card temp = cards.get(k); // variable temp is temporary variable that get the element at index k //
			cards.set(k, cards.get(randPos)); // element at index k is replaced with random element/value //
			cards.set(randPos, temp); // the element at index randPos is replaced by the temporary variable, temp, which is the element at index k //
		}
	}

  // Another simple way to shuffle cards in an ArrayList //

  /*
  public void shuffleTwo() {
    Collections.shuffle(cards);
  }
  */

  // isEmpty — This method should return true when the size of the deck is 0; false otherwise //
  public boolean isEmpty() {
    if(cards.size() == 0) { // if the deck is empty, return true //
      return true;
    }
    else { // otherwise, return false //
      return false;
    }
  }

  // size — This method returns the number of cards in the deck that are left to be dealt //
  public int size() {
    return cards.size(); // returns deck's size //
  }

  // deal — This method “deals” a card by removing a card from the deck and returning it, if there are any cards in the deck left to be dealt. It returns null if the deck is empty //
  /*
  public Card deal() {
    if(cards.size() > 0) { // if deck's size is not empty, then decrease variable size by one and return the element at size's index //
      Card newCard = cards.get(size);
      size--;
      return newCard;
    }
    else { // otherwise, return null //
      return null;
    }
  }
  */

  // Algorithm 2: It would be more efficient to leave the cards in the list. Instead of removing the card, simply decrement the size instance variable and then return the card at size. In this algorithm, the size instance variable does double duty; it determines which card to “deal” and it also represents how many cards in the deck are left to be dealt. This is the algorithm that you should implement.

  public Card getCard() { // removes a card from the deck(from the top of the deck(index at 0)) //
    return cards.remove(0);
  }

  public int varSize() { 
    return size; // checks the value of instance variable size(should decrease) //
  }

  // How to convert an ArrayList to String // https://www.geeksforgeeks.org/convert-an-arraylist-of-string-to-a-string-array-in-java/ 

  @Override
  public String toString(){  
    return Arrays.toString(cards.toArray()); // converting ArrayList of cards to string //
	}

}