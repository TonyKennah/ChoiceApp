import React from 'react';
import './funky.css';
import { Route, Routes } from 'react-router-dom';
import IndexPage from './components/IndexPage';
import OptionsPage from './components/OptionsPage';

function App() {
  return (
      <Routes>
        <Route path="/" element={<IndexPage />} />
        <Route path="/options" element={<OptionsPage />} />
      </Routes>
  );
}

export default App;
