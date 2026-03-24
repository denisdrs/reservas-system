import { useState, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { editUserSchema } from '../schemas/editUserSchema';
import { Mail, Lock, Eye, EyeOff, Loader2, User, MapPin } from 'lucide-react';
import { useNavigate } from 'react-router-dom';

const EditUserForm = () => {
  const [showPassword, setShowPassword] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const {
    register,
    handleSubmit,
    setValue,
    formState: { errors },
  } = useForm({
    resolver: zodResolver(editUserSchema),
  });

  useEffect(() => {
    console.log('Form errors:', errors);
  }, [errors]);

  useEffect(() => {
    const fetchUserData = async () => {
      const token = localStorage.getItem('token');
      if (token) {
        try {
          const response = await fetch(`${import.meta.env.VITE_API_URL}/api/users`, {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          });
          if (response.ok) {
            const user = await response.json();
            setValue('name', user.name);
            setValue('email', user.email);
            if (user.address) {
              setValue('street', user.address.street);
              setValue('number', user.address.number);
              setValue('city', user.address.city);
              setValue('state', user.address.state);
            }
          }
        } catch (error) {
          console.error('Failed to fetch user data', error);
        }
      }
    };
    fetchUserData();
  }, [setValue]);

  const onSubmit = async (data) => {
    console.log('Submitting data:', data);
    setIsLoading(true);
    setMessage('');
    const token = localStorage.getItem('token');
    const payload = {
      name: data.name,
      address: {
        street: data.street,
        number: parseInt(data.number, 10),
        city: data.city,
        state: data.state,
      },
    };

    if (data.password) {
      payload.password = data.password;
    }

    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/api/users`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(payload),
      });

      if (response.ok) {
        setMessage('Dados atualizados com sucesso!');
        setTimeout(() => navigate('/'), 2000);
      } else {
        const errorData = await response.json();
        setMessage(errorData.message || 'Erro ao atualizar. Tente novamente.');
      }
    } catch (error) {
      setMessage('Erro de conexão. Verifique sua rede.');
    } finally {
      setIsLoading(false);
    }
  };

  const Input = ({ name, type, placeholder, icon, registerOptions, error, disabled }) => (
    <div className="space-y-2">
      <div
        className={`relative flex items-center rounded-lg border transition-all ${
          error ? 'border-red-500 ring-2 ring-red-200' : 'border-gray-300 focus-within:border-primary focus-within:ring-2 focus-within:ring-orange-200'
        }`}
      >
        <div className="pl-3 text-gray-500">{icon}</div>
        <input
          {...register(name, registerOptions)}
          type={type}
          placeholder={placeholder}
          className="w-full bg-transparent px-4 py-3 text-base outline-none placeholder:text-gray-400"
          disabled={disabled}
        />
      </div>
      {error && <p className="text-xs font-semibold text-red-600">{error.message}</p>}
    </div>
  );

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
      {message && <p className={`text-sm text-center font-medium ${message.includes('sucesso') ? 'text-green-600' : 'text-red-600'}`}>{message}</p>}

      <Input name="name" type="text" placeholder="Nome Completo" icon={<User size={20} />} registerOptions={{ required: 'Nome é obrigatório' }} error={errors.name} />
      <Input name="email" type="email" placeholder="E-mail" icon={<Mail size={20} />} registerOptions={{ required: 'E-mail é obrigatório' }} error={errors.email} disabled />

      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        <Input name="street" type="text" placeholder="Rua" icon={<MapPin size={20} />} registerOptions={{ required: 'Rua é obrigatória' }} error={errors.street} />
        <Input name="number" type="number" placeholder="Número" icon={<MapPin size={20} />} registerOptions={{ required: 'Número é obrigatório' }} error={errors.number} />
        <Input name="city" type="text" placeholder="Cidade" icon={<MapPin size={20} />} registerOptions={{ required: 'Cidade é obrigatória' }} error={errors.city} />
        <Input name="state" type="text" placeholder="Estado" icon={<MapPin size={20} />} registerOptions={{ required: 'Estado é obrigatório' }} error={errors.state} />
      </div>

      <div className="space-y-2">
        <div
          className={`relative flex items-center rounded-lg border transition-all ${
            errors.password ? 'border-red-500 ring-2 ring-red-200' : 'border-gray-300 focus-within:border-primary focus-within:ring-2 focus-within:ring-orange-200'
          }`}
        >
          <div className="pl-3 text-gray-500"><Lock size={20} /></div>
          <input
            {...register('password')}
            type={showPassword ? 'text' : 'password'}
            placeholder="Nova Senha (ou senha antiga caso queira manter a mesma)"
            className="w-full bg-transparent px-4 py-3 text-base outline-none placeholder:text-gray-400"
          />
          <button type="button" onClick={() => setShowPassword(!showPassword)} className="pr-4 text-gray-500 hover:text-gray-700">
            {showPassword ? <EyeOff size={20} /> : <Eye size={20} />}
          </button>
        </div>
        {errors.password && <p className="text-xs font-semibold text-red-600">{errors.password.message}</p>}
      </div>

      <button
        type="submit"
        disabled={isLoading}
        className="flex w-full items-center justify-center rounded-lg bg-primary py-3 text-base font-semibold text-white transition-all hover:bg-orange-600 active:scale-[0.99] disabled:cursor-not-allowed disabled:bg-opacity-70"
      >
        {isLoading ? <Loader2 className="mr-2 h-5 w-5 animate-spin" /> : 'Salvar Alterações'}
      </button>
    </form>
  );
};

export default EditUserForm;