package sushigo.api.models;

import sushigo.domain.ISushiGo;

public class SushiGoDTO {
    private PlayerDTO[] players;
    private GameStatusDTO gameStatus;

    public SushiGoDTO(ISushiGo sushiGo) {
        players = new PlayerDTO[2];

        players[0] = new PlayerDTO(sushiGo, sushiGo.getNameOfPlayerOne());
        players[1] = new PlayerDTO(sushiGo, sushiGo.getNameOfPlayerTwo());

        gameStatus = new GameStatusDTO(sushiGo);
    }

    public PlayerDTO[] getPlayers() {
        return players;
    }

    public void setPlayers(PlayerDTO[] players) {
        this.players = players;
    }

    public GameStatusDTO getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatusDTO gameStatus) {
        this.gameStatus = gameStatus;
    }

}
