package model;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import controller.Game;
import model.Deck.Color;
public class Sets{											// TODO separate view/ output text from class
	private static Set<Stack<Card>> colorSet;
	private static Stack<Card> greenStack;
	private static Stack<Card> redStack;
	private static Stack<Card> whiteStack;
	private static Stack<Card> blueStack;
	private static Stack<Card> yellowStack;
	
	// constructor
	public Sets(){
		colorSet = new HashSet<Stack<Card>>();				// initialize
		greenStack = new Stack<Card>();
		redStack = new Stack<Card>();
		whiteStack = new Stack<Card>();
		blueStack = new Stack<Card>();
		yellowStack = new Stack<Card>();
		
		greenStack.push(new Card(Color.GREEN, 0));	// setting up pile
		redStack.push(new Card(Color.RED,0));
		blueStack.push(new Card(Color.BLUE, 0));
		yellowStack.push(new Card(Color.YELLOW, 0));
		whiteStack.push(new Card(Color.WHITE, 0));
		
		colorSet.add(Sets.greenStack);						// setting up table
		colorSet.add(Sets.blueStack);
		colorSet.add(Sets.yellowStack);
		colorSet.add(Sets.whiteStack);
		colorSet.add(Sets.redStack);
	}
	
	public static String addToSet(Card c){
		int number = c.getValue();
		String s = "";
		switch (c.getColor()) {
		case WHITE:										// check to see if card is of set color
			if (topOfStack(whiteStack) + 1 == number){	// check top of white stack is one less than card being played
				whiteStack.push(c);						// add card to white stack
				if (number == 5){
					Game.addTokens();					// add token when finish set
					s += "Set completed! Adding token back. You have " + Game.getTokens() + " tokens. ";
				}
				s += "You matched white " + number + "!";
			} else {
				Game.reduceLives();						// lose a life if no card can be placed in a stack
				s += "No match! One life lost. You have " + Game.getNumberOfLives() + " lives.";
				DiscardPile.addToDiscard(c);
			}
			break;
		case BLUE:										// repeat for other colors
			if (topOfStack(blueStack) + 1 == number){	
				blueStack.push(c);						
				if (number == 5){
					Game.addTokens();					
					s += "Set completed! Adding token back. You have " + Game.getTokens() + " tokens. ";
					DiscardPile.addToDiscard(c);
				}
				s += "You matched blue " + number + "!";
			} else {
				Game.reduceLives();
				s += "No match! One life lost. You have " + Game.getNumberOfLives() + " lives.";
				DiscardPile.addToDiscard(c);
			}
			break;
		case YELLOW:									
			if (topOfStack(yellowStack) + 1 == number){
				yellowStack.push(c);
				if (number == 5){
					Game.addTokens();
					s += "Set completed! Adding token back. You have " + Game.getTokens() + " tokens. ";
				}
				s += "You matched yellow " + number + "!";
			} else {
				Game.reduceLives();	
				s += "No match! One life lost. You have " + Game.getNumberOfLives() + " lives.";
				DiscardPile.addToDiscard(c);
			}
			break;
		case RED:									
			if (topOfStack(redStack) + 1 == number){	
				redStack.push(c);
				if (number == 5){
					Game.addTokens();
					s += "Set completed! Adding token back. You have " + Game.getTokens() + " tokens. ";
				}
				s += "You matched red " + number + "!";
			} else {
				Game.reduceLives();	
				s += "No match! One life lost. You have " + Game.getNumberOfLives() + " lives.";
				DiscardPile.addToDiscard(c);
			}
			break;
		case GREEN:									
			if (topOfStack(greenStack) + 1 == number){	
				greenStack.push(c);
				if (number == 5){
					Game.addTokens();
					s += "Set completed! Adding token back. You have " + Game.getTokens() + " tokens. ";
				}
				s += "You matched green " + number + "!";
			} else {
				Game.reduceLives();	
				s += "No match! One life lost. You have " + Game.getNumberOfLives() + " lives.";
				DiscardPile.addToDiscard(c);
			}
			break;
		default:
			break;
		}
		return s;
	}
	
	private static int topOfStack(Stack<Card> aStackOfCards){
		return aStackOfCards.peek().getValue();			// allows us to look at the top of a stack
	}
	
	public static String topsOfAllStacks(){
		String s = "";
		for(Stack<Card> el : colorSet){
			s += "| " +  el.peek().printCardInfo() + " |";
		}
		return s;
	}
}
