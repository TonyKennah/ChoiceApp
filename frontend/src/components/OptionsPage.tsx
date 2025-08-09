import React, { useState } from 'react';
import '../funky.css';
import { Link } from 'react-router-dom';

const OptionsPage = () => {
    const [selectedOption, setSelectedOption] = useState('UK');
    const [serverResponse, setServerResponse] = useState<any | null>(null);

    const handleChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setSelectedOption(event.target.value);
    };

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault(); // This is the most important part! It stops the browser from navigating.

        const formData = new URLSearchParams();
        formData.append('options', selectedOption);

        try {
            const response = await fetch('/config', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: formData,
            });

            if (response.ok) {
                const result = await response.json();
                setServerResponse(result);
            } else {
                // The server returned an error. Let's try to display its response body.
                const errorBody = await response.text();
                try {
                    // Most APIs return JSON, even for errors. Try to parse it.
                    const errorJson = JSON.parse(errorBody);
                    setServerResponse(errorJson);
                } catch (e) {
                    // If it's not JSON (e.g., an HTML error page), show the raw text.
                    setServerResponse({
                        status: 'error',
                        statusCode: response.status,
                        body: errorBody,
                    });
                }
            }
        } catch (error) {
            setServerResponse({
                status: 'error',
                message: 'A client-side error occurred while submitting the form.',
                errorDetails: (error as Error).message,
            });
        }
    };

    return (
        <div className="container">
            <h1>Select an Option</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="options">Choose an option:</label>
                <select id="options" name="options" value={selectedOption} onChange={handleChange}>
                    <option value="UK">United Kingdom & Northern Ireland</option>
                    <option value="FR">France</option>
                    <option value="ZA">South Africa</option>
                    <option value="AE">United Arab Emirates</option>
                </select>
                <div className="button-container">
                    <Link to="/" className="button">Back</Link>
                    <button type="submit" className="button">Submit</button>
                </div>
            </form>
            {serverResponse && (
                <div className="response-container">
                    <h2>Selected Odds:</h2>
                    <pre>
                        {JSON.stringify(serverResponse, null, 2)}
                    </pre>
                </div>
            )}
        </div>
    );
};

export default OptionsPage;
