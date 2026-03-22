import { render, screen, fireEvent } from '@testing-library/react';
import { BrowserRouter as Router } from 'react-router-dom';
import AddProduct from './AddProduct';

describe('AddProduct', () => {
  it('should render the form', () => {
    render(
      <Router>
        <AddProduct />
      </Router>
    );

    expect(screen.getByLabelText('Nome do Prato')).toBeInTheDocument();
    expect(screen.getByLabelText('Preço (R$)')).toBeInTheDocument();
    expect(screen.getByLabelText('Quantidade')).toBeInTheDocument();
    expect(screen.getByLabelText('URL da Imagem')).toBeInTheDocument();
    expect(screen.getByText('CADASTRAR PRODUTO')).toBeInTheDocument();
  });

  it('should update the form data on change', () => {
    render(
      <Router>
        <AddProduct />
      </Router>
    );

    const nameInput = screen.getByLabelText('Nome do Prato');
    fireEvent.change(nameInput, { target: { value: 'Test Product' } });
    expect(nameInput.value).toBe('Test Product');
  });
});