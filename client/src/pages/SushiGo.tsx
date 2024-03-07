import { useSushiGoGame } from "../../contexts/SushiGoGameContext"
import { Play } from "./Play"
import { Start } from "./Start"

export const SushiGo = () => {
    const { gameState } = useSushiGoGame();
    return gameState ? <Play /> : <Start />;
}