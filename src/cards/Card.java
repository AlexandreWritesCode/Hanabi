package cards;

import cards.Deck.Color;

public class Card{
	private Color color;										// cards have a unique color: RED, BLUE, GREEN, YELLOW, WHITE
	private int value;											// cards have a unique number: 1, 2, 3, 4, 5
	private boolean knowsColor;
	private boolean knowsValue;
	public Card(Color c, int number){
		this.color = c;
		this.value = number;
		this.knowsColor = false;
		this.knowsValue = false;
	}
	
	public String printCardInfo(){								// easiest method for printing cards
		return this.printColor() + this.getValue();
	}
	
	public String cardInfo(Hand activePlayer, Hand receiver){	// user looking at other users
		if (activePlayer.equals(receiver)){						// only give active user information attained
			String s = "";
			if(this.isColorKnown()){
				s = this.printColor();
			}
			if(this.isValueKnown()){
				s += this.getValue();
			}
			return s;
		} else {												// active user knows everyone else's cards
			return this.printColor() + this.getValue();
		}
	}
	
	public String printColor(){
		Color c = this.getColor();
		String s;
		switch (c) {
		case WHITE: s = "white";
			break;
		case RED: s = "red";
			break;
		case BLUE: s = "blue";
			break;
		case YELLOW: s = "yellow";
			break;
		case GREEN: s = "green";
			break;
		default: s = "";										// do nothing
			break;
		}
		return s;
	}

	public Color getColor() {
		return this.color;
	}

	public int getValue() {
		return value;
	}

	public boolean isColorKnown() {
		return knowsColor;
	}

	public void setKnowsColor(boolean knowsColor) {
		this.knowsColor = knowsColor;
	}

	public boolean isValueKnown() {
		return knowsValue;
	}

	public void setKnowsValue(boolean knowsValue) {
		this.knowsValue = knowsValue;
	}
}
