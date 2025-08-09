import React from 'react';
import '../funky.css';
import { Link } from 'react-router-dom';

const IndexPage = () => {
    return (
        <div className="container">
            <h1>Welcome to the Odds Generator App</h1>
            <p>This allows you to grab the latest odds from betfair.</p>
            <Link to="/options" className="button">Go to Options</Link>
        </div>
    );
};

export default IndexPage;
