## Description
Backend implementation of the cooperative boardgame Hanabi in Java, therefore playable on the console. This is more to demonstrate my ability to write well designed code.

## Lessons Learned
 - Refreshing of Object Oriented Programming concepts
 - Fun work with Java and Eclipse
 - Using Git/hub with Eclipse's plugins
 
## Hanabi
Rules explained here: https://en.wikipedia.org/wiki/Hanabi_(card_game)

## Features (and Design Challenges)
- Players are able to see all other player's hands except their own
- Players can give total information about other player's cards--that is, in telling a card's color, you must inform all cards in the player's hand of that same color--and in implementing the game, a player should be able to retreive known information
- Players play cards in their hand to a stack on the table which must being one greater than the current card on the table of that color
- Class implementation of a Card, Deck, Discard, Sets (table) and Hand
- Finite number of lives and hints which reduce and increase given player's moves
