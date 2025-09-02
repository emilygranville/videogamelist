import { useState } from 'react';
import '../styles/TopNavbar.css';

function TopNavbar() {
  const [count, setCount] = useState(0);

  return (
    <>
        <nav>
            <ul>
                <li>Contact</li>
                <li>About</li>
            </ul>
        </nav>
    </>
  );
}

export default TopNavbar;