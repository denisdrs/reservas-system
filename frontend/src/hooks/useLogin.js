import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { loginSchema } from '../schemas/loginSchema';
import { useNavigate } from 'react-router-dom';

export const useLogin = () => {
  const [showPassword, setShowPassword] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    resolver: zodResolver(loginSchema),
  });

  const onSubmit = async (data) => {
    setIsLoading(true);
    console.log('Dados do Login:', data);
    setMessage('')
    try {
      const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const result = await response.json();
      localStorage.setItem("token", result.token);
      console.log('Login successful:', result);
      setMessage("Login efetuado com sucesso!")
      navigate('/cardapio');
    } catch (error) {
      setMessage("Email ou password incorretos, tente novamente!")
      console.error('There was a problem with the login request:', error);
    } finally {
      setIsLoading(false);
    }
  };

  return {
    showPassword,
    setShowPassword,
    isLoading,
    register,
    handleSubmit: handleSubmit(onSubmit),
    errors,
    message,
    navigator
  };
};