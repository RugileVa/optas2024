import React, { useState, useEffect } from 'react';
// import GameBoard from './GameBoard';
import {START_GAME_URL} from '../constants.js';
// import Buttons from './Buttons';
// import './Game.css'; 
 
function GamePage() {
  const [gameId, setGameId] = useState(null);
  const [playerBoard, setPlayerBoard] = useState([]);
//   const [gameStatus, setGameStatus] = useState('');
//   const [shotsRemaining, setShotsRemaining] = useState(25);

  useEffect(() => {
    startGame();
  }, []);

  const startGame = async () => {
    // try {
    //   const response = await fetch(START_GAME_URL, {
    //     method: 'GET',
    //     credentials: 'include',
    //   });
    //   const data = await response.json();
    //   setGameId(data.data.gameId);
    //   setPlayerBoard(data.data.playerBoard);
    // //   setGameStatus(data.message);
    // //   setShotsRemaining(25);
    // } catch (error) {
    //   console.error("Error starting the game:", error);
    // }
  };

  return (
    <div className="game-page">
      <h1>Battleship Game</h1>
      {/* <h3>{gameStatus}</h3>
      <p>Shots Remaining: {shotsRemaining}</p>
      <GameBoard board={playerBoard} setBoard={setPlayerBoard} />
      <Buttons 
        onClear={() => setPlayerBoard([])} 
        onCheck={() => alert('Check!')} 
        onGetSolution={() => alert('Solution!')} 
      /> */}
    </div>
  );
}

export default GamePage;
