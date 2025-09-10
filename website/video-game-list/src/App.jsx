import { useState } from 'react';
import './App.css';
import TopNavbar from "./components/TopNavbar.jsx";
import SideNavbar from "./components/SideNavbar.jsx";
import MainBody from './components/MainBody.jsx';

function App() {
  const [count, setCount] = useState(0);

  // they're all there as a test that
  // they look right for now
  // this is not permanent
  return (
    <div>
        <SideNavbar/>
        <TopNavbar/>
        <MainBody/>
    </div>
  );
}

export default App;