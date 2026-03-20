import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './pages/Login'; 
import Register from './pages/Register'; 
import Orders from './pages/Orders'; 
import Checkout from './pages/Checkout';
import AddProduct from './pages/AddProduct';

function App() {
  return (
    <Router>
      <Routes>
        {/* Rota raiz e rota de login agora levam para o mesmo lugar corretamente */}
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        
        <Route path="/register" element={<Register />} />
        <Route path="/orders" element={<Orders />} />
        <Route path="/checkout" element={<Checkout />} />
        <Route path="/add-product" element={<AddProduct />} />
      </Routes>
    </Router>
  );
}

export default App;