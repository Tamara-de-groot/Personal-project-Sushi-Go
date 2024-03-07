package sushigo.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import sushigo.domain.ISushiGo.CardType;
import sushigo.domain.ISushiGo.Winner;

public class SushiGoTest {

    @Test
    public void testGetterNameOfPlayerOne() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        assertEquals("Piet", sushigo.getNameOfPlayerOne());
        assertEquals("Jan", sushigo.getNameOfPlayerTwo());
    }

    @Test
    public void testDoesPlayerOneHaveTurn() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        assertTrue(sushigo.isPlayersTurn("Piet"));
        assertFalse(sushigo.isPlayersTurn("Jan"));
    }

    @Test
    public void testIsPlayersTurnThrowsExceptionWhenInvalidNameIsUsed() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        Throwable thrown = assertThrows(IllegalArgumentException.class,
                () -> sushigo.isPlayersTurn("Ria"));
        assertThat(thrown.getMessage(), is("invalid player turn"));
    }

    @Test
    public void testDoesgetHandOfCardsReturnAListOfTenCards() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        List<Card> handOfCardsPlayerOne = sushigo.getHandOfCards("Piet");
        List<Card> handOfCardsPlayerTwo = sushigo.getHandOfCards("Jan");
        assertEquals(10, handOfCardsPlayerOne.size());
        assertEquals(Card.class, handOfCardsPlayerOne.get(0).getClass());
        assertEquals(10, handOfCardsPlayerTwo.size());
        assertEquals(Card.class, handOfCardsPlayerTwo.get(0).getClass());
    }

    @Test
    public void testGetHandOfCardsThrowsExceptionWhenInvalidNameIsUsed() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        Throwable thrown = assertThrows(IllegalArgumentException.class,
                () -> sushigo.getHandOfCards("Ria"));
        assertThat(thrown.getMessage(), is("invalid hand of cards"));
    }

    @Test
    public void testGetCardsOnBoardThrowsExceptionWhenInvalidNameIsUsed() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        Throwable thrown = assertThrows(IllegalArgumentException.class,
                () -> sushigo.getCardsOnBoard("Ria"));
        assertThat(thrown.getMessage(), is("invalid hand of cards"));
    }

    @Test
    public void testIfPlayedCardIsAddedToBoardOfPlayerWhoHasTurn() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        Card chosenCard = sushigo.getHandOfCards("Piet").get(0);
        sushigo.playCard(0, "Piet");
        assertEquals(chosenCard, sushigo.getCardsOnBoard("Piet").get(0));
        assertEquals(0, sushigo.getCardsOnBoard("Jan").size());
    }

    @Test
    public void testIfIllegalArgumentExceptionIsThrownWhenPlayerWhoDoesNotHaveTurnPlaysCard() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        Throwable thrown = assertThrows(IllegalArgumentException.class,
                () -> sushigo.playCard(2, "Jan"));
        assertThat(thrown.getMessage(), is("chosen card is invalid"));
    }

    @Test
    public void testIfPlayerTwoCanPlayCardWhenHeGetsTurn() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        Card chosenCard = sushigo.getPlayerWithName("Jan").getCardInHandAtIndex(0);
        sushigo.getPlayerWithName("Jan").switchTurn();
        sushigo.playCard(0, "Jan");
        assertEquals(1, sushigo.getCardsOnBoard("Jan").size());
        assertEquals(chosenCard, sushigo.getCardsOnBoard("Jan").get(0));
    }

    @Test
    public void testIfNameReturnsCorrectPlayer() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        Player player = sushigo.getPlayerOne();
        assertEquals(player, sushigo.getPlayerWithName("Piet"));
        assertEquals(player.getOpponent(), sushigo.getPlayerWithName("Jan"));
    }

    @Test
    public void testIfGetScoreCalculatesTheCorrectScoreForPlayerOne() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        Player player = sushigo.getPlayerOne();
        Card card = new Card(player, CardType.squid);
        player.addCardToBoard(card);
        player.updateScoreAtEndOfRound();
        assertEquals(3, sushigo.getScore("Piet"));
    }

    @Test
    public void testIfGetScoreCalculatesTheCorrectScoreForPlayerTwo() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        Player player = sushigo.getPlayerOne().getOpponent();
        Card card = new Card(player, CardType.squid);
        player.addCardToBoard(card);
        player.updateScoreAtEndOfRound();
        assertEquals(3, sushigo.getScore("Jan"));
    }

    @Test
    public void testIfGetScoreThrowsExceptionWhenInvalidNameIsUsed() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        Throwable thrown = assertThrows(IllegalArgumentException.class,
                () -> sushigo.getScore("Ria"));
        assertThat(thrown.getMessage(), is("invalid player name"));
    }

    @Test
    public void testIfBothPlayersHaveCardsInHandIsEndGameIsFalse() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        assertFalse(sushigo.isEndOfGame());
    }

    @Test
    public void testIfBothPlayersHaveNoCardsInHandAndRountCountIsThreeEndGameIsTrue() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        sushigo.getPlayerOne().updateRoundCountForBothPlayers();
        sushigo.getPlayerOne().updateRoundCountForBothPlayers();
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(1, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        assertTrue(sushigo.isEndOfGame());
    }

    @Test
    public void testIfAPlayerWinsWhenTheyHaveHighestScoreAndIsEndGame() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        sushigo.getPlayerOne().updateRoundCountForBothPlayers();
        sushigo.getPlayerOne().updateRoundCountForBothPlayers();
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        sushigo.playCard(0, "Piet");
        sushigo.playCard(0, "Jan");
        assertNotEquals(Winner.NO_ONE, sushigo.getWinner());
    }

    @Test
    public void testIfWinnerIsNoOneWhenIsEndGameIsFalse() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        assertEquals(Winner.NO_ONE, sushigo.getWinner());
    }

    @Test
    public void testIfPlayerOneWinsWithHighestScore() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        Player player1 = sushigo.getPlayerOne();
        Player player2 = sushigo.getPlayerOne().getOpponent();
        Card card1Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card1Player1);
        Card card2Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card2Player1);
        Card card3Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card3Player1);
        Card card4Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card4Player1);
        Card card5Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card5Player1);
        Card card6Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card6Player1);
        Card card7Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card7Player1);
        Card card8Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card8Player1);
        Card card9Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card9Player1);
        Card card10Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card10Player1);
        Card card1Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card1Player2);
        Card card2Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card2Player2);
        Card card3Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card3Player2);
        Card card4Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card4Player2);
        Card card5Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card5Player2);
        Card card6Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card6Player2);
        Card card7Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card7Player2);
        Card card8Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card8Player2);
        Card card9Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card9Player2);
        Card card10Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card10Player2);
        Card card = new Card(player1, CardType.squid);
        player1.addCardToBoard(card);
        player1.updateScoreAtEndOfRound();
        player1.updateRoundCountForBothPlayers();
        player1.updateRoundCountForBothPlayers();
        assertEquals(Winner.PLAYER_1, sushigo.getWinner());
    }

    @Test
    public void testIfPlayerTwoWinsWithHighestScore() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        Player player1 = sushigo.getPlayerOne();
        Player player2 = sushigo.getPlayerOne().getOpponent();
        Card card1Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card1Player1);
        Card card2Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card2Player1);
        Card card3Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card3Player1);
        Card card4Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card4Player1);
        Card card5Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card5Player1);
        Card card6Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card6Player1);
        Card card7Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card7Player1);
        Card card8Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card8Player1);
        Card card9Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card9Player1);
        Card card10Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card10Player1);
        Card card1Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card1Player2);
        Card card2Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card2Player2);
        Card card3Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card3Player2);
        Card card4Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card4Player2);
        Card card5Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card5Player2);
        Card card6Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card6Player2);
        Card card7Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card7Player2);
        Card card8Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card8Player2);
        Card card9Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card9Player2);
        Card card10Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card10Player2);
        Card card = new Card(player2, CardType.squid);
        player2.addCardToBoard(card);
        player1.calculatAndUpdateScoreAtEndOfRoundForBothPlayers();
        player1.updateRoundCountForBothPlayers();
        player1.updateRoundCountForBothPlayers();
        assertEquals(Winner.PLAYER_2, sushigo.getWinner());
    }

    @Test
    public void testIfItsADrawWhenScoreAreEqual() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        Player player1 = sushigo.getPlayerOne();
        Player player2 = sushigo.getPlayerOne().getOpponent();
        Card card1Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card1Player1);
        Card card2Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card2Player1);
        Card card3Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card3Player1);
        Card card4Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card4Player1);
        Card card5Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card5Player1);
        Card card6Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card6Player1);
        Card card7Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card7Player1);
        Card card8Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card8Player1);
        Card card9Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card9Player1);
        Card card10Player1 = player1.getCardInHandAtIndex(0);
        player1.removeCardFromHand(card10Player1);
        Card card1Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card1Player2);
        Card card2Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card2Player2);
        Card card3Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card3Player2);
        Card card4Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card4Player2);
        Card card5Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card5Player2);
        Card card6Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card6Player2);
        Card card7Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card7Player2);
        Card card8Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card8Player2);
        Card card9Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card9Player2);
        Card card10Player2 = player2.getCardInHandAtIndex(0);
        player2.removeCardFromHand(card10Player2);
        Card card1 = new Card(player1, CardType.squid);
        Card card2 = new Card(player2, CardType.squid);
        player1.addCardToBoard(card1);
        player2.addCardToBoard(card2);
        player1.calculatAndUpdateScoreAtEndOfRoundForBothPlayers();
        player1.updateRoundCountForBothPlayers();
        player1.updateRoundCountForBothPlayers();
        assertEquals(Winner.DRAW, sushigo.getWinner());
        assertEquals(sushigo.getScore("Piet"), sushigo.getScore("Jan"));
    }

    @Test
    public void testIfCorrectNumberOfCardsIsReturned() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        Player player1 = sushigo.getPlayerOne();
        Card card1 = new Card(player1, CardType.squid);
        Card card2 = new Card(player1, CardType.squid);
        Card card3 = new Card(player1, CardType.dumpling);
        Card card4 = new Card(player1, CardType.salmon);
        player1.addCardToBoard(card1);
        player1.addCardToBoard(card2);
        player1.addCardToBoard(card3);
        player1.addCardToBoard(card4);
        assertEquals(2, sushigo.getNumberOfSpecificCards("Piet", CardType.squid));
        assertEquals(1, sushigo.getNumberOfSpecificCards("Piet", CardType.dumpling));
        assertEquals(1, sushigo.getNumberOfSpecificCards("Piet", CardType.salmon));
        assertEquals(0, sushigo.getNumberOfSpecificCards("Piet", CardType.egg));
    }

    @Test
    public void testIfGetPuddingCountGetsTheCorrectPuddingCount() {
        ISushiGo sushigo = new SushiGo("Piet", "Jan");
        Player player1 = sushigo.getPlayerOne();
        Card card1 = new Card(player1, CardType.pudding);
        Card card2 = new Card(player1, CardType.pudding);
        card1.playCard();
        card2.playCard();
        assertEquals(2, sushigo.getPuddingCount("Piet"));
    }

}
