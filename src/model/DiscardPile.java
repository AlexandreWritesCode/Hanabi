package model;

import java.util.HashMap;
import java.util.Map;

public class DiscardPile{
	private static Map<String, Integer> discards;
	public DiscardPile(){															// initialize as empty
		discards = new HashMap<String, Integer>();
	}
	
	public static void addToDiscard(Card card) {
		if(discards.containsKey(card.printCardInfo())){								// if the card is inside the discard pile
			discards.put(card.printCardInfo(), discards.get(card.printCardInfo())+1);
		} else {																	// new card for the discard pile
			discards.put(card.printCardInfo(), 1);
		}
	}
	
	public static String printDiscard(){
		String s = "";
		for(Map.Entry<String, Integer> key: discards.entrySet()){
			s += key.getKey() + " (" + key.getValue() + ")" + ", ";					// blue4 (1),
		}
		return s;
	}
}
