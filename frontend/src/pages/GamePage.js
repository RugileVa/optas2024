import React, { useContext } from 'react';
import { GameContext } from '../GameContext.js';

function GamePage() {
  const { gameId } = useContext(GameContext);

  return (
    <div>
      <h1>Game Page</h1>
      {gameId ? <p>Your Game ID: {gameId}</p> : <p>Loading...</p>}
    </div>
  );
}

export default GamePage;