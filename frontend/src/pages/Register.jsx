import Logo from '../components/Logo';
import RegisterForm from '../components/RegisterForm';
import LoginImage from '../components/LoginImage';
import { useNavigate } from 'react-router-dom';

const Register = () => {
  const navigate = useNavigate();

  return (
    <div className="flex min-h-screen w-full bg-white font-sans text-gray-900">
      <div className="flex w-full flex-col items-center justify-center p-6 md:p-12 lg:w-1/2">
        <div className="w-full max-w-sm">
          <Logo />
          <div className="mb-8 text-center lg:text-left">
            <h1 className="text-2xl font-bold text-black">Crie sua conta</h1>
            <p className="text-gray-500">Preencha os dados abaixo</p>
          </div>
          <RegisterForm />
          <div className="mt-8 text-center">
            <p className="text-sm text-gray-600">
              Já tem uma conta?{' '}
              <button
                onClick={() => navigate('/login')}
                className="font-semibold text-gray-900 underline underline-offset-4 hover:text-orange-500"
              >
                Fazer login
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