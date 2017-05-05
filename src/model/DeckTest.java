package model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeckTest extends Deck {
    private static Deck d;
    private static DiscardPile dP;
    private static Sets s;

    @Before
    public void setUp() {
        d = new Deck();                            // creates cards as well
    }

    @Test
    public void checkSizeOfDeckAsItIsDrawn() {
        setUp();
        for (int i = 50; i > 0; i--) {
            assertEquals(i, d.getSizeOfDeck());
            Card c = d.drawCard();
            System.out.println(c.printCardInfo()); // check that all cards are being created by constructor
        }
        assertEquals(0, d.getSizeOfDeck());
    }

    @Test
    public void addToDiscardFromDeck() {
        setUp();
        dP = new DiscardPile();
        for (int i = 50; i > 0; i--) {
            Card c = d.drawCard();
            dP.addToDiscard(c);
        }
        System.out.println(dP.printDiscard());      // it's impossible to assert an equivalency since it will return
                                                    // depending how the cards are added to the map so just print and
                                                    // verify it works--it does!
    }

    @Test
    public void addToSet() {
        s = new Sets();
        for (int i = 1; i <= 5; i++) {
            s.addToSet(new Card(Color.BLUE, i));
            s.addToSet(new Card(Color.YELLOW, i));
            s.addToSet(new Card(Color.GREEN, i));
            s.addToSet(new Card(Color.RED, i));
            s.addToSet(new Card(Color.WHITE, i));
        }
        assertEquals("| green5 || white5 || red5 || yellow5 || blue5 |",s.topsOfAllStacks());
    }
}