import { useNavigate } from 'react-router-dom';

const CartTotal = ({ total, count, cart }) => {
  const navigate = useNavigate();

  // Se não tiver itens, o balão nem aparece
  if (count === 0) return null;

  return (
    <div className="fixed bottom-8 left-1/2 -translate-x-1/2 bg-black text-white p-4 rounded-3xl shadow-2xl flex items-center gap-6 z-50 w-[90%] max-w-lg">
      <div className="bg-orange-500 w-12 h-12 rounded-full flex items-center justify-center font-black text-xl text-white">
        {count}
      </div>
      
      <div className="flex-1 text-left">
        <p className="text-[10px] text-gray-400 uppercase font-black tracking-widest">Seu Pedido</p>
        <p className="text-xl font-black text-orange-500">Total: ${total.toFixed(2)}</p>
      </div>

      <button 
        type="button"
        onClick={() => {
          console.log("Botão clicado! Itens no carrinho:", cart); // Isso vai aparecer no F12 para testarmos
          navigate('/checkout', { state: { cart, total } });
        }}
        className="bg-orange-500 text-white px-6 py-3 rounded-2xl text-sm font-black hover:bg-orange-600 transition-all uppercase active:scale-95"
      >
        Finalizar
      </button>
    </div>
  );
};

export default CartTotal;