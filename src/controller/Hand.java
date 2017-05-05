package controller;

import java.util.ArrayList;import java.util.List;
import java.util.Scanner;

import model.Card;
import model.Deck;
import model.DiscardPile;
import model.Sets;

public class Hand{
	private List<Card> hand;										// player hand
	private String name;
	private int id;
	private static List<Hand> allPlayers = new ArrayList<Hand>();
	private boolean turn;
																	// constructor
	public Hand(int number) {
		this.hand = new ArrayList<Card>();
		int handCount = 5; 											// two or three player game, five cards
		if (Game.getNumberOfPlayers() > 3){
			handCount--;											// four or five player game, four cards
		}
		for(int i = 0; i < handCount; i++){ 						// draw cards into hand
			this.hand.add(Deck.drawCard());
		}
		this.id = number;											// set player id
		allPlayers.add(this);										// add players to our list of all players
		this.turn = false;											// not player's turn
	}
																	// player actions on a given turn

	protected void giveInformation(Hand player, int position, Scanner read){
		if (!this.equals(player)){									// enforce player cannot be self
			if(Game.getTokens() > 0){								// only if tokens available
				Game.removeTokens(); 								// remove token
				// give information
				Card c = player.hand.get(position);					// specify card to examine
				System.out.print("Specify cards in " + player.name + "'s hand by:\n"
						+ "0. Color\n"
						+ "1. Value\n"
						+ "Choose: ");
				int i = Integer.parseInt(read.nextLine());
				switch(i){
				case 0: 											// color(0)
					for(Card allCards : player.hand){				// iterate through hand
						if(c.getColor().equals(allCards.getColor())){
							allCards.setKnowsColor(true);			// for all cards sharing specified card's color
						}											// and toggle whether user knows color
					}
					break;
				case 1: 											// value(1)
					for(Card allCards : player.hand){
						if(c.getValue() == allCards.getValue()){	// for all cards sharing specified card's value
							allCards.setKnowsValue(true);			// toggle value for those cards
							c.cardInfo(this, player);
						}
					}
					break;
				default: 											// TODO: assuming user is not malicious
					break;
				}
				player.viewPlayersHand(player);						// print out player's hand with information
			}
		}
	}

	protected void discard(int replace){
		Card tossOut = this.removeCardFromHand(replace);			// remove card from hand
		DiscardPile.addToDiscard(tossOut);							// move card to discard pile
		System.out.println("You removed a " + tossOut.printCardInfo() + ".");
		this.addCardToHand(Deck.drawCard());						// draw new card
		if(Game.getTokens() < 8){									// add token
			Game.addTokens();
			System.out.print("Adding token back. You have " + Game.getTokens() + " tokens. ");
		}
	}
	
	protected String playCard(int replace){
		String s = Sets.addToSet(this.removeCardFromHand(replace));	// move card to color set from hand
		this.addCardToHand(Deck.drawCard());						// draw new card
		return s;
	}
	
	protected void viewPlayersHand(Hand player){						// prints out hand
		int i = 0;
		System.out.println("Player " + player.name + " card's: ");
		for (Card c : player.hand){									// example: 'Card 1. blue4'
			System.out.println("Card " + (i++) + ". " + c.cardInfo(this, player));
		}
	}
	
	protected String inspectCard(Hand player, int position){				// look at a specific player's card
		return player.hand.get(position).cardInfo(this, player);
	}

	protected void setPlayerName(String s){
		this.name = s;
	}

	protected String getPlayerName(){
		return this.name;
	}
	
	private Card removeCardFromHand(int position){
		return this.hand.remove(position);							// remove card in position of hand
	}
	
	private void addCardToHand(Card card){							// append card to hand
		this.hand.add(card);
	}

	protected static Hand getPlayer(int i){							// find player with id
		for (Hand e : allPlayers){
			if (e.id == i){
				return e;
			}
		}
		return null;
	}

	protected boolean isTurn() {
		return turn;
	}

	protected void toggleTurn() {
		this.turn = !this.turn;
	}

	protected static void printPlayers(){
		for(Hand e : allPlayers){
			System.out.println(e.id + ". " + e.name);				// example 1. Alexandre
		}
	}
}
