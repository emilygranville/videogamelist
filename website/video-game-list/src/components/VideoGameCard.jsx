import { useState } from 'react';
import '../styles/VideoGameCard.css';

function VideoGameCard() {
  const [count, setCount] = useState(0);

  return (
    <>
        <h2>Game Name</h2>
        <button>Favorite</button>
        <ul>
            <li>Console</li>
            <li>Console</li>
        </ul>
        <button>Edit</button>
        <button>Delete</button>
    </>
  );
}

export default VideoGameCard;