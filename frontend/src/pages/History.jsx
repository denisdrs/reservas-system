import { useEffect, useState } from 'react';

const History = () => {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchOrders = async () => {
      const token = localStorage.getItem('token');
      try {
        const response = await fetch(`${import.meta.env.VITE_API_URL}/api/orders`, {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        const data = await response.json();
        
        if (response.ok) {
          setOrders(data);
        }
      } catch (error) {
        console.error("Erro de conexão:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchOrders();
  }, []);

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-50">
        <p className="text-orange-600 font-bold animate-bounce">Carregando histórico...</p>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50 p-6 font-sans">
      <div className="max-w-4xl mx-auto">
        <h1 className="text-3xl font-bold text-gray-800 mb-6">Histórico de Pedidos</h1>
        {orders.length > 0 ? (
          <div className="space-y-4">
            {orders.map((order) => (
              <div key={order.id} className="bg-white p-4 rounded-lg shadow">
                <div className="flex justify-between">
                  <span className="font-bold">Pedido #{order.id}</span>
                  <span className="text-gray-500">{new Date(order.createdAt).toLocaleDateString()}</span>
                </div>
                <div className="mt-2">
                  {order.products.map(product => (
                    <div key={product.id} className="flex justify-between">
                      <span>{product.name}</span>
                      <span>Qtd: {product.quantity}</span>
                    </div>
                  ))}
                </div>
                <div className="text-right font-bold mt-2">
                  Total: R$ {order.total.toFixed(2)}
                </div>
              </div>
            ))}
          </div>
        ) : (
          <p>Nenhum pedido encontrado.</p>
        )}
      </div>
    </div>
  );
};

export default History;
