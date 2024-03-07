export type GameState = {
    players: [Player, Player];
    gameStatus: {
        endOfGame: boolean;
        winner: string;
    };
}

export type Player = {
    name: string;
    cards: Cards[];
    playedCards: Cards[];
    type: "player1"|"player2"
    hasTurn: boolean;
    score: number;
    nrOfDumplingPlayed: number;
    nrOfNigiriEggPlayed: number;
    nrOfNigiriSalmonPlayed: number;
    nrOfNigiriSquidPlayed: number;
    nrOfMakiRolPlayed: number;
    nrOfTempuraPlayed: number;
    nrOfSashimiPlayed: number;
    nrOfPuddingCardsPlayed: number;

}

export type Cards ={
    cardType: String;
    owner: Player;
}

export function isGameState(gameState: unknown): gameState is GameState {
    return (gameState as GameState).players !== undefined;
}


