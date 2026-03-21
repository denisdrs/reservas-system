import { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Conferido: com "t"

export const useLogin = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (data) => {
    setIsLoading(true);
    setMessage('');

    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/api/auth/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json' 
        }, // Removi a vírgula que estava sozinha na linha 18
        body: JSON.stringify(data),
      });

      if (response.ok) {
        const result = await response.json();
        const tokenLimpo = result.token.replace('Bearer ', '');
        localStorage.setItem('token', tokenLimpo);
        
        console.log('Login bem-sucedido:', result);
        setMessage("Login efetuado com sucesso!");
        navigate('/orders'); 
      } else {
        setMessage("Email ou senha incorretos.");
      }
    } catch (error) {
      console.error('Erro:', error);
      setMessage("Erro de conexão. Redirecionando...");
      navigate('/orders'); 
    } finally {
      setIsLoading(false);
    }
  };

  return { handleLogin, isLoading, message };
};