package cards;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import cards.Card;

public class Deck{
	private static Stack <Card> shuffledDeck;
	// use deckSizeOfDeck getter below
	public enum Color {
		GREEN, YELLOW, RED, WHITE, BLUE
	}	
	
	// constructor
	public Deck(){
		shuffledDeck = new Stack<Card>();
		ArrayList<Card> unshuffledDeck = new ArrayList<Card>();					// first we create all cards
		for (int i = 0; i < 3; i++){											// ones
			unshuffledDeck.add(new Card(Color.WHITE,1));						// there are three of each color
			unshuffledDeck.add(new Card(Color.BLUE, 1));
			unshuffledDeck.add(new Card(Color.YELLOW, 1));
			unshuffledDeck.add(new Card(Color.GREEN, 1));
			unshuffledDeck.add(new Card(Color.RED, 1));
		}
		for (int i = 0; i < 2; i++){											// twos, threes and fours
			for (int number = 2; number < 5; number++){							// there are two of each color
				unshuffledDeck.add(new Card(Color.WHITE, number));
				unshuffledDeck.add(new Card(Color.BLUE, number));
				unshuffledDeck.add(new Card(Color.YELLOW, number));
				unshuffledDeck.add(new Card(Color.GREEN, number));
				unshuffledDeck.add(new Card(Color.RED, number));
			}
		}
		unshuffledDeck.add(new Card(Color.WHITE, 5));							// fives
		unshuffledDeck.add(new Card(Color.BLUE, 5));							// there is one of each color
		unshuffledDeck.add(new Card(Color.YELLOW, 5));
		unshuffledDeck.add(new Card(Color.GREEN, 5));
		unshuffledDeck.add(new Card(Color.RED, 5));
		
		int deckSize = unshuffledDeck.size();									// move cards into deck stack
		while(deckSize != 0){													
			Random rand = new Random();											// randomly to shuffle
			shuffledDeck.push(unshuffledDeck.remove(rand.nextInt(deckSize)));	// random number from 0 to 40--
			deckSize = unshuffledDeck.size();
		}																		// shuffled deck created
	}
	
	public static Card drawCard(){												// draw card from deck
		return shuffledDeck.pop();
	}
	
	public static int getSizeOfDeck(){											// size of deck
		return shuffledDeck.size();
	}
}
