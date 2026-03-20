import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { loginSchema } from '../schemas/loginSchema';

export const useLogin = () => {
  const [showPassword, setShowPassword] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

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

    try {
      const response = await fetch('http://3.213.13.51/dev/api/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      });

      if (response.ok) {
        const result = await response.json();
        
        // Aqui está o segredo: Pegamos exatamente o campo "token" que vimos no Insomnia
        // E removemos a palavra "Bearer " se ela vier junto, para deixar só o código
        const tokenLimpo = result.token.replace('Bearer ', '');
        
        localStorage.setItem('token', tokenLimpo);
        
        alert("Login realizado com sucesso! Redirecionando para o cadastro...");
        window.location.href = '/add-product'; 
      } else {
        alert("E-mail ou senha incorretos.");
      }
    } catch (error) {
      console.error('Erro na requisição de login:', error);
      alert("Erro ao conectar com o servidor.");
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
  };
};