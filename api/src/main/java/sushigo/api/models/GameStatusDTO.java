package sushigo.api.models;

import sushigo.domain.ISushiGo;

public class GameStatusDTO {

    public boolean endOfGame;
    public String winner;

    public GameStatusDTO(ISushiGo sushigo) {
        this.endOfGame = sushigo.isEndOfGame();
        switch (sushigo.getWinner()) {
            case PLAYER_1:
                this.winner = sushigo.getNameOfPlayerOne();
                break;
            case PLAYER_2:
                this.winner = sushigo.getNameOfPlayerTwo();
                break;
            case DRAW:
                this.winner = sushigo.getNameOfPlayerOne()
                        + " and "
                        + sushigo.getNameOfPlayerTwo();
                break;
            default:
                this.winner = null;
        }
    }

    public boolean getEndOfGame() {
        return endOfGame;
    }

    public String getWinner() {
        return winner;
    }
}
