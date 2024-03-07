# Personal Project: Sushi Go

[![pipeline status](https://git.sogyo.nl/tdgroot/ip-sushi-go/badges/main/pipeline.svg)](https://git.sogyo.nl/tdgroot/ip-sushi-go/-/commits/main)
[![code-coverage](https://git.sogyo.nl/tdgroot/ip-sushi-go/badges/main/coverage.svg)](https://git.sogyo.nl/tdgroot/ip-sushi-go/-/commits/main)

## Goal

The goal of this project is to get an more indept understanding of programming by implementing the card game [Sushi Go] (https://boardgamegeek.com/boardgame/133473/sushi-go). In Sushi Go 2-5 players play against eachother to get the best combination of sushi dishes. 

Sushi Go is a standard pass-and-go game that requires players to pass their cards to eachother, while keeping the best cards for themselves. In this game players play a card every turn. After each player has played their card, they pass the remaining cards in their hand to the player on their left. This continues until no one has any cards left in their hand. When this happens, a round is finished and the score of every player is calculated. The score is not only dependent on which cards are played, but also on which combinatin of cards is played. For example, sashimi cards are only worth any points if they are played in a set of 3. So if a player has played 2 sashimi cards, no points are rewarded to this player for their sashimi cards. Another example is the wasabi card. This card triples the point value of the next card its player plays. After the score is calculated, every player gets a new hand of cards and the next round begins. 

Every game has 3 rounds and after the 3 round the winner is determined.

## Instructions for installating and running the application and for running the tests:


Download and install all requirements as explained here:
```
git clone https://git.sogyo.nl/tdgroot/ip-sushi-go
cd client
npm install
```

Start the java back-end server by running the App.java file in the `ip-sushi-go/api/src/main/java/sushigo/api` folder.

Next run the front-end server from the `ip-sushi-go/client` folder by using the following command in a terminal:

```
npm run dev
```

Tests can be run by using the following command in a terminal:
```
./mvnw test
```

## Goals of the project according to the MoSCoW method:

The goal of this project is to implement a playable version of the Sushi Go card game.

**Must**

- [x] A playable game for 2 players on 1 computer.
- [x] Every player gets 10 cards in their hand. These are randomly chosen from 3 card types (only ones available in the first version of this project).
- [x] A player is only able to see their own cards.
- [x] The perspective switches between the players: the player that has turn is able to only see their own cards.
- [x] The game has 3 different nigiri cards: egg, salmon and squid.
- [x] ~~Players can play a card on their board during their turn. This card should remain visible until the round is finished and the board is cleared.~~ --> this is solved by keeping track of how many cards of every available card type every player has played during the current round.
- [x] Players can pass their hand of cards to their opponent after both players have had their turn. 
- [x] When both players do not have any cards remaining in their hand, a round is over and the score of this round should be calculated.
- [x] When a round is over all cards should be cleared from the board and 10 new cards should be added to the hand of both players.
- [x] When the 3rd round is over, the game should be finished and a winner should be determined.
- [x] The winner is the player with the most points. A tie is possilbe as well, when both players have the same score.

**Should**

- [x] Add **Dumpling** cards:
    - The value of **dumpling** cards increases if more dumpling cards are played.
    - 1x = 1 point
    - 2x = 3 points
    - 3x = 6 points
    - 4x = 10 points
    - 5x = 15 points
- [x] Add **Maki Roll** cards:
    - The value of **Maki Roll** cards is dependent on the amount of **Maki Roll** cards that have been played by both players in the current round.
    - The player with the most **Maki Roll** cards get 6 points.
    - The player with the least **Maki Roll** cards get 3 points.
    - If a player does not have any **Maki Roll** cards, they do not get any points.
- [x] Add **Tempura** cards:
    - The value of **Tempura** cards is dependent on how many a player has played in the current round.
    - Every set of 2 **Tempura** cards is worth 5 points.
    - 1 **Tempura** card is not worth any points! 
- [x] Add **Sashimi** cards:
    - The value of **Sashimi** cards is dependent on how many a player has played in the current round.
    - Every set of 3 **Sashimi** cards is worth 5 points.
    - 1 **Sashimi** card is not worth any points!
- [x] Add **pudding** cards:
    - A **pudding** card is a dessert card, which have special rules.
    - Dessert cards stay on the board until the end of round 3.
    - The value of a **Pudding** card is dependent on how many **Pudding** cards both players have played.
    - The player with the most **Pudding** cards gets 6 points.
    - The player with the least **Pudding** cards loses 6 points.

**Could:**

- [ ] Add **Wasabi** cards:
    - When a **Wasabi** card is played, the value of the next card will be tripled.
- [ ] Add an option to choose whcih menu you will play:
    - Similar to Sushi Go Party.
    - Players will be able to choose which dishes will be included in the game.
    - To add this feature, more cards (that are only available in Sushi Go Party) should be added.
    - Examples of cards that might be added are: Temaki, Eel & Tofu.
    - Description of all available cards in Sushi Go Party can be found here: https://www.ultraboardgames.com/sushi-go/party-card-guide.php?utm_content=cmp-true. 
- [ ] Add a 3 player option.

**Won't/wouldn't:**
- [ ] Add an option to play against the computer.
- [ ] Add an option to play online against other players.


## Technical objectives:

- To better understand the Software stack that I've previously used during the MVC module of my traineeship:
    - Java, React, Typescript & Vite. 
- To get a better understanding of the MVC model.
- ~~Working with SpringBoot as a framework.~~

## Personal learning objectives:

- Asking for help more often.
- Learn to make an adequate planning to build an simple application in three weeks.
- Work in vertical slices instead of horizontal slices. 
