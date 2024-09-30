import React from 'react';
import { useNavigate } from 'react-router-dom';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './LandingPage.css';  

function LandingPage() {
  const navigate = useNavigate();

  const startGame = () => {
    navigate('/game');
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
