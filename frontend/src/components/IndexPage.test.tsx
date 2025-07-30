import React from 'react';
import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import IndexPage from './IndexPage';

describe('IndexPage', () => {
  beforeEach(() => {
    render(
      <MemoryRouter>
        <IndexPage />
      </MemoryRouter>
    );
  });

  test('renders the main heading', () => {
    const headingElement = screen.getByText(/Welcome to the Index Page/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('renders the introductory paragraph', () => {
    const paragraphElement = screen.getByText(/This is a simple React component named IndexPage./i);
    expect(paragraphElement).toBeInTheDocument();
  });

  test('renders the link to the options page', () => {
    const linkElement = screen.getByRole('link', { name: /Go to Options/i });
    expect(linkElement).toBeInTheDocument();
    expect(linkElement).toHaveAttribute('href', '/options');
  });
});
