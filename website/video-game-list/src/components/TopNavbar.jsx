import { useState } from 'react';
import '../styles/TopNavbar.css';

function TopNavbar() {
    const [count, setCount] = useState(0);

    return (
        <nav className="topNavBar">
            <ul className="topNavBar__list">
                <li className="topNavBar__listItem">
                    <a className="topNavBar__link" href="">Contact</a>
                </li>
                <li className="topNavBar__listItem">
                    <a className="topNavBar__link" href="">About</a>
                </li>
                <li className="topNavBar__listItem">
                    <a className="topNavBar__link" href="./index.html">Home</a>
                </li>
            </ul>
        </nav>
    );
}

export default TopNavbar;