const OrderCard = ({ product, onAdd }) => {
  return (
    <div className="bg-white rounded-3xl p-4 shadow-sm border border-gray-100 flex flex-col gap-3 transition-transform hover:scale-[1.02] text-left">
      <img src={product.image} alt={product.name} className="w-full h-44 object-cover rounded-2xl" />
      <div className="flex justify-between items-center px-1">
        <span className="text-orange-600 font-extrabold text-xl">R$ {product.price.toFixed(2).replace('.', ',')}</span>
        <div className="bg-green-500 text-white px-2 py-0.5 rounded-lg text-xs font-bold flex items-center gap-1">★ {product.rating || '5.0'}</div>
      </div>
      <div className="px-1">
        <h3 className="font-bold text-gray-800 text-lg leading-tight">{product.name}</h3>
        <p className="text-gray-400 text-xs mt-2 leading-relaxed line-clamp-2">{product.description}</p>
      </div>
      <div className="flex gap-2 mt-2">
        <button className="flex-1 text-[10px] font-black py-3 border-2 border-orange-100 text-orange-500 rounded-xl uppercase">Info</button>
        <button onClick={onAdd} className="flex-1 text-[10px] font-black py-3 bg-orange-500 text-white rounded-xl shadow-lg shadow-orange-200 uppercase">Adicionar</button>
      </div>
    </div>
  );
};

export default OrderCard;