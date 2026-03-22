import { BrowserRouter as Router, Routes, Route, Navigate, Outlet } from 'react-router-dom';
import Login from './pages/Login';
import Register from './pages/Register';
import Orders from './pages/Orders';
import Checkout from './pages/Checkout';
import AddProduct from './pages/AddProduct';
import History from './pages/History';
import Navbar from './components/Navbar';
import AllOrders from './pages/AllOrders';

// Layout para rotas de usuário
const UserLayout = () => (
  <div>
    <Navbar />
    <Outlet />
  </div>
);

// Layout para rotas de admin
const AdminLayout = () => (
  <div>
    <Navbar />
    <Outlet />
  </div>
);

// Rota protegida para usuários logados
const ProtectedRoute = () => {
  const token = localStorage.getItem('token');
  return token ? <UserLayout /> : <Navigate to="/login" replace />;
};

// Rota protegida para admins
const AdminRoute = () => {
  const token = localStorage.getItem('token');
  const role = localStorage.getItem('role');
  
  if (!token || role !== 'ADMIN') {
    return <Navigate to="/orders" replace />;
  }
  
  return <AdminLayout />;
};

function App() {
  return (
    <Router>
      <Routes>
        {/* Rotas Públicas */}
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        {/* Rotas de Usuário */}
        <Route element={<ProtectedRoute />}>
          <Route path="/orders" element={<Orders />} />
          <Route path="/checkout" element={<Checkout />} />
          <Route path="/history" element={<History />} />
        </Route>
        
        {/* Rotas de Admin */}
        <Route element={<AdminRoute />}>
          <Route path="/add-product" element={<AddProduct />} />
          <Route path="/all-orders" element={<AllOrders />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;