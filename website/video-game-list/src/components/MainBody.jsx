import { useState } from 'react';
import '../styles/MainBody.css';
import VideoGameCard from "./VideoGameCard.jsx";

function MainBody() {
  const [count, setCount] = useState(0);

  return (
    <>
        <div class="mainBody">
            <div class="mainBody__consoleList">
                <button class="mainBody__consoleList__button">Button</button>
                <button class="mainBody__consoleList__button">Button 2</button>
            </div>
            <div class="mainBody__cardSection">
                <VideoGameCard/>
                <VideoGameCard/>
                <VideoGameCard/>
                <VideoGameCard/>
                <VideoGameCard/>
                <VideoGameCard/>
                <VideoGameCard/>
                <VideoGameCard/>
                <VideoGameCard/>
            </div>
        </div>
    </>
  );
}

export default MainBody;