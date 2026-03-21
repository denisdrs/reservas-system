import { BrowserRouter as Router, Routes, Route, Navigate, Outlet } from 'react-router-dom';
import Login from './pages/Login';
import Register from './pages/Register';
import Orders from './pages/Orders';
import Checkout from './pages/Checkout';
import AddProduct from './pages/AddProduct';
import History from './pages/History';
import Navbar from './components/Navbar';

// Layout para rotas protegidas
const ProtectedLayout = () => (
  <div>
    <Navbar />
    <Outlet />
  </div>
);

// Função de Proteção: Só entra se tiver Token
const ProtectedRoute = () => {
  const token = localStorage.getItem('token');
  if (!token) {
    return <Navigate to="/login" replace />;
  }
  return <ProtectedLayout />;
};

function App() {
  return (
    <Router>
      <Routes>
        {/* Rotas Públicas (Qualquer um vê) */}
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        {/* Rotas Protegidas (Só quem logou vê) */}
        <Route element={<ProtectedRoute />}>
          <Route path="/orders" element={<Orders />} />
          <Route path="/checkout" element={<Checkout />} />
          <Route path="/add-product" element={<AddProduct />} />
          <Route path="/history" element={<History />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;