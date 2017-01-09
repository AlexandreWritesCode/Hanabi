package cards;

import java.util.HashMap;
import java.util.Map;

public class DiscardPile{
	private static Map<Card, Integer> discards;
	public DiscardPile(){															// initialize as empty
		discards = new HashMap<Card, Integer>();
	}
	
	public static void addToDiscard(Card card) {
		if(discards.containsKey(card)){												// if the card is inside the discard pile
			discards.put(card, discards.get(card)+1);								// TODO: improve performance by switching
		} else {																	// new card for the discard pile
			discards.put(card, 0);
		}
	}
	
	public static String printDiscard(){
		String s = "";
		for(Map.Entry<Card, Integer> key: discards.entrySet()){
			s += key.getKey().printCardInfo() + " (" + key.getValue() + ")" + ", ";	// blue4 (1), 
		}
		return s;
	}
}
