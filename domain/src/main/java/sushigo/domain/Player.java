package sushigo.domain;

import java.util.List;
import java.util.Random;

import sushigo.domain.ISushiGo.CardType;

import java.util.ArrayList;

public class Player {
    private Player opponent;
    private boolean hasTurn;
    private List<Card> cardsInHand;
    private List<Card> cardsOnBoard;
    private int nrOfCardsPlayedCount;
    private int nrOfRoundsPlayedCount;
    private int score;
    private int puddingCount;

    public Player() {
        this.hasTurn = true;
        this.opponent = new Player(this);

        this.cardsInHand = new ArrayList<Card>();
        this.opponent.cardsInHand = new ArrayList<Card>();

        this.cardsOnBoard = new ArrayList<Card>();
        this.opponent.cardsOnBoard = new ArrayList<Card>();

        this.drawTenCardsAndGiveToPlayerHand();
        this.opponent.drawTenCardsAndGiveToPlayerHand();

        this.nrOfCardsPlayedCount = 0;
        this.opponent.nrOfCardsPlayedCount = 0;

        this.nrOfRoundsPlayedCount = 0;
        this.opponent.nrOfRoundsPlayedCount = 0;

        this.score = 0;
        this.opponent.score = 0;

        this.puddingCount = 0;
        this.opponent.puddingCount = 0;
    }

    private Player(Player opponent) {
        this.hasTurn = false;
        this.opponent = opponent;
    }

    public boolean hasTurn() {
        return this.hasTurn;
    }

    public Player getOpponent() {
        return this.opponent;
    }

    public List<Card> getCardsInHand() {
        return this.cardsInHand;
    }

    public List<Card> getCardsOnBoard() {
        return this.cardsOnBoard;
    }

    public int getCount() {
        return this.nrOfCardsPlayedCount;
    }

    public void updateCount(int updatedCount) {
        this.nrOfCardsPlayedCount = updatedCount;
    }

    public int getRoundCount() {
        return this.nrOfRoundsPlayedCount;
    }

    public void updateRoundCountWithOne() {
        this.nrOfRoundsPlayedCount++;
    }

    public void updateRoundCountForBothPlayers() {
        this.updateRoundCountWithOne();
        this.getOpponent().updateRoundCountWithOne();
    }

    public int getScore() {
        return this.score;
    }

    public void updateScoreAtEndOfRound() {
        this.score = score + this.calculateScore();
    }

    public void calculatAndUpdateScoreAtEndOfRoundForBothPlayers() {
        this.updateScoreAtEndOfRound();
        this.getOpponent().updateScoreAtEndOfRound();
    }

    public int getPuddingCount() {
        return this.puddingCount;
    }

    public void increasePuddingCountByOne() {
        this.puddingCount++;
    }

    public Card getCardInHandAtIndex(int index) {
        return this.cardsInHand.get(index);
    }

    public Card getCardOnBoardAtIndex(int index) {
        return this.cardsOnBoard.get(index);
    }

    public void changeOwnerOfAllCardsInHandToSelf() {
        for (int i = 0; i < this.getCardsInHand().size(); i++) {
            this.getCardInHandAtIndex(i).changeOwner(this);
        }
    }

    public void switchTurn() {
        this.switchTurnAlone();
        this.opponent.switchTurnAlone();
    }

    private void switchTurnAlone() {
        this.hasTurn = !this.hasTurn;
    }

    public int generateRandomNumberBetweenZeroAndThree() {
        Random random = new Random();
        int randomInt = random.nextInt(9);
        return randomInt;
    }

    public Card drawRandomCard() {
        int randomInt = this.generateRandomNumberBetweenZeroAndThree();
        Card randomCard;
        switch (randomInt) {
            case 0:
                randomCard = new Card(this, CardType.egg);
                break;
            case 1:
                randomCard = new Card(this, CardType.salmon);
                break;
            case 2:
                randomCard = new Card(this, CardType.squid);
                break;
            case 3:
                randomCard = new Card(this, CardType.dumpling);
                break;
            case 4:
                randomCard = new Card(this, CardType.makiRol);
                break;
            case 5:
                randomCard = new Card(this, CardType.makiRol);
                break;
            case 6:
                randomCard = new Card(this, CardType.sashimi);
                break;
            case 7:
                randomCard = new Card(this, CardType.tempura);
                break;
            case 8:
                randomCard = new Card(this, CardType.pudding);
                break;
            default:
                throw new IllegalArgumentException("invalid hand of cards");
        }
        return randomCard;
    }

    public void drawTenCardsAndGiveToPlayerHand() {
        for (int i = 0; i < 10; i++) {
            Card drawnCard = this.drawRandomCard();
            this.cardsInHand.add(drawnCard);
        }
    }

    public void switchHandOfCardsAfterTurn() {
        List<Card> playerHand = this.cardsInHand;
        List<Card> opponentHand = this.getOpponent().cardsInHand;
        this.cardsInHand = opponentHand;
        this.changeOwnerOfAllCardsInHandToSelf();
        this.getOpponent().cardsInHand = playerHand;
        this.getOpponent().changeOwnerOfAllCardsInHandToSelf();
    }

    public void removeCardFromHand(Card card) {
        this.cardsInHand.remove(card);
    }

    public void addCardToBoard(Card card) {
        this.cardsOnBoard.add(card);
    }

    public void removeAllCardsOnBoard() {
        this.cardsOnBoard.clear();
    }

    public void removeAllCardsOnBoardForBothPlayers() {
        this.removeAllCardsOnBoard();
        this.opponent.removeAllCardsOnBoard();
    }

    public int calculateScore() {
        int scoreNigiriEgg = this.calculateScoreForCardType(CardType.egg);
        int scoreNigiriSalmon = this.calculateScoreForCardType(CardType.salmon);
        int scoreNigiriSquid = this.calculateScoreForCardType(CardType.squid);
        int scoreDumpling = this.calculateScoreForCardType(CardType.dumpling);
        int scoreMakirol = this.calculateScoreForCardType(CardType.makiRol);
        int scoreTempura = this.calculateScoreForCardType(CardType.tempura);
        int scoreSashimi = this.calculateScoreForCardType(CardType.sashimi);
        int scorePudding = this.calculateScoreForCardType(CardType.pudding);
        int totalScore = scoreNigiriEgg + scoreNigiriSalmon
                + scoreNigiriSquid + scoreDumpling + scoreMakirol
                + scoreTempura + scoreSashimi + scorePudding;
        return totalScore;
    }

    public int calculateScoreForCardType(CardType cardType) {
        int score = 0;
        int nrOfCards = this.countNumberOfSpecificCardsPlayed(cardType);
        if (cardType == CardType.egg) {
            score = nrOfCards;
        } else if (cardType == CardType.salmon) {
            score = nrOfCards * 2;
        } else if (cardType == CardType.squid) {
            score = nrOfCards * 3;
        } else if (cardType == CardType.dumpling) {
            score = this.calculateDumplingScore(nrOfCards);
        } else if (cardType == CardType.makiRol) {
            score = this.calculateMakiRollScore(nrOfCards);
        } else if (cardType == CardType.tempura) {
            int dividedBy = this.determineSashimiOrTempuraCard(cardType);
            score = this.calculateSashimiOrTempuraScore(nrOfCards, dividedBy);
        } else if (cardType == CardType.sashimi) {
            int dividedBy = this.determineSashimiOrTempuraCard(cardType);
            score = this.calculateSashimiOrTempuraScore(nrOfCards, dividedBy);
        } else if (cardType == CardType.pudding) {
            score = this.calculatePuddingScore();
        }
        return score;
    }

    public int calculatePuddingScore() {
        int score = 0;
        if (this.getRoundCount() == 2
                && this.determineIfPlayerOneHasMostPuddingCards()
                && !this.determineIfPlayersHaveEqualPuddingCards()) {
            score = 6;
        } else if (this.getRoundCount() == 2
                && !this.determineIfPlayerOneHasMostPuddingCards()
                && !this.determineIfPlayersHaveEqualPuddingCards()) {
            score = -6;
        } else if (this.getRoundCount() == 2
                && this.determineIfPlayersHaveEqualPuddingCards()) {
            score = 3;
        }
        return score;
    }

    public boolean determineIfPlayerOneHasMostPuddingCards() {
        if (this.getPuddingCount() > this.getOpponent().getPuddingCount()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean determineIfPlayersHaveEqualPuddingCards() {
        if (this.getPuddingCount() == this.getOpponent().getPuddingCount()) {
            return true;
        } else {
            return false;
        }
    }

    public int calculateSashimiOrTempuraScore(int nrOfCards, int dividedBy) {
        int score = 0;
        if (nrOfCards % dividedBy == 0) {
            int nrOfPairs = nrOfCards / dividedBy;
            score = nrOfPairs * 5;
        } else if (nrOfCards % dividedBy != 0) {
            int remainder = nrOfCards % dividedBy;
            int nrOfPairs = (nrOfCards - remainder) / dividedBy;
            score = nrOfPairs * 5;
        }
        return score;
    }

    public int determineSashimiOrTempuraCard(CardType cardType) {
        int dividedBy = 0;
        if (cardType == CardType.tempura) {
            dividedBy = 2;
        } else if (cardType == CardType.sashimi) {
            dividedBy = 3;
        } else {
            throw new IllegalArgumentException("Not sashimi or tempura card type!");
        }
        return dividedBy;
    }

    public int calculateMakiRollScore(int nrOfCards) {
        int nrOfCardsOpponent = this.getOpponent().countNumberOfSpecificCardsPlayed(CardType.makiRol);
        int score = 0;
        if (nrOfCards > nrOfCardsOpponent || nrOfCards == nrOfCardsOpponent && nrOfCards != 0) {
            score = 6;
        } else if (nrOfCards < nrOfCardsOpponent) {
            score = 3;
        } else if (nrOfCards == 0) {
            score = 0;
        }
        return score;
    }

    public int countNumberOfSpecificCardsPlayed(CardType cardType) {
        List<Card> cardsOnBoard = this.getCardsOnBoard();
        int count = 0;
        for (int i = 0; i < cardsOnBoard.size(); i++) {
            Card card = cardsOnBoard.get(i);
            if (card.getCardType() == cardType) {
                count++;
            }
        }
        return count;
    }

    public int calculateDumplingScore(int dumplingCount) {
        int score = 0;
        switch (dumplingCount) {
            case 0:
                score = 0;
                break;
            case 1:
                score = 1;
                break;
            case 2:
                score = 3;
                break;
            case 3:
                score = 6;
                break;
            case 4:
                score = 10;
                break;
            case 5:
                score = 15;
                break;
            default:
                score = 15;
        }
        return score;
    }

    public void handleEndOfRoundOneAndTwo() {
        this.calculatAndUpdateScoreAtEndOfRoundForBothPlayers();
        this.drawTenCardsAndGiveToPlayerHand();
        this.getOpponent().drawTenCardsAndGiveToPlayerHand();
        this.updateRoundCountForBothPlayers();
        this.removeAllCardsOnBoardForBothPlayers();
    }
}
