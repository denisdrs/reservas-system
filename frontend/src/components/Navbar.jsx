import { Link, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { ShoppingCart, User, History, LogOut, Package, ClipboardList } from 'lucide-react';
import Logo from './Logo';

const Navbar = () => {
  const [dropdownOpen, setDropdownOpen] = useState(false);
  const navigate = useNavigate();
  const userRole = localStorage.getItem('role');

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    navigate('/login');
  };

  return (
    <nav className="bg-white shadow-lg">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-20">
          <div className="flex-shrink-0">
            <Link to="/orders" className="flex items-center">
              <Logo className="h-10 w-auto" />
            </Link>
          </div>
          <div className="flex items-center space-x-4">
            <Link to="/checkout" className="text-gray-600 hover:text-orange-500 transition-colors duration-300">
              <ShoppingCart className="h-6 w-6" />
            </Link>
            <div className="relative">
              <button
                onClick={() => setDropdownOpen(!dropdownOpen)}
                className="p-2 rounded-full text-gray-600 hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-orange-500"
              >
                <User className="h-6 w-6" />
              </button>
              {dropdownOpen && (
                <div
                  className="absolute right-0 mt-2 w-56 bg-white rounded-lg shadow-xl z-20 origin-top-right"
                  onMouseLeave={() => setDropdownOpen(false)}
                >
                  <div className="py-1">
                    <Link
                      to="/edit-user"
                      className="flex items-center px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                    >
                      <User className="h-5 w-5 mr-3" />
                      Editar Usuário
                    </Link>
                    <Link
                      to="/history"
                      className="flex items-center px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                    >
                      <History className="h-5 w-5 mr-3" />
                      Histórico
                    </Link>
                    {userRole === 'ADMIN' && (
                      <>
                        <Link
                          to="/add-product"
                          className="flex items-center px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                        >
                          <Package className="h-5 w-5 mr-3" />
                          Adicionar Produtos
                        </Link>
                        <Link
                          to="/all-orders"
                          className="flex items-center px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                        >
                          <ClipboardList className="h-5 w-5 mr-3" />
                          Todos os Pedidos
                        </Link>
                      </>
                    )}
                    <button
                      onClick={handleLogout}
                      className="w-full text-left flex items-center px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                    >
                      <LogOut className="h-5 w-5 mr-3" />
                      Deslogar
                    </button>
                  </div>
                </div>
              )}
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
