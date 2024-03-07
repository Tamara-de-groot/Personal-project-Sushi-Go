package sushigo.api.models;

import sushigo.domain.ISushiGo;
import sushigo.domain.ISushiGo.CardType;

public class PlayerDTO {
    public String name;
    public boolean hasTurn;
    public CardDTO[] cards;
    public CardDTO[] cardsOnBoard;
    public int score;
    public int nrOfDumplingPlayed;
    public int nrOfNigiriEggPlayed;
    public int nrOfNigiriSalmonPlayed;
    public int nrOfNigiriSquidPlayed;
    public int nrOfMakiRolPlayed;
    public int nrOfTempuraPlayed;
    public int nrOfSashimiPlayed;
    public int nrOfPuddingCardsPlayed;

    public PlayerDTO(
            ISushiGo sushiGo,
            String name) {
        this.name = name;
        this.hasTurn = sushiGo.isPlayersTurn(name);
        this.cards = new CardDTO[sushiGo.getHandOfCards(name).size()];
        for (int i = 0; i < sushiGo.getHandOfCards(name).size(); i++) {
            this.cards[i] = new CardDTO(
                    sushiGo.getHandOfCards(name).get(i).getCardType(),
                    name);
        }
        this.cardsOnBoard = new CardDTO[sushiGo.getCardsOnBoard(name).size()];
        for (int i = 0; i < sushiGo.getCardsOnBoard(name).size(); i++) {
            this.cardsOnBoard[i] = new CardDTO(
                    sushiGo.getCardsOnBoard(name).get(i).getCardType(),
                    name);
        }
        this.score = sushiGo.getScore(name);
        this.nrOfDumplingPlayed = sushiGo.getNumberOfSpecificCards(name, CardType.dumpling);
        this.nrOfNigiriEggPlayed = sushiGo.getNumberOfSpecificCards(name, CardType.egg);
        this.nrOfNigiriSalmonPlayed = sushiGo.getNumberOfSpecificCards(name, CardType.salmon);
        this.nrOfNigiriSquidPlayed = sushiGo.getNumberOfSpecificCards(name, CardType.squid);
        this.nrOfMakiRolPlayed = sushiGo.getNumberOfSpecificCards(name, CardType.makiRol);
        this.nrOfTempuraPlayed = sushiGo.getNumberOfSpecificCards(name, CardType.tempura);
        this.nrOfSashimiPlayed = sushiGo.getNumberOfSpecificCards(name, CardType.sashimi);
        this.nrOfPuddingCardsPlayed = sushiGo.getPuddingCount(name);
    }

    public String getName() {
        return name;
    }

    public boolean getHasTurn() {
        return hasTurn;
    }

    public CardDTO[] getCards() {
        return cards;
    }

}
