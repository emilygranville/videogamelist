import { useState } from 'react';
import '../styles/SideNavbar.css';

function SideNavbar() {
  const [count, setCount] = useState(0);

  return (
    <>
        <nav>
            <button>Add new game</button>
            <ul>
                <li>Save to device</li>
                <li>Load from device (default)</li>
                <li>Save to cloud</li>
                <li>Load from cloud</li>
            </ul>
            <ul>
                <li>Sign up</li>
                <li>Sign in</li>
                <li>Change password</li>
                <li>Reset password</li>
                <li>Sign out</li>
                <li>Delete account</li>
            </ul>
        </nav>
    </>
  );
}

export default SideNavbar;