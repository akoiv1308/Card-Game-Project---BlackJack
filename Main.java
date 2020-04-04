import java.util.*; //importing library //

/* The following is a quick explanation of the card game BlackJack - https://www.youtube.com/watch?v=eyoh-Ku9TCI (Video) as well as https://bicyclecards.com/how-to-play/blackjack/ (Informational Website). This code resembles a modified version of BlackJack in which there are no chips/money. The game is simply constructed on the randomness of an opponent's(the coded methods/function) hand and player's/user's hand */

public class Main {
  
  public static void main(String[] args){
    Scanner input = new Scanner(System.in); // scanner input //
    System.out.println("Hey! Welcome to BlackJack");
    System.out.print("What's your name? : ");
    String name = input.nextLine();
    System.out.println("Ok, " + name + ", let's begin!");
    System.out.println("How many times would you like to play? : ");
    int games = input.nextInt(); // asking number of games for while loop //
    int numOfgames = games; // setting numOfgames to games to keep track of games //
    int win = 0; // number of wins //
    boolean run = true;
    while(games > 0){ // while there're still games to be played //
      String[] ranks = {"Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King","Ace"};
      String[] suits = {"Clubs", "Hearts", "Spades", "Diamonds"};
      int[] values = {2,3,4,5,6,7,8,9,10,11,12,13,14};
      Deck deck = new Deck(ranks, suits, values);// initializing deck // 
      deck.shuffle(); // randomly shuffling the deck of cards //
      int numOfAces = 0; // variable for number of aces //
      Opponent opponent = new Opponent(deck); // creating an Opponent Object to be an opponent for the user //
      System.out.println("\nOpponent's First Card: ");
      opponent.firstCard();
      ArrayList<Card> hand = new ArrayList<Card>(); // initializing an ArrayList to store cards of a player //
      for(int i = 0; i < 2; i++) {
        hand.add(deck.getCard());
      }
      System.out.println("\nYour Hand: " + hand);
      int handValue = determineCurrentValue(hand, numOfAces); // variable for determining the value of the card //
      if(handValue == 21 && opponent.hasBlackJack()) { //checking whether both, the user and opponent, have a blackjack already //
        System.out.println("\nIt's a tie!");
        drawLine();
      }
      else if(handValue == 21) { // checking if the user has blackjack //
        System.out.println("You have BlackJack :) ");
        System.out.println("\nCongratulations, you won!");
        drawLine();
        win++;
        break;
      }
      else if(opponent.hasBlackJack()) { // checking if the dealer has blackjack //
        System.out.println("Opponent has a BlackJack :( ");
        opponent.showHand();
        System.out.println("\nSorry, you lost!");
        drawLine();
      }
      else {
        Scanner choice = new Scanner(System.in);
        System.out.println("\nHit or stand? (hit/stand)"); // asking whether wants to hit or stand //
        String hitOrStand = choice.nextLine();
        while(hitOrStand.equalsIgnoreCase("hit")) { 
          hand.add(deck.getCard());
          determineCurrentValue(hand, numOfAces);
          System.out.println("\nYour hand is now: " + hand);
          handValue = determineCurrentValue(hand, numOfAces);
          if(handValue > 21) { //checks if the user has over 21 //
            System.out.println("\nSorry, you lost!");
            drawLine();
          }
          if(handValue == 21) {
            System.out.println("\nCongratulations, you won!");
            drawLine();
            win++;
          }
          if(hand.size() == 5 && handValue <= 21) { // checks for a five card trick //
            System.out.println("\nYou have five cards!");
            /* Website explaining what 5 card trick is: https://www.spinzwin.com/blackjack-5-card-trick-provide-edge-players/ */
            System.out.println("\nCongratulations, you won!");
            drawLine();
            win++;
            break;
          }
          System.out.println("\nHit or stand? (hit/stand) ");
          hitOrStand = choice.nextLine();
        }
        if(hitOrStand.equalsIgnoreCase("stand")) { // users choice is stand //
          int opponentHand = opponent.opponentsTurn(deck);// random turn of an opponent
          System.out.println("\nThe opponent's hand was:" + opponent.showHand());
          if(opponentHand > 21) { //if the opponent has a value greater than 21, user wins //
            System.out.println("\nCongratulations, you won!");
            drawLine();
            win++;
          }
          else {
            int userResult = 21 - handValue;//check who is closer to 21 and determine winner
            int opponentResult = 21 - opponentHand;
            if(userResult == opponentResult) {
              System.out.println("\nIt's a tie!");
              drawLine();
            }
            if(userResult < opponentResult) {
              System.out.println("\nCongratulations, you won!");
              drawLine();
              win++;
            }
            if(opponentResult < userResult) {
              System.out.println("\nSorry, you lost!");
              drawLine();
            }
          }
        }
      }
      games--; // decreasing game variable for while loop //
      if(games == 0) {  // if statement for whether the person wants to continue playing or not //
        System.out.println("You have played " + numOfgames + " game(s) and have won " + win + " games!");
        System.out.println("Would you like to play again? (y/n)"); // asking whether user wants to contrinue to play //
        Scanner continueGame = new Scanner(System.in);
        String response = continueGame.nextLine();
        if(response.equalsIgnoreCase("n") || response.equalsIgnoreCase("no")) {
          System.out.println("Thanks for playing, bye!");
        }
        else {
          System.out.println("How many times would you like to play? : ");
          games = input.nextInt();
          numOfgames = games;
          win = 0;
        }
      }
    }
  }

  // I used this information to determine the value of hand with aces - https://stackoverflow.com/questions/837951/is-there-an-elegant-way-to-deal-with-the-ace-in-blackjack //

  public static int determineCurrentValue(ArrayList<Card> hand, int numOfAces) { // calculate the value of a player's hand //
    int handValue = 0; // used to calculate the (int) value of hand //
    for(Card card : hand) { // for every card in a hand //
      handValue += card.getValue(); // add (int) value it to handValue //
      if(card.getValue() == 11) { // if the value added is 11, then the card must be Ace //
        numOfAces++; // increase the number of Aces, so that the next statement(while loop) could be executed //
      }
      while (numOfAces > 0 && handValue > 21) { // if there is more than one Ace in hand and the total value of hand the player has exceeds 21, then change Ace value to 1 and decrease the number of Aces //
        handValue -= 10;
        numOfAces--;
      }
    }
    return handValue; // returning a point value of hand //
  }

  public static void drawLine() { // used to separate each game //
    System.out.println("\n_________________________________________________");
  }

}