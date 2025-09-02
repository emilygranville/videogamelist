import { useState } from 'react';
import './App.css';
import VideoGameCard from "./components/VideoGameCard.jsx";
import TopNavbar from "./components/TopNavbar.jsx";
import SideNavbar from "./components/SideNavbar.jsx";

function App() {
  const [count, setCount] = useState(0);

  // they're all there as a test that
  // they look right for now
  // this is not permanent
  return (
    <div>
        <TopNavbar/>
        <VideoGameCard/>
        <SideNavbar/>
    </div>
  );
}

export default App;