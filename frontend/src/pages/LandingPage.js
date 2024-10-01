import React, { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './LandingPage.css'; 
import { GameContext } from '../GameContext.js'; 
import {START_GAME_URL} from '../constants.js'


function LandingPage() {

  const navigate = useNavigate();
  const { setGameId } = useContext(GameContext);

  const startGame = async () => {
    try {
      // Fetching the gameId only when the "Start Game" button is clicked
      const response = await fetch(START_GAME_URL, {
        method: 'GET', 
        headers: {
          'Content-Type': 'application/json',
        },
      });
      
      if (!response.ok) {
        throw new Error('Failed to start the game');
      }

      const data = await response.json();

      // Assuming `gameId` is returned in the response
      const gameId = data.gameId;
      setGameId(gameId); // Store the gameId in the context or localStorage

      // Navigate to the game page
      navigate('/game');
    } catch (error) {
      console.error('Error starting the game:', error);
    }
  };

  return (
    <div className="landing-container">
    <h1 className="landing-title">Welcome to Battleship!</h1>
    <p className="landing-description">
      Click the button below to start playing.
    </p>
    <button className="start-button" onClick={startGame}>
      Start Game
    </button>
    </div>
  );
}

export default LandingPage;
