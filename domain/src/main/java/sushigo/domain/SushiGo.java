package sushigo.domain;

import java.util.List;

public class SushiGo implements ISushiGo {
    private String namePlayerOne;
    private String namePlayerTwo;
    private Player player;

    public SushiGo(String namePlayerOne, String namePlayerTwo) {
        this.player = new Player();
        this.namePlayerOne = namePlayerOne;
        this.namePlayerTwo = namePlayerTwo;
    }

    @Override
    public Player getPlayerOne() {
        return player;
    }

    @Override
    public String getNameOfPlayerOne() {
        return namePlayerOne;
    }

    @Override
    public String getNameOfPlayerTwo() {
        return namePlayerTwo;
    }

    @Override
    public Player getPlayerWithName(String name) {
        if (this.getNameOfPlayerOne().equals(name)) {
            Player playerWithName = this.player;
            return playerWithName;
        } else if (this.getNameOfPlayerTwo().equals(name)) {
            Player playerWithName = this.player.getOpponent();
            return playerWithName;
        } else {
            throw new IllegalArgumentException("invalid player name");
        }
    }

    @Override
    public boolean isPlayersTurn(String name) {
        if (name.equals(namePlayerOne)) {
            return player.hasTurn();
        } else if (name.equals(namePlayerTwo)) {
            return player.getOpponent().hasTurn();
        } else {
            throw new IllegalArgumentException("invalid player turn");
        }
    }

    @Override
    public void playCard(int index, String owner) {
        Card chosenCard;
        if (this.getPlayerWithName(owner).hasTurn()
                && this.getNameOfPlayerOne().equals(owner)) {
            chosenCard = this.player.getCardInHandAtIndex(index);
        } else if (this.getPlayerWithName(owner).hasTurn() && this.getNameOfPlayerTwo().equals(owner)) {
            chosenCard = this.player.getOpponent().getCardInHandAtIndex(index);
        } else {
            throw new IllegalArgumentException("chosen card is invalid");
        }
        chosenCard.playCardsAndSwitchHand();
    }

    @Override
    public List<Card> getHandOfCards(String name) {
        if (name.equals(namePlayerOne)) {
            return player.getCardsInHand();
        } else if (name.equals(namePlayerTwo)) {
            return player.getOpponent().getCardsInHand();
        } else {
            throw new IllegalArgumentException("invalid hand of cards");
        }

    }

    @Override
    public List<Card> getCardsOnBoard(String name) {
        if (name.equals(namePlayerOne)) {
            return player.getCardsOnBoard();
        } else if (name.equals(namePlayerTwo)) {
            return player.getOpponent().getCardsOnBoard();
        } else {
            throw new IllegalArgumentException("invalid hand of cards");
        }

    }

    @Override
    public int getScore(String name) {
        if (name.equals(namePlayerOne)) {
            return this.getPlayerOne().getScore();
        } else if (name.equals(namePlayerTwo)) {
            return this.getPlayerOne().getOpponent().getScore();
        } else {
            throw new IllegalArgumentException("invalid player name");
        }
    }

    @Override
    public boolean isEndOfGame() {
        if (player.getCardsInHand().size() == 0
                && player.getOpponent().getCardsInHand().size() == 0
                && player.getRoundCount() == 2) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Winner getWinner() {
        if (this.isEndOfGame()) {
            int scorePlayerOne = this.getScore(namePlayerOne);
            int scorePlayerTwo = this.getScore(namePlayerTwo);
            if (scorePlayerOne > scorePlayerTwo) {
                return Winner.PLAYER_1;
            } else if (scorePlayerOne < scorePlayerTwo) {
                return Winner.PLAYER_2;
            } else if (scorePlayerOne == scorePlayerTwo) {
                return Winner.DRAW;
            } else {
                return Winner.NO_ONE;
            }
        } else {
            return Winner.NO_ONE;
        }
    }

    @Override
    public int getNumberOfSpecificCards(String name, CardType cardType) {
        int count = this.getPlayerWithName(name).countNumberOfSpecificCardsPlayed(cardType);
        return count;
    }

    @Override
    public int getPuddingCount(String name) {
        if (name.equals(namePlayerOne)) {
            return this.getPlayerOne().getPuddingCount();
        } else if (name.equals(namePlayerTwo)) {
            return this.getPlayerOne().getOpponent().getPuddingCount();
        } else {
            throw new IllegalArgumentException("invalid player name");
        }
    }

}
