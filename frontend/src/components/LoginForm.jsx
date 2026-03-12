import { Mail, Lock, Eye, EyeOff, Loader2 } from 'lucide-react';
import { useLogin } from '../hooks/useLogin';

const LoginForm = () => {
  const {
    showPassword,
    setShowPassword,
    isLoading,
    register,
    handleSubmit,
    errors,
  } = useLogin();

  return (
    <form onSubmit={handleSubmit} className="space-y-5">
      <div className="space-y-1">
        <div className={`relative flex items-center rounded-md border bg-white transition-all ${errors.email ? 'border-red-500 ring-1 ring-red-500' : 'border-gray-300 focus-within:border-orange-500 focus-within:ring-1 focus-within:ring-orange-500'}`}>
          <div className="flex items-center pl-3 text-gray-800">
            <Mail size={20} />
          </div>
          <input
            {...register('email')}
            type="email"
            placeholder="email@gmail.com"
            className="w-full bg-transparent px-3 py-3 text-sm outline-none placeholder:text-gray-400"
          />
        </div>
        {errors.email && (
          <p className="text-xs font-medium text-red-500">{errors.email.message}</p>
        )}
      </div>

      <div className="space-y-1">
        <div className={`relative flex items-center rounded-md border bg-white transition-all ${errors.password ? 'border-red-500 ring-1 ring-red-500' : 'border-gray-300 focus-within:border-orange-500 focus-within:ring-1 focus-within:ring-orange-500'}`}>
          <div className="flex items-center pl-3 text-gray-800">
            <Lock size={20} />
          </div>
          <input
            {...register('password')}
            type={showPassword ? 'text' : 'password'}
            placeholder="********"
            className="w-full bg-transparent px-3 py-3 text-sm outline-none placeholder:text-gray-400"
          />
          <button
            type="button"
            onClick={() => setShowPassword(!showPassword)}
            className="pr-3 text-gray-400 transition-colors hover:text-gray-600"
          >
            {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
          </button>
        </div>
        {errors.password && (
          <p className="text-xs font-medium text-red-500">{errors.password.message}</p>
        )}
      </div>

      <button
        type="submit"
        disabled={isLoading}
        className="flex w-full items-center justify-center rounded-md bg-[#222222] py-3 text-sm font-semibold text-white transition-all hover:bg-black active:scale-[0.98] disabled:cursor-not-allowed disabled:opacity-70"
      >
        {isLoading ? (
          <Loader2 className="mr-2 h-4 w-4 animate-spin" />
        ) : (
          'Entrar'
        )}
      </button>
    </form>
  );
};

export default LoginForm;