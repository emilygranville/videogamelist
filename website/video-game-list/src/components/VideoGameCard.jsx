import { useState } from 'react';
import '../styles/VideoGameCard.css';

function VideoGameCard() {
  const [count, setCount] = useState(0);

  return (
    <div class="videoGameCard">
        <h2>Game Name</h2>
        <button>Favorite</button>
        <ul>
            <li>Console</li>
            <li>Console</li>
        </ul>
        <button>Edit</button>
        <button>Delete</button>
    </div>
  );
}

export default VideoGameCard;