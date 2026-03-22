import { useNavigate } from 'react-router-dom';
import Logo from '../components/Logo';
import RegisterForm from '../components/RegisterForm';
import LoginImage from '../components/LoginImage';

const Register = () => {
  const navigate = useNavigate();

  return (
    <div className="flex min-h-screen w-full bg-white font-sans text-gray-900">
      <div className="flex w-full flex-col items-center justify-center p-6 md:p-12 lg:w-1/2">
        <div className="w-full max-w-sm">
          <Logo />
          <div className="mb-8 text-center lg:text-left">
            <h1 className="text-4xl font-extrabold text-gray-900">Crie sua conta</h1>
            <p className="mt-2 text-gray-600">Junte-se a nós! Preencha seus dados para começar.</p>
          </div>
          <RegisterForm />
          <div className="mt-8 text-center">
            <p className="text-sm text-gray-600">
              Já tem uma conta?{' '}
              <button
                onClick={() => navigate('/login')}
                className="font-semibold text-orange-500 underline-offset-4 hover:underline"
              >
                Faça seu login
              </button>
            </p>
          </div>
        </div>
      </div>
      <LoginImage />
    </div>
  );
};

export default Register;