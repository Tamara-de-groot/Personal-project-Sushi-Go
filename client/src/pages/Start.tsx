import { useSushiGoGame } from "../../contexts/SushiGoGameContext"
import { useState } from "react"; 
import { startGame } from "../services/api";
import { isGameState } from "../types";
import { Alert } from "../components/Alert";
import { FloatingInput} from "../components/FloatingInput";
import classNames from "classnames";
import './Start.css';


export const Start = () => {
    const { setGameState } = useSushiGoGame();

    const [player1, setPlayer1] = useState("");
    const [player2, setPlayer2] = useState("");
    const [alert, setAlert] = useState<string | null> (null);
    const valid = player1 !== "" && player2 !== "" && player1 !== player2;

    const onSubmit = async () => {
        const result = await startGame(player1, player2);
        console.log(player1);
        console.log(player2);
        console.log(result);

        if (isGameState(result)){
            setGameState(result);
        }else {
            setAlert('${result.statusCode} ${result.statusText}');
        }
    }
return (
    <div>
        <div className = "completePlayerInputField">
        <h1 className="Welcome">Welcome to Sushi Go!</h1>
        <h2 className="EnterName">Enter the name of both players to start the game</h2>
        
        {alert && <Alert text={alert} onClick={() => setAlert(null)} />}
        <form>

                <div className = "playerInputField">
                    <div className="input">
                        <FloatingInput 
                            id="player1"
                            label="Name of player 1"
                            value={player1}
                            onChange={e => setPlayer1(e.target.value)}
                            hasError={player1 !== "" && player2 !== "" && player1 === player2}
                        />                      
                    </div>
                    <div className="input">
                        <FloatingInput 
                            id="player2"
                            label="Name of player 2"
                            value={player2}
                            onChange={e => setPlayer2(e.target.value)}
                            hasError={player1 !== "" && player2 !== "" && player1 === player2}
                        />                      
                    </div>
                    <div>
                        {player1 && player2 && player1 === player2 && <p className="mt-2 text sm text-red-400">
                            <span className="font-medium">Names must be unique</span>
                            </p>}
                    </div>
                    </div>
                    <div className="PlayButton">
                                    <button
                                        type="button"
                                        className="button"
                                        data-te-ripple-init
                                        data-te-ripple-color="light"
                                        disabled={!valid}
                                        onClick={() => onSubmit()}
                                    >
                                        Play 
                                    </button>
                                    </div>
                                    
                                    

                
        </form>
        </div> 

    </div>
)

}