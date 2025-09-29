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

    // dealing with the console list buttons
    const consoleSet = new Set(["Favorites"]);
    data.map((x) => {
        for (const console of x.consoles) {
            consoleSet.add(console);
        }
    });
    const consoleButtons = Array.from(consoleSet).map((x) => {
        return (
            <li className="mainBody__consoleListSection__item">
                <a href="" className="mainBody__consoleListSection__itemLink">{x}</a>
            </li>
            // <button className="mainBody__consoleList__button">{x}</button>
        )
    });

    return (
        <>
            <section className="mainBody">
                <section className="mainBody__consoleSection">
                    <ul className="mainBody__consoleList">{consoleButtons}</ul>
                </section>
                <section className="mainBody__cardSection">{videoGameCards}</section>
            </section>
        </>
    );
}

export default MainBody;