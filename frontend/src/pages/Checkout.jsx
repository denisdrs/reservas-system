import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useCart } from '../contexts/CartContext.jsx';

const Checkout = () => {
  const navigate = useNavigate();
  const { cart, getCartTotal, getCartForApi, clearCart } = useCart();
  const cartTotal = getCartTotal();

  const [payment, setPayment] = useState('Cartão');
  const [isFinished, setIsFinished] = useState(false);

  const [address, setAddress] = useState('');

  useEffect(() => {
    handleAddress();
  }, []);

  const navigateToOrders = () => {
    navigate('/orders');
  };

  const handleAddress = async () => {
    const token = localStorage.getItem('token');

    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/api/users`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });

      if (response.ok) {
        const data = await response.json();
        setAddress(`${data.address.street}, ${data.address.number} - ${data.address.city}, ${data.address.state}`);
      } else {
        // Handle error
        console.error('Erro ao buscar dados de usuario!');
      }
    } catch (error) {
      console.error('Erro de conexão:', error);
    }
  };

  const handleCheckout = async () => {
    const products = getCartForApi();
    const token = localStorage.getItem('token');

    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/api/orders`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({ products }),
      });

      if (response.ok) {
        setIsFinished(true);
        clearCart();
      } else {
        // Handle error
        console.error("Erro ao finalizar o pedido");
      }
    } catch (error) {
      console.error("Erro de conexão:", error);
    }
  };

  if (cart.length === 0 && !isFinished) {
    return (
      <div className="min-h-screen flex flex-col items-center justify-center p-10 font-sans">
        <p className="text-gray-500 mb-4">Seu carrinho está vazio.</p>
        <button onClick={() => navigate('/orders')} className="bg-orange-500 text-white px-6 py-2 rounded-xl font-bold">Voltar ao cardápio</button>
      </div>
    );
  }

  if (isFinished) {
    return (
      <div className="min-h-screen bg-white flex flex-col items-center justify-center p-6 text-center font-sans">
        <div className="text-6xl mb-4">✅</div>
        <h1 className="text-3xl font-black text-gray-800 tracking-tighter">Pedido Enviado!</h1>
        <p className="text-gray-500 mt-2">Seu hambúrguer já está sendo preparado.</p>
        <button 
          onClick={() => navigate('/orders')}
          className="mt-8 bg-orange-500 text-white px-8 py-3 rounded-2xl font-bold shadow-lg shadow-orange-200"
        >
          Voltar para o Cardápio
        </button>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50 p-6 md:p-12 font-sans">
      <div className="max-w-2xl mx-auto bg-white rounded-[40px] p-10 shadow-sm border border-gray-100">
        <h1 className="text-4xl font-black text-gray-800 mb-10 tracking-tighter italic text-left">Resumo do Pedido</h1>

        <section className="mb-10 text-left">
          <h2 className="text-[10px] font-black text-gray-300 uppercase tracking-[3px] mb-6">Itens Escolhidos</h2>
          <div className="space-y-4">
            {cart.map((item, index) => (
              <div key={index} className="flex justify-between items-center">
                <span className="text-gray-600 font-bold">{item.name}</span>
                <span className="text-gray-900 font-black">R$ {parseFloat(item.value || 0).toFixed(2).replace('.', ',')}</span>
              </div>
            ))}
            <div className="border-t-2 border-dashed border-gray-100 mt-6 pt-6 flex justify-between">
              <span className="text-2xl font-black text-gray-800">Total</span>
              <span className="text-2xl font-black text-orange-500">R$ {cartTotal.toFixed(2).replace('.', ',')}</span>
            </div>
          </div>
        </section>

        <section className="mb-10 text-left">
          <h2 className="text-[10px] font-black text-gray-300 uppercase tracking-[3px] mb-4">Endereço de Entrega</h2>
          <div className="p-5 border-2 border-orange-100 rounded-[25px] bg-orange-50/50 flex items-center gap-4">
            <span className="text-2xl">📍</span>
            <div>
              <p className="font-black text-gray-800 text-sm">{address}</p>
              <p className="text-[10px] font-bold text-orange-400 uppercase tracking-widest mt-1">Endereço de Cadastro</p>
            </div>
          </div>
        </section>

        <section className="mb-12 text-left">
          <h2 className="text-[10px] font-black text-gray-300 uppercase tracking-[3px] mb-4">Pagamento (Somente na entrega)</h2>
          <div className="grid grid-cols-3 gap-3">
            {['Cartão', 'Pix', 'Dinheiro'].map((method) => (
              <button
                key={method}
                onClick={() => setPayment(method)}
                className={`py-4 rounded-2xl font-black text-[10px] uppercase tracking-widest transition-all ${
                  payment === method 
                  ? 'bg-black text-white shadow-xl shadow-gray-200 scale-105' 
                  : 'bg-gray-50 text-gray-400 border border-gray-100 hover:bg-gray-100'
                }`}
              >
                {method}
              </button>
            ))}
          </div>
        </section>

        <button 
          onClick={handleCheckout}
          className="w-full bg-orange-500 text-white py-5 rounded-[25px] font-black text-lg shadow-2xl shadow-orange-200 hover:bg-orange-600 hover:-translate-y-1 transition-all uppercase tracking-tighter"
        >
          Confirmar e Pagar
        </button>
        <button 
        className="mt-8 bg-orange-500 text-white px-8 py-3 rounded-2xl font-bold shadow-lg shadow-orange-200"
        onClick={navigateToOrders}>
            Retornar ao cardapio
        </button>
      </div>
    </div>
  );
};

export default Checkout;