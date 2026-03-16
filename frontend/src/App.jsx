import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Login from './pages/Login';
import Register from './pages/Register';

function App() {
  //Usar esse protectedRoute quando tivermos rotas que só podem ser acessadas se tiver logado 
  const ProtectedRoute = ({redirectPath = '/login' }) => {
  const token = localStorage.getItem('token');
  
  if (token === null) {
    return <Navigate to={redirectPath} replace />;
  }

  return <Outlet />;
};

  return (
    <BrowserRouter>
      <Routes>
        {/* Rota para o Login */}
        <Route path="/login" element={<Login />} />
        
        {/* Rota para o seu novo Cadastro */}
        <Route path="/register" element={<Register />} />

        {/* Redireciona qualquer caminho vazio para o login por padrão */}
        <Route path="/" element={<Navigate to="/login" />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;