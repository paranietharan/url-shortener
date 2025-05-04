import { BrowserRouter, Routes, Route } from 'react-router-dom'
import './App.css'
import LandingPage from './components/LandingPage'
import About from './components/About'

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/about" element={<About />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
