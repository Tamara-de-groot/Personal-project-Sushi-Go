package sushigo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import sushigo.domain.ISushiGo.CardType;

public class CardTest {

    @Test
    public void testGetCorrectOnwer() {
        Player player = new Player();
        Card firstCard = player.getCardInHandAtIndex(0);
        assertEquals(player, firstCard.getOwner());
    }

    @Test
    public void testIfOwnerIsChangedToCorrectOwner() {
        Player player = new Player();
        Card card = new Card(player.getOpponent(), CardType.salmon);
        card.changeOwner(player);
        assertEquals(player, card.getOwner());
    }

    @Test
    public void testCardHasCorrectCardType() {
        Player player = new Player();
        Card nigiriSalmon = new Card(player, CardType.salmon);
        assertEquals(CardType.salmon, nigiriSalmon.getCardType());
    }

    @Test
    public void testCanCardOnlyBePlayedWhenOwnerHasTurn() {
        Player playerOne = new Player();
        Card firstPlayerOneCard = playerOne.getCardInHandAtIndex(0);
        Card firstPlayerTwoCard = playerOne.getOpponent().getCardInHandAtIndex(0);
        assertTrue(firstPlayerOneCard.cardCanBePlayed());
        assertFalse(firstPlayerTwoCard.cardCanBePlayed());
    }

    @Test
    public void testIfCardCanBeChosenIfPlayerHasTurn() {
        Player playerOne = new Player();
        Card firstCard = playerOne.getCardInHandAtIndex(0);
        firstCard.chooseCard();
        assertNotEquals(firstCard, playerOne.getCardInHandAtIndex(0));
        assertEquals(9, playerOne.getCardsInHand().size());
        assertEquals(playerOne.getCardOnBoardAtIndex(0), firstCard);
    }

    @Test
    public void testIfAnExceptionIsThrownWhenCardIsChosenOfPlayerWhoDoesNotHaveTurn() {
        Player playerOne = new Player();
        Card firstPlayerTwoCard = playerOne.getOpponent().getCardInHandAtIndex(0);
        Throwable thrown = assertThrows(IllegalStateException.class, () -> firstPlayerTwoCard.chooseCard());
        assertThat(thrown.getMessage(), is("Card cannot be played"));
    }

    @Test
    public void testPlayCardRemovesCardFromHandAndAddsToBoard() {
        Player player = new Player();
        Card firstPlayerCard = player.getCardInHandAtIndex(0);
        firstPlayerCard.playCard();
        assertNotEquals(firstPlayerCard, player.getCardInHandAtIndex(0));
        assertEquals(9, player.getCardsInHand().size());
        assertEquals(player.getCardOnBoardAtIndex(0), firstPlayerCard);
    }

    @Test
    public void testPlayersSwitchTurnAfterPlayingCard() {
        Player player = new Player();
        Card firstPlayerCard = player.getCardInHandAtIndex(0);
        firstPlayerCard.playCard();
        assertFalse(player.hasTurn());
        assertTrue(player.getOpponent().hasTurn());
    }

    @Test
    public void testIfAfterPlayingCardOneIsAddedToCountofPlayerOneAndNotPlayerTwo() {
        Player player = new Player();
        Card firstPlayerCard = player.getCardInHandAtIndex(0);
        firstPlayerCard.playCardsAndSwitchHand();
        assertEquals(1, player.getCount());
        assertEquals(0, player.getOpponent().getCount());
    }

    @Test
    public void testAfterBothPlayersHavePlayedCardIfTheySwitchHands() {
        Player player = new Player();
        Card firstPlayerCardPlayerOne = player.getCardInHandAtIndex(0);
        Card firstPlayerCardPlayerTwo = player.getOpponent().getCardInHandAtIndex(0);
        firstPlayerCardPlayerOne.playCardsAndSwitchHand();
        List<Card> handPlayerOneBeforeSwitch = player.getCardsInHand();
        firstPlayerCardPlayerTwo.playCardsAndSwitchHand();
        assertEquals(1, player.getCount());
        assertEquals(1, player.getOpponent().getCount());
        assertEquals(handPlayerOneBeforeSwitch, player.getOpponent().getCardsInHand());
    }

    @Test
    public void testIfHandOfCardsIsNotSwitchedAfterSecondTurnOfPlayerOne() {
        Player player = new Player();
        Card firstPlayerCardPlayerOne = player.getCardInHandAtIndex(0);
        Card firstPlayerCardPlayerTwo = player.getOpponent().getCardInHandAtIndex(0);
        firstPlayerCardPlayerOne.playCardsAndSwitchHand();
        firstPlayerCardPlayerTwo.playCardsAndSwitchHand();
        Card SecondPlayerCardPlayerOne = player.getCardInHandAtIndex(1);
        List<Card> handPlayerOneAfterSwitch = player.getCardsInHand();
        SecondPlayerCardPlayerOne.playCardsAndSwitchHand();
        assertEquals(2, player.getCount());
        assertEquals(1, player.getOpponent().getCount());
        assertEquals(handPlayerOneAfterSwitch, player.getCardsInHand());
    }

    @Test
    public void testIfCheckIfTurnIsOverReturnsFalseWhenTurnIsNotOver() {
        Player player = new Player();
        Card card = player.getCardInHandAtIndex(0);
        assertFalse(card.checkIfRoundIsOverAfterThisTurn());
    }

    @Test
    public void testIfCheckIfTurnIsOverReturnsTrueWhenBothPlayersDontHaveCardsInHand() {
        Player player1 = new Player();
        Player player2 = player1.getOpponent();
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
        assertEquals(0, player1.getCardsInHand().size());
        assertEquals(1, player2.getCardsInHand().size());
        assertTrue(card10Player2.checkIfRoundIsOverAfterThisTurn());
    }

    @Test
    public void testIfPlayCardsAndSwitchHandDrawsTenCardsForBothPlayersAfterTheRoundIsOverAndUpdateScore() {
        Player player1 = new Player();
        Player player2 = player1.getOpponent();
        Card card1Player1 = player1.getCardInHandAtIndex(0);
        card1Player1.playCardsAndSwitchHand();
        Card card1Player2 = player2.getCardInHandAtIndex(0);
        card1Player2.playCardsAndSwitchHand();
        Card card2Player1 = player1.getCardInHandAtIndex(0);
        card2Player1.playCardsAndSwitchHand();
        Card card2Player2 = player2.getCardInHandAtIndex(0);
        card2Player2.playCardsAndSwitchHand();
        Card card3Player1 = player1.getCardInHandAtIndex(0);
        card3Player1.playCardsAndSwitchHand();
        Card card3Player2 = player2.getCardInHandAtIndex(0);
        card3Player2.playCardsAndSwitchHand();
        Card card4Player1 = player1.getCardInHandAtIndex(0);
        card4Player1.playCardsAndSwitchHand();
        Card card4Player2 = player2.getCardInHandAtIndex(0);
        card4Player2.playCardsAndSwitchHand();
        Card card5Player1 = player1.getCardInHandAtIndex(0);
        card5Player1.playCardsAndSwitchHand();
        Card card5Player2 = player2.getCardInHandAtIndex(0);
        card5Player2.playCardsAndSwitchHand();
        Card card6Player1 = player1.getCardInHandAtIndex(0);
        card6Player1.playCardsAndSwitchHand();
        Card card6Player2 = player2.getCardInHandAtIndex(0);
        card6Player2.playCardsAndSwitchHand();
        Card card7Player1 = player1.getCardInHandAtIndex(0);
        card7Player1.playCardsAndSwitchHand();
        Card card7Player2 = player2.getCardInHandAtIndex(0);
        card7Player2.playCardsAndSwitchHand();
        Card card8Player1 = player1.getCardInHandAtIndex(0);
        card8Player1.playCardsAndSwitchHand();
        Card card8Player2 = player2.getCardInHandAtIndex(0);
        card8Player2.playCardsAndSwitchHand();
        Card card9Player1 = player1.getCardInHandAtIndex(0);
        card9Player1.playCardsAndSwitchHand();
        Card card9Player2 = player2.getCardInHandAtIndex(0);
        card9Player2.playCardsAndSwitchHand();
        Card card10Player1 = player1.getCardInHandAtIndex(0);
        card10Player1.playCardsAndSwitchHand();
        Card card10Player2 = player2.getCardInHandAtIndex(0);
        card10Player2.playCardsAndSwitchHand();
        assertEquals(10, player1.getCardsInHand().size());
        assertEquals(10, player2.getCardsInHand().size());
        assertNotEquals(0, player1.getScore());
        assertEquals(0, player1.getCardsOnBoard().size());
    }

    @Test
    public void testIfRoundThreeIsOverAfterThisTurnWhenRoundCountIsThree() {
        Player player1 = new Player();
        Player player2 = player1.getOpponent();
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
        assertEquals(0, player1.getCardsInHand().size());
        assertEquals(1, player2.getCardsInHand().size());
        assertTrue(card10Player2.checkIfRoundIsOverAfterThisTurn());
        assertTrue(card10Player2.checkIfGameIsOverAfterThisTurn(2));
    }

    @Test
    public void testIfNoNewCardsAredrawnAtTheEndOfThirdRound() {
        Player player1 = new Player();
        Player player2 = player1.getOpponent();
        player1.updateRoundCountForBothPlayers();
        player1.updateRoundCountForBothPlayers();
        Card card1Player1 = player1.getCardInHandAtIndex(0);
        card1Player1.playCardsAndSwitchHand();
        Card card1Player2 = player2.getCardInHandAtIndex(0);
        card1Player2.playCardsAndSwitchHand();
        Card card2Player1 = player1.getCardInHandAtIndex(0);
        card2Player1.playCardsAndSwitchHand();
        Card card2Player2 = player2.getCardInHandAtIndex(0);
        card2Player2.playCardsAndSwitchHand();
        Card card3Player1 = player1.getCardInHandAtIndex(0);
        card3Player1.playCardsAndSwitchHand();
        Card card3Player2 = player2.getCardInHandAtIndex(0);
        card3Player2.playCardsAndSwitchHand();
        Card card4Player1 = player1.getCardInHandAtIndex(0);
        card4Player1.playCardsAndSwitchHand();
        Card card4Player2 = player2.getCardInHandAtIndex(0);
        card4Player2.playCardsAndSwitchHand();
        Card card5Player1 = player1.getCardInHandAtIndex(0);
        card5Player1.playCardsAndSwitchHand();
        Card card5Player2 = player2.getCardInHandAtIndex(0);
        card5Player2.playCardsAndSwitchHand();
        Card card6Player1 = player1.getCardInHandAtIndex(0);
        card6Player1.playCardsAndSwitchHand();
        Card card6Player2 = player2.getCardInHandAtIndex(0);
        card6Player2.playCardsAndSwitchHand();
        Card card7Player1 = player1.getCardInHandAtIndex(0);
        card7Player1.playCardsAndSwitchHand();
        Card card7Player2 = player2.getCardInHandAtIndex(0);
        card7Player2.playCardsAndSwitchHand();
        Card card8Player1 = player1.getCardInHandAtIndex(0);
        card8Player1.playCardsAndSwitchHand();
        Card card8Player2 = player2.getCardInHandAtIndex(0);
        card8Player2.playCardsAndSwitchHand();
        Card card9Player1 = player1.getCardInHandAtIndex(0);
        card9Player1.playCardsAndSwitchHand();
        Card card9Player2 = player2.getCardInHandAtIndex(0);
        card9Player2.playCardsAndSwitchHand();
        Card card10Player1 = player1.getCardInHandAtIndex(0);
        card10Player1.playCardsAndSwitchHand();
        Card card10Player2 = player2.getCardInHandAtIndex(0);
        card10Player2.playCardsAndSwitchHand();
        assertEquals(0, player1.getCardsInHand().size());
        assertEquals(0, player2.getCardsInHand().size());
        assertEquals(2, player1.getRoundCount());
    }

    @Test
    public void testIfPlayingPuddingCardUpdatesPuddingCount() {
        Player player = new Player();
        Card card = new Card(player, CardType.pudding);
        card.playCard();
        assertEquals(1, player.getPuddingCount());
        assertEquals(0, player.getOpponent().getPuddingCount());
    }

    @Test
    public void testIfPuddingCountIsNotUpdatedWhenNotPudding() {
        Player player = new Player();
        Card card = new Card(player, CardType.sashimi);
        card.playCard();
        assertEquals(0, player.getPuddingCount());
        assertEquals(0, player.getOpponent().getPuddingCount());
    }
}
