import { useState } from 'react';
import { Mail, Lock, Eye, EyeOff, Loader2 } from 'lucide-react';
import { useForm } from 'react-hook-form';
import { useLogin } from '../hooks/useLogin';

const LoginForm = () => {
  const [showPassword, setShowPassword] = useState(false);
  const { handleLogin, isLoading, message } = useLogin();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = (data) => {
    handleLogin(data);
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
      {message && <p className="text-red-500 text-sm text-center font-medium">{message}</p>}

      <div className="space-y-2">
        <label htmlFor="email" className="text-sm font-medium text-text-color">
          E-mail
        </label>
        <div
          className={`relative flex items-center rounded-lg border transition-all ${
            errors.email ? 'border-red-500 ring-2 ring-red-200' : 'border-gray-300 focus-within:border-primary focus-within:ring-2 focus-within:ring-orange-200'
          }`}
        >
          <div className="pl-3 text-gray-500">
            <Mail size={20} />
          </div>
          <input
            {...register('email', { required: 'E-mail é obrigatório' })}
            type="email"
            id="email"
            placeholder="email@exemplo.com"
            className="w-full bg-transparent px-4 py-3 text-base outline-none placeholder:text-gray-400"
          />
        </div>
        {errors.email && <p className="text-xs font-semibold text-red-600">{errors.email.message}</p>}
      </div>

      <div className="space-y-2">
        <label htmlFor="password-input" className="text-sm font-medium text-text-color">
          Senha
        </label>
        <div
          className={`relative flex items-center rounded-lg border transition-all ${
            errors.password ? 'border-red-500 ring-2 ring-red-200' : 'border-gray-300 focus-within:border-primary focus-within:ring-2 focus-within:ring-orange-200'
          }`}
        >
          <div className="pl-3 text-gray-500">
            <Lock size={20} />
          </div>
          <input
            {...register('password', { required: 'Senha é obrigatória' })}
            type={showPassword ? 'text' : 'password'}
            id="password-input"
            placeholder="Sua senha"
            className="w-full bg-transparent px-4 py-3 text-base outline-none placeholder:text-gray-400"
          />
          <button
            type="button"
            onClick={() => setShowPassword(!showPassword)}
            className="pr-4 text-gray-500 transition-colors hover:text-gray-700"
          >
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
        {isLoading ? <Loader2 className="mr-2 h-5 w-5 animate-spin" /> : 'Entrar'}
      </button>
    </form>
  );
};

export default LoginForm;