import { useState } from 'react';
import '../styles/VideoGameCard.css';

function VideoGameCard() {
  const [count, setCount] = useState(0);

  return (
    <div class="videoGameCard">
        <h2>Game Name</h2>
        <button class="videoGameCard__button">Favorite</button>
        <ul class="videoGameCard__list">
            <li class="videoGameCard__listItem">Console</li>
            <li class="videoGameCard__listItem">Console</li>
        </ul>
        <button class="videoGameCard__button">Edit</button>
        <button class="videoGameCard__button">Delete</button>
    </div>
  );
}

export default VideoGameCard;