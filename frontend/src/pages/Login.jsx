import { useNavigate } from 'react-router-dom';
import Logo from '../components/Logo';
import LoginForm from '../components/LoginForm';
import LoginImage from '../components/LoginImage';

const Login = () => {
  const navigate = useNavigate();

  return (
    <div className="flex min-h-screen w-full bg-white font-sans text-gray-900">
      <div className="flex w-full flex-col items-center justify-center p-6 md:p-12 lg:w-1/2">
        <div className="w-full max-w-sm">
          <Logo />
          <div className="mb-8 text-center lg:text-left">
            <h1 className="text-4xl font-extrabold text-gray-900">Bem-vindo de volta!</h1>
            <p className="mt-2 text-gray-600">Estamos felizes em te ver novamente. Faça seu login para continuar.</p>
          </div>
          <LoginForm />
          <div className="mt-8 text-center">
            <p className="text-sm text-gray-600">
              Ainda não tem uma conta?{' '}
              <button
                onClick={() => navigate('/register')}
                className="font-semibold text-orange-500 underline-offset-4 hover:underline"
              >
                Crie uma agora
              </button>
            </p>
          </div>
        </div>
      </div>
      <LoginImage />
    </div>
  );
};

export default Login;