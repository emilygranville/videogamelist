import { useState } from 'react';
import '../styles/TopNavbar.css';

function TopNavbar() {
  const [count, setCount] = useState(0);

  return (
    <>
        <nav class="topNavBar">
            <ul class="topNavBar__list">
                <li class="topNavBar__listItem">
                    <a class="topNavBar__link" href="">Contact</a>
                </li>
                <li class="topNavBar__listItem">
                    <a class="topNavBar__link" href="">About</a>
                </li>
                <li class="topNavBar__listItem">
                    <a class="topNavBar__link" href="./index.html">Home</a>
                </li>
            </ul>
        </nav>
    </>
  );
}

export default TopNavbar;