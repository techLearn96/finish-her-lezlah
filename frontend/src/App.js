import React, { useState } from 'react';
import axios from 'axios';
import './App.css';

function App() {

//const lezlahCooldowns = [
//  "Lezlah says: Girl, you're doing too much. Take 60 seconds.",
//  "Lezlah says: I said stop tapping me. You thirsty.",
//  "Lezlah says: Rate limit exceeded and I’m judging you.",
//  "Lezlah says: You’re lucky I even let you send the first one.",
//  "Lezlah says: Do I look like Postman to you? WAIT.",
//  "Lezlah says: I’m not mad, I’m just rate-limiting your desperation.",
//  "Lezlah says: Sit down. Meditate. Breathe. Roast later.",
//  "Lezlah says: You’re gonna break your own keyboard before you break me.",
//  "Lezlah says: You’ve hit max roast energy. Come back with snacks.",
//  "Lezlah says: I’m in my cool-down era. Try again in 60s."
//];
//
//const getRandomCooldownMessage = () => {
//  const index = Math.floor(Math.random() * lezlahCooldowns.length);
//  return lezlahCooldowns[index];
//};


  const [roast, setRoast] = useState('She ain’t ready.');
  const [error, setError] = useState('');
  const [cooldown, setCooldown] = useState(0);
  const [isCoolingDown, setIsCoolingDown] = useState(false);

  const fetchRoast = async () => {
    if (isCoolingDown) return;

    try {
      const roastOptions = {
        LEZLAHSAYS: 18,
        FOREHEAD: 5,
        LIKETOHATE: 4,
        FAKEQUEEN: 16,
        WANNABESTRAIGHT: 5,
        LOSERBRAIDKNUCKS: 3
      };

      const categories = Object.keys(roastOptions);
      const randomCategory = categories[Math.floor(Math.random() * categories.length)];
      const maxIndex = roastOptions[randomCategory];
      const randomIndex = Math.floor(Math.random() * maxIndex);

      setRoast('Loading roast...');
      setError('');

      const response = await axios.get('https://finish-her-lezlah-production.up.railway.app/api/v1/roast/roast', {
        params: {
          category: randomCategory,
          index: randomIndex
        }
      });

      setTimeout(() => {
        setRoast(response.data);
      }, 1000);

    } catch (err) {
      if (err.response) {
        const status = err.response.status;

        if (status === 429) {
//          setError(`Lezlah needs space. Try again in ${cooldown}s.`);
          setRoast('');
          setIsCoolingDown(true);
          setCooldown(60);

          const countdownInterval = setInterval(() => {
            setCooldown(prev => {
              if (prev <= 1) {
                clearInterval(countdownInterval);
                setIsCoolingDown(false);
                setError('');
                return 0;
              }
              return prev - 1;
            });
          }, 1000);

        } else if (status === 400) {
          const msg = err.response.data?.message || "Roast category or index was invalid.";
          setError(`🛑 ${msg}`);
          setRoast('');
        } else {
          setError("Girl, will be right back something went wrong");
          setRoast('');
        }
      } else {
        console.error("Unhandled Error:", err);
        setError("Girl, hold up, I need to go cuss somebody out.");
        setRoast('');
      }
    }
  };

  return (
    <div className="container">
      <h1 className="title-drip">
        <span className="blood-container">
          <span className="blood-emoji">🩸</span>
          <span className="blood-drip drip-1">🩸</span>
          <span className="blood-drip drip-2">🩸</span>
        </span>
        Roast LoserBraids™
      </h1>

      <div className="roast-section">
        <button
          className="roast-button"
          onClick={fetchRoast}
          disabled={isCoolingDown}
        >
          {isCoolingDown ? `Cooldown (${cooldown}s)` : 'Roast Her'}
        </button>

        {/* Countdown message */}
        {isCoolingDown && (
          <p className="roast-text error">
            Lezlah needs space. Try again in {cooldown}s.
          </p>
        )}

        {/* Error or Roast message */}
        {error && <p className="roast-text error">{error}</p>}
        {!error && roast && <p className="roast-text">{roast}</p>}

        {/* SVG Blood Trail */}
        <svg className="blood-drip-svg" viewBox="0 0 30 30">
          <path d="M15 0 C18 10, 12 20, 15 30" fill="red" />
        </svg>
      </div>

      <footer className="footer">
        <p>© {new Date().getFullYear()} Ms. Incognito. All roasts reserved.</p>
      </footer>
    </div>
  );
}

export default App;
