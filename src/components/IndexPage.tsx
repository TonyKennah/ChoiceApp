import React from 'react';
import '../funky.css';
import { Link } from 'react-router-dom';

const IndexPage = () => {
    return (
        <div className="container">
            <h1>Welcome to the Index Page</h1>
            <p>This is a simple React component named IndexPage.</p>
            <Link to="/options" className="button">Go to Options</Link>
        </div>
    );
};

export default IndexPage;
