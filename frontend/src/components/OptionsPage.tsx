import React from 'react';
import '../funky.css';
import { Link } from 'react-router-dom';

const OptionsPage = () => {
    return (
        <div className="container">
            <h1>Select an Option</h1>
            <form action="/config" method="post">
                <label htmlFor="options">Choose an option:</label>
                <select id="options" name="options">
                    <option value="option1">Option 1</option>
                    <option value="option2">Option 2</option>
                    <option value="option3">Option 3</option>
                    <option value="option4">Option 4</option>
                </select>
                <div className="button-container">
                    <Link to="/" className="button">Back</Link>
                    <input type="submit" value="Submit" />
                </div>
            </form>
        </div>
    );
};

export default OptionsPage;
