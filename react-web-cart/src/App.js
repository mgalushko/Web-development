import React from 'react';
import './App.css';

import Cart from './components/cart'


function App() 
{
  const automobiles = [{name: 'BMW 4', cost: 3000000}, {name: 'BMW x3', cost: 4000000}, {name: 'BMW x5', cost: 5000000}, {name: 'Mercedes-benz C', cost: 3700000}]

  return (
    <div className="App">

      <Cart automobiles = {automobiles}/>

    </div>
  );
}

export default App;
