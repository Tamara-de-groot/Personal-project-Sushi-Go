package sushigo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import sushigo.domain.ISushiGo.CardType;

public class PlayerTest {

    @Test
    public void testNewPlayerHasTurn() {
        Player player = new Player();
        assertTrue(player.hasTurn());
    }

    @Test
    public void testOpponentOfNewPlayerDoesNotHaveTurn() {
        Player player = new Player();
        Player opponent = player.getOpponent();
        assertFalse(opponent.hasTurn());
    }

    @Test
    public void testOpponentHasAsOpponentPlayerOne() {
        Player player = new Player();
        Player opponent = player.getOpponent();
        assertEquals(player, opponent.getOpponent());
    }

    @Test
    public void testIfCountOfPlayersIsZero() {
        Player player = new Player();
        Player opponent = player.getOpponent();
        assertEquals(0, player.getCount());
        assertEquals(0, opponent.getCount());
    }

    @Test
    public void testIfCountCanBeUpdated() {
        Player player = new Player();
        player.updateCount(11);
        assertEquals(11, player.getCount());
    }

    @Test
    public void testIfRoundCountOfPlayersIsZero() {
        Player player = new Player();
        Player opponent = player.getOpponent();
        assertEquals(0, player.getRoundCount());
        assertEquals(0, opponent.getRoundCount());
    }

    @Test
    public void testIfRoundCountCanBeUpdatesByAddingOne() {
        Player player = new Player();
        player.updateRoundCountWithOne();
        assertEquals(1, player.getRoundCount());
    }

    @Test
    public void testIfBothPlayerRoundCountIsUpdated() {
        Player player = new Player();
        player.updateRoundCountForBothPlayers();
        assertEquals(1, player.getRoundCount());
        assertEquals(1, player.getOpponent().getRoundCount());
    }

    @Test
    public void testIfScoreAtStartOfGameIsZero() {
        Player player = new Player();
        assertEquals(0, player.getScore());
    }

    @Test
    public void testsIfScoreIsUpdatedWithCalculatedScore() {
        Player player = new Player();
        Card card = new Card(player, CardType.dumpling);
        player.addCardToBoard(card);
        player.updateScoreAtEndOfRound();
        assertEquals(1, player.calculateScore());
        assertEquals(player.calculateScore(), player.getScore());
    }

    @Test
    public void testsIfScoreIsUpdatedWithCalculatedScoreForBothPlayers() {
        Player player = new Player();
        Card card = new Card(player, CardType.dumpling);
        Card card2 = new Card(player.getOpponent(), CardType.squid);
        player.addCardToBoard(card);
        player.getOpponent().addCardToBoard(card2);
        player.calculatAndUpdateScoreAtEndOfRoundForBothPlayers();
        assertEquals(1, player.getScore());
        assertEquals(3, player.getOpponent().getScore());
    }

    @Test
    public void testIfAllCardsAreRemovedFromBoard() {
        Player player = new Player();
        Card card = new Card(player, CardType.dumpling);
        Card card2 = new Card(player.getOpponent(), CardType.squid);
        player.addCardToBoard(card);
        player.getOpponent().addCardToBoard(card2);
        player.removeAllCardsOnBoardForBothPlayers();
        assertEquals(0, player.getCardsOnBoard().size());
        assertEquals(0, player.getOpponent().getCardsOnBoard().size());
    }

    @Test
    public void testFirstCardPlayerIsNotSameObjectAsOponentFirstCard() {
        Player player = new Player();
        Player opponent = player.getOpponent();
        assertNotEquals(player.getCardInHandAtIndex(0), opponent.getCardInHandAtIndex(0));
    }

    @Test
    public void testPlayerOneandPlayerTwoSwitchHandOfCards() {
        Player player = new Player();
        Player opponent = player.getOpponent();
        List<Card> playerHandBeforeSwitch = player.getCardsInHand();
        List<Card> opponentHandBeforeSwitch = opponent.getCardsInHand();
        player.switchHandOfCardsAfterTurn();
        assertNotEquals(playerHandBeforeSwitch, player.getCardsInHand());
        assertEquals(opponentHandBeforeSwitch, player.getCardsInHand());
    }

    @Test
    public void testRemoveCardFromHand() {
        Player player = new Player();
        Card card = player.getCardInHandAtIndex(0);
        player.removeCardFromHand(card);
        assertEquals(9, player.getCardsInHand().size());
        assertNotEquals(card, player.getCardInHandAtIndex(0));
    }

    @Test
    public void testAddCardToBoard() {
        Player player = new Player();
        Card card = new Card(player, CardType.salmon);
        player.addCardToBoard(card);
        assertEquals(player.getCardOnBoardAtIndex(0), card);
    }

    @Test
    public void testARandomNumberBetweenZeroAndThreeIsDrawn() {
        Player player = new Player();
        int randomInt = player.generateRandomNumberBetweenZeroAndThree();
        assertTrue(randomInt >= 0 && randomInt <= 8);
    }

    @Test
    public void testACardIsDrawn() {
        Player player = new Player();
        Card randomCard = player.drawRandomCard();
        assertEquals(Card.class, randomCard.getClass());
    }

    @Test
    public void testTenCardsAreAddedToPlayerHand() {
        Player player = new Player();
        player.drawTenCardsAndGiveToPlayerHand();
        assertEquals(20, player.getCardsInHand().size());
    }

    @Test
    public void testPlayersSwitchTurn() {
        Player player = new Player();
        player.switchTurn();
        assertTrue(player.getOpponent().hasTurn());
        assertFalse(player.hasTurn());
    }

    @Test
    public void testIfTheCorrectScoreIsCalculatedWhenOneEggCardIsPlayed() {
        Player player = new Player();
        Card card = new Card(player, CardType.egg);
        player.addCardToBoard(card);
        assertEquals(1, player.calculateScore());
    }

    @Test
    public void testIfTheCorrectScoreIsCalculatedWhenOneSalmonCardIsPlayed() {
        Player player = new Player();
        Card card = new Card(player, CardType.salmon);
        player.addCardToBoard(card);
        assertEquals(2, player.calculateScore());
    }

    @Test
    public void testIfTheCorrectScoreIsCalculatedWhenOneSquidCardIsPlayed() {
        Player player = new Player();
        Card card = new Card(player, CardType.squid);
        player.addCardToBoard(card);
        assertEquals(3, player.calculateScore());
    }

    @Test
    public void testIfTheCorrectScoreIsCalculatedWhenSquidEggAndSalmonCardsArePlayed() {
        Player player = new Player();
        Card cardOne = new Card(player, CardType.squid);
        Card cardTwo = new Card(player, CardType.salmon);
        Card cardThree = new Card(player, CardType.egg);
        player.addCardToBoard(cardOne);
        player.addCardToBoard(cardTwo);
        player.addCardToBoard(cardThree);
        assertEquals(6, player.calculateScore());
    }

    @Test
    public void testIfscoreOfOneIsGivenWhenADumplingCardIsPlayed() {
        Player player = new Player();
        Card card = new Card(player, CardType.dumpling);
        player.addCardToBoard(card);
        assertEquals(1, player.calculateScore());
    }

    @Test
    public void testIfScoreOfThreeIsGivenWhenTwoDumplingCardsArePlayed() {
        Player player = new Player();
        Card cardOne = new Card(player, CardType.dumpling);
        Card cardTwo = new Card(player, CardType.dumpling);
        player.addCardToBoard(cardOne);
        player.addCardToBoard(cardTwo);
        assertEquals(3, player.calculateScore());
    }

    @Test
    public void testIfScoreOfSixIsGivenWhenThreeDumplingCardsArePlayed() {
        Player player = new Player();
        Card cardOne = new Card(player, CardType.dumpling);
        Card cardTwo = new Card(player, CardType.dumpling);
        Card cardThree = new Card(player, CardType.dumpling);
        player.addCardToBoard(cardOne);
        player.addCardToBoard(cardTwo);
        player.addCardToBoard(cardThree);
        assertEquals(6, player.calculateScore());
    }

    @Test
    public void testIfScoreOfTenIsGivenWhenFourDumplingCardsArePlayed() {
        Player player = new Player();
        Card cardOne = new Card(player, CardType.dumpling);
        Card cardTwo = new Card(player, CardType.dumpling);
        Card cardThree = new Card(player, CardType.dumpling);
        Card cardFour = new Card(player, CardType.dumpling);
        player.addCardToBoard(cardOne);
        player.addCardToBoard(cardTwo);
        player.addCardToBoard(cardThree);
        player.addCardToBoard(cardFour);
        assertEquals(10, player.calculateScore());
    }

    @Test
    public void testIfScoreOfFifteenIsGivenWhenFiveDumplingCardsArePlayed() {
        Player player = new Player();
        Card cardOne = new Card(player, CardType.dumpling);
        Card cardTwo = new Card(player, CardType.dumpling);
        Card cardThree = new Card(player, CardType.dumpling);
        Card cardFour = new Card(player, CardType.dumpling);
        Card cardFive = new Card(player, CardType.dumpling);
        player.addCardToBoard(cardOne);
        player.addCardToBoard(cardTwo);
        player.addCardToBoard(cardThree);
        player.addCardToBoard(cardFour);
        player.addCardToBoard(cardFive);
        assertEquals(15, player.calculateScore());
    }

    @Test
    public void testIfScoreOfFifteenIsGivenWhenMoreThanFiveDumplingCardsArePlayed() {
        Player player = new Player();
        Card cardOne = new Card(player, CardType.dumpling);
        Card cardTwo = new Card(player, CardType.dumpling);
        Card cardThree = new Card(player, CardType.dumpling);
        Card cardFour = new Card(player, CardType.dumpling);
        Card cardFive = new Card(player, CardType.dumpling);
        Card cardSix = new Card(player, CardType.dumpling);
        player.addCardToBoard(cardOne);
        player.addCardToBoard(cardTwo);
        player.addCardToBoard(cardThree);
        player.addCardToBoard(cardFour);
        player.addCardToBoard(cardFive);
        player.addCardToBoard(cardSix);
        assertEquals(15, player.calculateScore());
    }

    @Test
    public void testIfAllCardsInHandAreChangedToPlayer() {
        Player player = new Player();
        Card firstCard = player.getCardInHandAtIndex(0);
        Card secondCard = player.getCardInHandAtIndex(1);
        firstCard.changeOwner(player.getOpponent());
        secondCard.changeOwner(player.getOpponent());
        player.changeOwnerOfAllCardsInHandToSelf();
        assertEquals(player, player.getCardInHandAtIndex(0).getOwner());
        assertEquals(player, player.getCardInHandAtIndex(1).getOwner());

    }

    @Test
    public void testIfOneNigiriEggCardIsCounted() {
        Player player = new Player();
        Card card = new Card(player, CardType.egg);
        player.addCardToBoard(card);
        assertEquals(1, player.countNumberOfSpecificCardsPlayed(CardType.egg));
    }

    @Test
    public void testIfNoNigiriEggCardsAreOnBoardReturnIsZero() {
        Player player = new Player();
        assertEquals(0, player.countNumberOfSpecificCardsPlayed(CardType.egg));
    }

    @Test
    public void testIfPlayerWithMostMakiRolCardsGetsSix() {
        Player player = new Player();
        assertEquals(6, player.calculateMakiRollScore(1));
    }

    @Test
    public void testIfOpponentWithMostMakiRolCardsGetsSix() {
        Player player = new Player();
        assertEquals(6, player.getOpponent().calculateMakiRollScore(2));
    }

    @Test
    public void testIfPlayerHasNoMakiRolCardsTheyGetZero() {
        Player player = new Player();
        assertEquals(0, player.calculateMakiRollScore(0));
    }

    @Test
    public void testIfCalculateScoreForCardMethodReturnsCorrectScoreForMakiRoll() {
        Player player = new Player();
        Card card = new Card(player, CardType.makiRol);
        player.addCardToBoard(card);
        assertEquals(6, player.calculateScoreForCardType(CardType.makiRol));
    }

    @Test
    public void testifSashimiScoreReturnsFiveWhenNrOfCardsIsThree() {
        Player player = new Player();
        assertEquals(5, player.calculateSashimiOrTempuraScore(3, 3));
    }

    @Test
    public void testIfSashimiScoreReturnsFiveWhenNrOfCardsIsFour() {
        Player player = new Player();
        assertEquals(5, player.calculateSashimiOrTempuraScore(4, 3));
    }

    @Test
    public void testIfSashimiScoreReturnsZeroWhenNrOfCardsIsZero() {
        Player player = new Player();
        assertEquals(0, player.calculateSashimiOrTempuraScore(0, 3));
    }

    @Test
    public void testifTempuraScoreReturnsFiveWhenNrOfCardsIsTwo() {
        Player player = new Player();
        assertEquals(5, player.calculateSashimiOrTempuraScore(2, 2));
    }

    @Test
    public void testifTempuraScoreReturnsFiveWhenNrOfCardsIsThree() {
        Player player = new Player();
        assertEquals(5, player.calculateSashimiOrTempuraScore(3, 2));
    }

    @Test
    public void testifTempuraScoreReturnsZeroWhenNrOfCardsIsZero() {
        Player player = new Player();
        assertEquals(0, player.calculateSashimiOrTempuraScore(0, 2));
    }

    @Test
    public void testIfCardTypeTempuraReturnsTwo() {
        Player player = new Player();
        assertEquals(2, player.determineSashimiOrTempuraCard(CardType.tempura));
    }

    @Test
    public void testIfCardTypeSashimiReturnsThree() {
        Player player = new Player();
        assertEquals(3, player.determineSashimiOrTempuraCard(CardType.sashimi));
    }

    @Test
    public void testIfTwoTempuraCardsOnBoardReturnScoreOfFive() {
        Player player = new Player();
        Card card = new Card(player, CardType.tempura);
        Card cardTwo = new Card(player, CardType.tempura);
        player.addCardToBoard(cardTwo);
        player.addCardToBoard(card);
        assertEquals(5, player.calculateScoreForCardType(CardType.tempura));
    }

    @Test
    public void testIfOnlyPuddingCountOfCorrectPlayerIsUpdated() {
        Player player = new Player();
        player.increasePuddingCountByOne();
        player.getOpponent().increasePuddingCountByOne();
        player.getOpponent().increasePuddingCountByOne();
        player.getOpponent().increasePuddingCountByOne();
        assertEquals(1, player.getPuddingCount());
        assertEquals(3, player.getOpponent().getPuddingCount());
    }

    @Test
    public void testIfPuddingScoreReturnsSixWhenPlayerHasPlayedMostPudding() {
        Player player = new Player();
        player.increasePuddingCountByOne();
        player.updateRoundCountWithOne();
        player.updateRoundCountWithOne();
        assertEquals(6, player.calculatePuddingScore());
    }

    @Test
    public void testIfPuddingScoreReturnsMinusSixWhenPlayerDoesNotHaveMostPudding() {
        Player player = new Player();
        player.getOpponent().increasePuddingCountByOne();
        player.updateRoundCountWithOne();
        player.updateRoundCountWithOne();
        assertEquals(-6, player.calculatePuddingScore());
    }

    @Test
    public void testIfEqualPuddingCardsGivesScoreOfThree() {
        Player player = new Player();
        player.increasePuddingCountByOne();
        player.increasePuddingCountByOne();
        player.updateRoundCountWithOne();
        player.updateRoundCountWithOne();
        player.getOpponent().increasePuddingCountByOne();
        player.getOpponent().increasePuddingCountByOne();
        player.getOpponent().updateRoundCountWithOne();
        player.getOpponent().updateRoundCountWithOne();
        assertEquals(3, player.calculatePuddingScore());
        assertEquals(3, player.getOpponent().calculatePuddingScore());
    }

    @Test
    public void testIfPlayerOneMostPuddingPlayedResultsInScoreWhenRoundCountIsTwo() {
        Player player = new Player();
        player.updateRoundCountWithOne();
        player.updateRoundCountWithOne();
        player.increasePuddingCountByOne();
        Card card = new Card(player, CardType.pudding);
        player.addCardToBoard(card);
        assertEquals(2, player.getRoundCount());
        assertEquals(6, player.calculateScoreForCardType(CardType.pudding));
    }

    @Test
    public void testIfPuddingScoreIsZeroWhenRoundCountIsNotTwo() {
        Player player = new Player();
        Card card = new Card(player, CardType.pudding);
        player.addCardToBoard(card);
        assertEquals(0, player.getRoundCount());
        assertEquals(0, player.calculateScoreForCardType(CardType.pudding));
    }

    @Test
    public void testblablabla() {
        Player player = new Player();
        Card card = new Card(player, CardType.sashimi);
        Card card2 = new Card(player.getOpponent(), CardType.egg);
        card.playCardsAndSwitchHand();
        card2.playCardsAndSwitchHand();
        assertEquals(0, player.getScore());
        assertEquals(0, player.getOpponent().getScore());
    }

}
