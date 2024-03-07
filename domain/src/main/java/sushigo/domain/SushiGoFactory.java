package sushigo.domain;

public class SushiGoFactory implements ISushiGoFactory {

    @Override
    public ISushiGo createNewGame(String namePlayerOne, String namePlayerTwo) {
        return new SushiGo(namePlayerOne, namePlayerTwo);
    }

}
