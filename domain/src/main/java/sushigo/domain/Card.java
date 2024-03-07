package sushigo.domain;

import sushigo.domain.ISushiGo.CardType;

public class Card {
    private Player owner;
    private CardType cardType;

    public Card(Player owner, CardType cardType) {
        this.owner = owner;
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return this.cardType;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void changeOwner(Player owner) {
        this.owner = owner;
    }

    public boolean cardCanBePlayed() {
        return this.owner.hasTurn();
    }

    public void chooseCard() {
        if (!this.cardCanBePlayed()) {
            throw new IllegalStateException("Card cannot be played");
        }
        this.playCard();
    }

    public void playCard() {
        this.checkIfPuddingAndUpdatePuddingCount();
        this.owner.removeCardFromHand(this);
        this.owner.addCardToBoard(this);
        this.owner.switchTurn();
    }

    public void playCardsAndSwitchHand() {
        Player player = this.getOwner();
        int countCurrentPlayer = this.getOwner().getCount();
        int countOpponent = this.getOwner().getOpponent().getCount();
        countCurrentPlayer = countCurrentPlayer + 1;
        player.updateCount(countCurrentPlayer);
        if (checkIfPlayersShouldSwitchHand(countCurrentPlayer, countOpponent)) {
            this.chooseCard();
            player.switchHandOfCardsAfterTurn();
        } else if (checkIfItIsEndOfRoundOneOrTwoAfterThisTurn(countCurrentPlayer, countOpponent)) {
            this.chooseCard();
            player.handleEndOfRoundOneAndTwo();
        } else if (countCurrentPlayer == countOpponent
                && this.checkIfRoundIsOverAfterThisTurn()
                && this.checkIfGameIsOverAfterThisTurn(player.getRoundCount())) {
            this.chooseCard();
            player.calculatAndUpdateScoreAtEndOfRoundForBothPlayers();
        } else if (countCurrentPlayer != countOpponent) {
            this.chooseCard();
        }
    }

    public void checkIfPuddingAndUpdatePuddingCount() {
        if (this.getCardType() == CardType.pudding) {
            this.getOwner().increasePuddingCountByOne();
        }
    }

    public boolean checkIfPlayersShouldSwitchHand(int countCurrentPlayer, int countOpponent) {
        if (countCurrentPlayer == countOpponent && !this.checkIfRoundIsOverAfterThisTurn()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfItIsEndOfRoundOneOrTwoAfterThisTurn(int countCurrentPlayer, int countOpponent) {
        if (countCurrentPlayer == countOpponent && this.checkIfRoundIsOverAfterThisTurn()
                && !this.checkIfGameIsOverAfterThisTurn(this.getOwner().getRoundCount())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfRoundIsOverAfterThisTurn() {
        Player player = this.getOwner();
        if (player.getCardsInHand().size() == 1 && player.getOpponent().getCardsInHand().size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfGameIsOverAfterThisTurn(int roundCount) {
        if (roundCount == 2 && this.checkIfRoundIsOverAfterThisTurn()) {
            return true;
        } else {
            return false;
        }
    }

}
