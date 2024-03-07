import { useSushiGoGame } from "../../contexts/SushiGoGameContext";
import { playGame } from "../services/api";
import { isGameState } from '../types';
import './Play.css';



export const Play = () =>  {
  const {gameState, setGameState} = useSushiGoGame();
  const playerOne = gameState!.players[0];
  const playerTwo = gameState!.players[1];
  
  var cardsPlayerOne = playerOne.cards;
  var cardsPlayerTwo = playerTwo.cards;
  var cardsPlayerOneButtons =[];
  var cardsPlayerTwoButtons =[];
  var playerWhoHasTurn = playerOne;

  if(playerOne.hasTurn){
    playerWhoHasTurn = playerOne;
  } else if(playerTwo.hasTurn){
    playerWhoHasTurn = playerTwo;
  }

  for (let i = 0; i < cardsPlayerOne.length; i++){
    if(cardsPlayerOne[i].cardType == "egg"){
      cardsPlayerOneButtons.push(<button key={i} className = "cardsPlayerOneEgg" onClick={() => playCard(i, playerOne.name)}></button>);
    }else if (cardsPlayerOne[i].cardType == "salmon"){
      cardsPlayerOneButtons.push(<button key={i} className = "cardsPlayerOneSalmon" onClick={() => playCard(i, playerOne.name)}></button>);
    }else if (cardsPlayerOne[i].cardType == "squid"){
      cardsPlayerOneButtons.push(<button key={i} className = "cardsPlayerOneSquid" onClick={() => playCard(i, playerOne.name)}></button>);
    }else if (cardsPlayerOne[i].cardType == "dumpling"){
      cardsPlayerOneButtons.push(<button key={i} className = "cardsPlayerOneDumpling" onClick={() => playCard(i, playerOne.name)}></button>);
    }else if (cardsPlayerOne[i].cardType == "makiRol"){
      cardsPlayerOneButtons.push(<button key={i} className = "cardsPlayerOneMakiRol" onClick={() => playCard(i, playerOne.name)}></button>)
    }else if (cardsPlayerOne[i].cardType == "tempura"){
      cardsPlayerOneButtons.push(<button key={i} className = "cardsPlayerOneTempura" onClick={() => playCard(i, playerOne.name)}></button>)
    }else if (cardsPlayerOne[i].cardType == "sashimi"){
      cardsPlayerOneButtons.push(<button key={i} className = "cardsPlayerOneSashimi" onClick={() => playCard(i, playerOne.name)}></button>)
    }else if (cardsPlayerOne[i].cardType == "pudding"){
      cardsPlayerOneButtons.push(<button key={i} className = "cardsPlayerOnePudding" onClick={() => playCard(i, playerOne.name)}></button>)
    }
  }

    for (let i = 0; i < cardsPlayerTwo.length; i++){
      if(cardsPlayerTwo[i].cardType == "egg"){
      cardsPlayerTwoButtons.push(<button key={i} className = "cardsPlayerTwoEgg" onClick={() => playCard(i, playerTwo.name)}></button>); 
      }else if(cardsPlayerTwo[i].cardType == "salmon"){
      cardsPlayerTwoButtons.push(<button key={i} className = "cardsPlayerTwoSalmon" onClick={() => playCard(i, playerTwo.name)}></button>); 
      }else if(cardsPlayerTwo[i].cardType == "squid"){
       cardsPlayerTwoButtons.push(<button key={i} className = "cardsPlayerTwoSquid" onClick={() => playCard(i, playerTwo.name)}></button>);  
      }else if(cardsPlayerTwo[i].cardType == "dumpling"){
       cardsPlayerTwoButtons.push(<button key={i} className = "cardsPlayerTwoDumpling" onClick={() => playCard(i, playerTwo.name)}></button>);  
      }else if (cardsPlayerTwo[i].cardType == "makiRol"){
      cardsPlayerTwoButtons.push(<button key={i} className = "cardsPlayerTwoMakiRol" onClick={() => playCard(i, playerTwo.name)}></button>)
      } else if (cardsPlayerTwo[i].cardType == "tempura"){
      cardsPlayerTwoButtons.push(<button key={i} className = "cardsPlayerTwoTempura" onClick={() => playCard(i, playerTwo.name)}></button>)
      } else if (cardsPlayerTwo[i].cardType == "sashimi"){
      cardsPlayerTwoButtons.push(<button key={i} className = "cardsPlayerTwoSashimi" onClick={() => playCard(i, playerTwo.name)}></button>)
      }else if (cardsPlayerTwo[i].cardType == "pudding"){
      cardsPlayerTwoButtons.push(<button key={i} className = "cardsPlayerTwoPudding" onClick={() => playCard(i, playerTwo.name)}></button>)
      }
  }

  async function playCard(int: number, owner: String){
    const result = await playGame(int, owner); 
    console.log(result);
    console.log(int);

    if(isGameState(result)){
      setGameState(result);
    } else {
      console.log('${result.statusCode} ${result.statusText}');
    }
    return 
  }


 

  return (
    <body>
    <div className = "box">
      <div className = "PlayerNames">

        <div className = "NotActivePlayerInfo">
         <div className = "PlayerName">{playerTwo.hasTurn? ((playerOne.name)):((playerTwo.name))} <br/></div> 
          <div className = "PlayerScore">Score: {playerTwo.hasTurn? ((playerOne.score)):((playerTwo.score))}</div>
              <div className = "nrOfCards" >Dumplings: {playerTwo.hasTurn? ((playerOne.nrOfDumplingPlayed)):((playerTwo.nrOfDumplingPlayed))}</div>
              <div className = "nrOfCards">Nigiri Egg: {playerTwo.hasTurn? ((playerOne.nrOfNigiriEggPlayed)):((playerTwo.nrOfNigiriEggPlayed))}</div>
              <div className = "nrOfCards">Nigiri Salmon: {playerTwo.hasTurn? ((playerOne.nrOfNigiriSalmonPlayed)):((playerTwo.nrOfNigiriSalmonPlayed))}</div>
              <div className = "nrOfCards">Nigiri Squid: {playerTwo.hasTurn? ((playerOne.nrOfNigiriSquidPlayed)):((playerTwo.nrOfNigiriSquidPlayed))}</div>
              <div className = "nrOfCards">Maki roll:{playerTwo.hasTurn? ((playerOne.nrOfMakiRolPlayed)):((playerTwo.nrOfMakiRolPlayed))}</div>
              <div className = "nrOfCards">Tempura:{playerTwo.hasTurn? ((playerOne.nrOfTempuraPlayed)):((playerTwo.nrOfTempuraPlayed))}</div>
              <div className = "nrOfCards">Sashimi:{playerTwo.hasTurn? ((playerOne.nrOfSashimiPlayed)):((playerTwo.nrOfSashimiPlayed))}</div>
              <div className = "nrOfCards">Pudding: {playerTwo.hasTurn? ((playerOne.nrOfPuddingCardsPlayed)):((playerTwo.nrOfPuddingCardsPlayed))}</div>
         </div>
    

          <div className = "ActivePlayerInfo">
         <div className = "PlayerName">{playerOne.hasTurn? ((playerOne.name)):((playerTwo.name))} <br/></div> 
          <div className = "PlayerScore">Score: {playerOne.hasTurn? ((playerOne.score)):((playerTwo.score))}</div>
              <div className = "nrOfCards" >Dumplings: {playerOne.hasTurn? ((playerOne.nrOfDumplingPlayed)):((playerTwo.nrOfDumplingPlayed))}</div>
              <div className = "nrOfCards">Nigiri Egg: {playerOne.hasTurn? ((playerOne.nrOfNigiriEggPlayed)):((playerTwo.nrOfNigiriEggPlayed))}</div>
              <div className = "nrOfCards">Nigiri Salmon: {playerOne.hasTurn? ((playerOne.nrOfNigiriSalmonPlayed)):((playerTwo.nrOfNigiriSalmonPlayed))}</div>
              <div className = "nrOfCards">Nigiri Squid: {playerOne.hasTurn? ((playerOne.nrOfNigiriSquidPlayed)):((playerTwo.nrOfNigiriSquidPlayed))}</div>
              <div className = "nrOfCards">Maki rol: {playerOne.hasTurn? ((playerOne.nrOfMakiRolPlayed)):((playerTwo.nrOfMakiRolPlayed))}</div>
              <div className = "nrOfCards">Tempura: {playerOne.hasTurn? ((playerOne.nrOfTempuraPlayed)):((playerTwo.nrOfTempuraPlayed))}</div>
              <div className = "nrOfCards">Sashimi: {playerOne.hasTurn? ((playerOne.nrOfSashimiPlayed)):((playerTwo.nrOfSashimiPlayed))}</div>
              <div className = "nrOfCards">Pudding: {playerOne.hasTurn? ((playerOne.nrOfPuddingCardsPlayed)):((playerTwo.nrOfPuddingCardsPlayed))}</div>
          </div>
  

      </div>

      <div className = "PlayerCards">

        <div>{playerOne.hasTurn? ((cardsPlayerOneButtons)):((cardsPlayerTwoButtons))}</div>
      </div>


      <div className = "GameStatusMessage">
        {gameState?.gameStatus.endOfGame? ("") : ((playerWhoHasTurn.name) +  ", please select a card to play.")}
      </div>
      <div className = "GameStatusMessage">
        <p>
            {gameState?.gameStatus.endOfGame? (gameState.gameStatus.winner) + " win(s)!": ("")}
        </p>
      </div>
      </div>
</body>
    
  )

  };


