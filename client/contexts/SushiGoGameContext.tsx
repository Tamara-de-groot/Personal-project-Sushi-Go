import { createContext, useContext, useState } from "react";
import React from 'react'
import { GameState } from "../src/types";

type ContextType = {
    gameState: GameState | undefined,
    setGameState: (gameState: GameState) => void;
}

const SushiGoGameContext = createContext<ContextType>({
    gameState: undefined,
    setGameState() { },
});

type Props = React.PropsWithChildren;

export const SushiGoGameProvider = (props: Props) => {
    const { children } = props;

    const [gameState, setGameState] = useState<GameState | undefined>(undefined);

    return <SushiGoGameContext.Provider value={{
        gameState: gameState,
        setGameState: setGameState
    }}>{children}</SushiGoGameContext.Provider>
}

export const useSushiGoGame = () => {
    const context = useContext(SushiGoGameContext);

    if (context === undefined) {
        throw new Error('useSushiGoGame must be used within a SushiGoGameProvider');
    }

    return context;
}