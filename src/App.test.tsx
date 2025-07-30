import React from 'react';
import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import App from './App';

describe('App routing', () => {
  test('renders the IndexPage on the root route', () => {
    render(
      <MemoryRouter initialEntries={['/']}>
        <App />
      </MemoryRouter>
    );
    const headingElement = screen.getByText(/Welcome to the Index Page/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('renders the OptionsPage on the /options route', () => {
    render(
      <MemoryRouter initialEntries={['/options']}>
        <App />
      </MemoryRouter>
    );
    const headingElement = screen.getByText(/Select an Option/i);
    expect(headingElement).toBeInTheDocument();
  });
});
