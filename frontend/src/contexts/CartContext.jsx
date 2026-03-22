import { createContext, useState, useContext } from 'react';

const CartContext = createContext();

export const useCart = () => useContext(CartContext);

export const CartProvider = ({ children }) => {
  const [cart, setCart] = useState([]);

  const addToCart = (product) => {
    setCart((prevCart) => {
      const existingProduct = prevCart.find((item) => item.id === product.id);
      if (existingProduct) {
        return prevCart.map((item) =>
          item.id === product.id ? { ...item, quantity: item.quantity + 1 } : item
        );
      }
      return [...prevCart, { ...product, quantity: 1 }];
    });
  };

  const getCartTotal = () => {
    return cart.reduce((total, item) => total + item.value * item.quantity, 0);
  };

  const getCartForApi = () => {
    return cart.map(item => ({ id: item.id, quantity: item.quantity }));
  }

  return (
    <CartContext.Provider value={{ cart, addToCart, getCartTotal, getCartForApi }}>
      {children}
    </CartContext.Provider>
  );
};
