package sushigo.domain;

import java.util.List;

public interface ISushiGo {

    public enum Winner {
        NO_ONE,
        PLAYER_1,
        PLAYER_2,
        DRAW
    }

    public enum CardType {
        egg,
        salmon,
        squid,
        dumpling,
        makiRol,
        sashimi,
        tempura,
        pudding
    }

    /**
     * Method returning the first player
     * 
     * @return player 1.
     */
    Player getPlayerOne();

    /**
     * Method returning the name of the first player
     * 
     * @return The name of the first player.
     */
    String getNameOfPlayerOne();

    /**
     * Method returning the name of the opponent
     * 
     * @return The name of the first player.
     */
    String getNameOfPlayerTwo();

    /**
     * Method to get the player from a name.
     * 
     * @return The player that matches the name.
     */
    Player getPlayerWithName(String name);

    /**
     * Method indicating if the first player has the next turn or not.
     * 
     * @param name The player which you want to know the turn for.
     * @return True if the player has the turn. False if it's the turn of the other
     *         player.
     */
    boolean isPlayersTurn(String name);

    /**
     * 
     * @param index the index of the card that will be played from the hand of
     *              player.
     */
    void playCard(int index, String owner);

    /**
     * 
     * @param name
     * @return a list of all the card in the hand of the player
     */
    List<Card> getHandOfCards(String name);

    /**
     * 
     * @param name
     * @return a list of all the card that are played by the player and are on the
     *         board
     */
    List<Card> getCardsOnBoard(String name);

    /**
     * 
     * @param name
     * @param cardType
     * @return The number of cards correspoding to the given card type that have
     *         currently been played in
     *         this round
     */
    int getNumberOfSpecificCards(String name, CardType cardType);

    /**
     * 
     * @param name
     * @return the score calculated from the cards that were played by a player
     */

    int getScore(String name);

    /**
     * 
     * @param name
     * @return the nr of puddingCards played during the whole game by a player with
     *         name.
     */

    int getPuddingCount(String name);

    /**
     * Method for retrieving whether the game has ended or not.
     *
     * @return True if the game has ended otherwise False.
     */
    boolean isEndOfGame();

    /**
     * Method for retrieving the player that has won the game.
     *
     * @return Enum value representing which player(s) (if any) won the game.
     *         0 : No Winner
     *         1 : Player 1
     *         2 : Player 2
     *         3 : Draw
     */
    Winner getWinner();
}
