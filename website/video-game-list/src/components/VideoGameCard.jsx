import { useState } from 'react';
import '../styles/VideoGameCard.css';

function VideoGameCard(props) {
  const [count, setCount] = useState(0);

  const consoles = props.consoles.map((x) => {
    return (
        <li class="videoGameCard__listItem">{x}</li>
    )
  });

  return (
    <div class="videoGameCard">
        <h2>{props.gameName}</h2>
        <button class="videoGameCard__button">Favorite</button>
        <ul class="videoGameCard__list">{consoles}</ul>
        <button class="videoGameCard__button">Edit</button>
        <button class="videoGameCard__button">Delete</button>
    </div>
  );
}

export default VideoGameCard;