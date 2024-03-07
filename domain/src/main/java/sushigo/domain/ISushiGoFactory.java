
package sushigo.domain;

public interface ISushiGoFactory {
    /**
     * Method to create a new Mancala game
     * 
     * @param namePlayer1 The name of the first player
     * 
     * @param namePlayer2 The name of the second player
     * 
     * @return A new Mancala game
     */
    ISushiGo createNewGame(String namePlayer1, String namePlayer2);

}
