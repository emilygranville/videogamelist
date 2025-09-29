import { useState } from 'react';
import '../styles/VideoGameCard.css';

function VideoGameCard(props) {
    const [count, setCount] = useState(0);

    const consoles = props.consoles.map((x) => {
        return (
            <li className="videoGameCard__listItem" key={x}>{x}</li>
        )
    });

    return (
        <div className="videoGameCard">
            <h2>{props.gameName}</h2>
            <button className="videoGameCard__button">Favorite</button>
            <ul className="videoGameCard__list">{consoles}</ul>
            <button className="videoGameCard__button">Edit</button>
            <button className="videoGameCard__button">Delete</button>
        </div>
    );
}

export default VideoGameCard;