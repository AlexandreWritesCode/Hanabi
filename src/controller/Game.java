package controller;

import java.util.Scanner;

import model.Deck;
import model.DiscardPile;
import model.Sets;

public class Game {
	private static int tokens;
	private static int lives;
	private static int numberOfPlayers;
	
	public Game(){
		 tokens = 8;
		 lives = 4;
		 new Sets();
		 new Deck();														// build deck of cards
		 new DiscardPile();
	}
	
	public static int getTokens(){
		return tokens;
	}
	
	public static void addTokens(){
		tokens++;
	}
	
	protected static void removeTokens(){
		tokens--;
	}
	
	private static void setNumberOfPlayers(int number){
		numberOfPlayers = number;
	}
	
	protected static int getNumberOfPlayers(){
		return numberOfPlayers;
	}
	
	public static void reduceLives(){
		lives--;
	}
	
	public static int getNumberOfLives(){
		return lives;
	}
	
	public static void main(String args[]){
			new Game();
			System.out.print("Number of players: ");
			Scanner read = new Scanner(System.in);
			int numberOfPlayers = Integer.parseInt(read.nextLine());
			setNumberOfPlayers(numberOfPlayers);
			for (int i = 0; i < numberOfPlayers; i++){
				System.out.print("Enter player name: ");					// creates hands for number of players
				Hand player = new Hand(i);									// assign player id
				player.setPlayerName(read.nextLine());						// assign player name
			}
			int i = 0;
			while(Deck.getSizeOfDeck() > 0 && lives > 0){					// keep playing
				possiblePlayerActions(read, Hand.getPlayer(i)); 			// player's turn, gets current player's id
				i++;
				if(i >= numberOfPlayers){									// next player turn
					i = 0;													// loop back around
				}
			}
			while(lives > 0){												// play out final turns, deck empty but still alive
				for(i = 0 ; i < numberOfPlayers; i++){
					possiblePlayerActions(read, Hand.getPlayer(i));
				}
			}
			read.close();
	}
	
	private static void possiblePlayerActions(Scanner read, Hand player){
		player.toggleTurn();												// toggle player's turn on
		while(player.isTurn()){												// is player's turn
			System.out.print("---" + player.getPlayerName() + "'s turn---\n"
					+ "0. Give information about another player's hand\n"
					+ "1. Discard card from your hand\n"
					+ "2. Play card from your hand\n"
					+ "3. Look at a hand\n"
					+ "4. Inspect card\n"
					+ "5. Look at table\n"
					+ "6. Check discards\n"
					+ "7. Get deck count\n"
					+ "8. Get lives\n"
					+ "9. Get tokens\n"
					+ "Select: ");
			int x = Integer.parseInt(read.nextLine());
			int playerId;
			int selectedValue;
			Hand selectedPlayer;
			switch(x){														// user actions on a turn
			case 0: 														// give information about another player's hand
				Hand.printPlayers();
				System.out.print("Select player: ");						// display all players
				playerId = Integer.parseInt(read.nextLine());
				selectedPlayer = Hand.getPlayer(playerId);					// select player
				player.viewPlayersHand(selectedPlayer);						// display player's cards
				System.out.print("Select card: ");							// select card from player
				selectedValue = Integer.parseInt(read.nextLine());
				player.giveInformation(selectedPlayer, selectedValue, read);
				player.toggleTurn();										// ends turn
				System.out.println();										// just to make it look nicer
				break;
			case 1: 														// discard card from your hand
				player.viewPlayersHand(player);								// see cards in hand to pick from
				System.out.print("Select card to discard: ");
				player.discard(Integer.parseInt(read.nextLine()));			// discard whatever player selects
				player.viewPlayersHand(player);								// see new card drawn
				player.toggleTurn();
				System.out.println();
				break;
			case 2: 														// play card from your hand
				player.viewPlayersHand(player);								// see cards in hand to pick from
				System.out.print("Choose a card: ");
				System.out.println(player.playCard(Integer.parseInt(read.nextLine())));
				player.viewPlayersHand(player);								// see new card drawn
				player.toggleTurn();
				System.out.println();
				break;
			case 3: 														// look at a hand
				Hand.printPlayers();										// print all players
				System.out.print("Choose a person's hand: ");		
				playerId = Integer.parseInt(read.nextLine());
				player.viewPlayersHand(Hand.getPlayer(playerId));
				System.out.println();
				break;
			case 4: 														// inspect card
				Hand.printPlayers();
				System.out.print("Choose a person's hand: ");				
				playerId = Integer.parseInt(read.nextLine());
				selectedPlayer = Hand.getPlayer(playerId);					// select player
				player.viewPlayersHand(selectedPlayer);						// display player's cards
				System.out.print("Select card: ");				
				selectedValue = Integer.parseInt(read.nextLine());
				System.out.println("Selected card is a " + player.inspectCard(Hand.getPlayer(playerId), selectedValue));
				System.out.println();
				break;
			case 5: System.out.println(Sets.topsOfAllStacks() + "\n");		// look at table
				break;
			case 6:	System.out.println(DiscardPile.printDiscard() + "\n");	// check discards
				break;
			case 7: System.out.println(Deck.getSizeOfDeck() + "\n");		// deck count
				break;
			case 8: System.out.println(lives + "\n");						// number of lives
				break;
			case 9: System.out.println(tokens + "\n");						// number of tokens
				break;
			default:														// TODO handle default
				break;
			}
		}
	}
}
