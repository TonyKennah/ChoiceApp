import React from 'react';
import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import OptionsPage from './OptionsPage';

describe('OptionsPage', () => {
  beforeEach(() => {
    render(
      <MemoryRouter>
        <OptionsPage />
      </MemoryRouter>
    );
  });

  test('renders the main heading', () => {
    const headingElement = screen.getByText(/Select an Option/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('renders the form elements', () => {
    const labelElement = screen.getByText(/Choose an option:/i);
    const selectElement = screen.getByRole('combobox');
    expect(labelElement).toBeInTheDocument();
    expect(selectElement).toBeInTheDocument();
  });

  test('renders the Back and Submit buttons', () => {
    const backButton = screen.getByRole('link', { name: /Back/i });
    const submitButton = screen.getByRole('button', { name: /Submit/i });
    expect(backButton).toBeInTheDocument();
    expect(backButton).toHaveAttribute('href', '/');
    expect(submitButton).toBeInTheDocument();
  });
});
