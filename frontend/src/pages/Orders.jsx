import { useEffect, useState } from 'react';
import { useCart } from '../contexts/CartContext.jsx';
import { useNavigate } from 'react-router-dom';

const Orders = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const { addToCart } = useCart();
  const navigate = useNavigate();

  const handleAddToCart = (produto) => {
    addToCart(produto);
    navigate('/checkout');
  };

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch(`${import.meta.env.VITE_API_URL}/api/products`);
        const data = await response.json();
        
        if (response.ok) {
          setProducts(data);
        }
      } catch (error) {
        console.error("Erro de conexão:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchProducts();
  }, []);

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-50">
        <p className="text-orange-600 font-bold animate-bounce">Carregando cardápio real...</p>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50 p-6 font-sans">
      <header className="max-w-6xl mx-auto mb-10 flex justify-between items-center">
        <div>
          <h1 className="text-4xl font-black text-gray-800 italic">BURGER ADS</h1>
          <p className="text-gray-500 font-medium">O sabor da aprovação em cada mordida!</p>
        </div>
      </header>

      <div className="max-w-6xl mx-auto grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
        {products.length > 0 ? (
          products.map((produto) => (
            <div key={produto.id} className="bg-white rounded-3xl overflow-hidden shadow-lg border border-gray-100">
              <div className="h-48 overflow-hidden bg-gray-200">
                <img 
                  src={produto.url || 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd?q=80&w=500'} 
                  alt={produto.name}
                  className="w-full h-full object-cover"
                />
              </div>

              <div className="p-6">
                <h3 className="text-xl font-bold text-gray-800 mb-2">{produto.name}</h3>
                <p className="text-gray-500 text-sm mb-4 line-clamp-2">
                  {produto.description || 'Qualidade Burger Ads.'}
                </p>

                <div className="flex justify-between items-center mt-6">
                  <div>
                    <span className="text-gray-400 text-xs font-bold block uppercase">Preço</span>
                    <span className="text-2xl font-black text-orange-600">
                      R$ {parseFloat(produto.value || 0).toFixed(2).replace('.', ',')}
                    </span>
                  </div>
                  <button onClick={() => handleAddToCart(produto)} className="bg-gray-900 text-white p-3 px-6 rounded-2xl font-bold hover:bg-orange-600 transition">
                    Adicionar
                  </button>
                </div>
              </div>
            </div>
          ))
        ) : (
          <div className="col-span-full text-center py-20">
            <p className="text-gray-400 font-medium text-xl">Nenhum hambúrguer encontrado... 🍔</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default Orders;