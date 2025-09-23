import { useState } from 'react';
import '../styles/MainBody.css';
import VideoGameCard from "./VideoGameCard.jsx";
import data from '../test-data.js'

function MainBody() {
  const [count, setCount] = useState(0);

  const videoGameCards = data.map((x) => {
    return (
        <VideoGameCard
            key={x.gameID}
            {...x}
        />
    )
  });

  return (
    <>
        <section class="mainBody">
            <section class="mainBody__consoleList">
                <button class="mainBody__consoleList__button">Button</button>
                <button class="mainBody__consoleList__button">Button 2</button>
            </section>
            <section class="mainBody__cardSection">{videoGameCards}</section>
        </section>
    </>
  );
}

export default MainBody;