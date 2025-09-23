import { useState } from 'react';
import '../styles/SideNavbar.css';

function SideNavbar() {
  const [count, setCount] = useState(0);

  return (
    <nav class="sideNavBar">
        <ul class="sideNavBar__list">
            <li class="sideNavBar__listItem">
                <a class="sideNavBar__link" href="">Add new game</a>
            </li>
        </ul>
        <ul class="sideNavBar__list">
            <li class="sideNavBar__listItem">   
                <a class="sideNavBar__link" href="">Save to device</a>
            </li>
            <li class="sideNavBar__listItem">
                <a class="sideNavBar__link" href="">Load from device (default)</a>
            </li>
            <li class="sideNavBar__listItem">
                <a class="sideNavBar__link" href="">Save to cloud</a>
            </li>
            <li class="sideNavBar__listItem">
                <a class="sideNavBar__link" href="">Load from cloud</a>
            </li>
        </ul>
        <ul class="sideNavBar__list">
            <li class="sideNavBar__listItem">
                <a class="sideNavBar__link" href="">Sign up</a>
            </li>
            <li class="sideNavBar__listItem">
                <a class="sideNavBar__link" href="">Sign in</a>
            </li>
            <li class="sideNavBar__listItem">
                <a class="sideNavBar__link" href="">Change password</a>
            </li>
            <li class="sideNavBar__listItem">
                <a class="sideNavBar__link" href="">Reset password</a>
            </li>
            <li class="sideNavBar__listItem">
                <a class="sideNavBar__link" href="">Sign out</a>
            </li>
            <li class="sideNavBar__listItem">
                <a class="sideNavBar__link" href="">Delete account</a>
            </li>
        </ul>
    </nav>
  );
}

export default SideNavbar;