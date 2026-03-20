import Logo from '../components/Logo';
import LoginForm from '../components/LoginForm';
import LoginImage from '../components/LoginImage';
import { useNavigate } from 'react-router-dom';
const Login = () => {
  const navigate = useNavigate();
  const navigator = () => {
    navigate('/register');
  };

  return (
    <div className="flex min-h-screen w-full bg-white font-sans text-gray-900">
    <div className="flex w-full flex-col items-center justify-center p-6 md:p-12 lg:w-1/2">
      <div className="w-full max-w-sm">
        <Logo />
        <div className="mb-8 text-center lg:text-left">
          <h1 className="text-2xl font-bold text-black">Já tem cadastro?</h1>
          <p className="text-gray-500">Faça seu login</p>
        </div>
        <LoginForm />
        <div className="mt-8 text-center">
          <button className="text-sm font-semibold text-gray-900 underline underline-offset-4 transition-colors hover:text-orange-500" onClick={navigator}>
            Criar conta
          </button>
        </div>
      </div>
    </div>
    <LoginImage />
  </div>
  );
};

export default Login;