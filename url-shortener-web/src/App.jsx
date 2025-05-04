import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <h1 className="text-3xl font-bold underline text-center bg-gradient-to-r from-blue-500 to-purple-600 p-4 rounded-lg shadow-lg">
        Hello Vite + React!
      </h1>
    </>
  )
}

export default App
