import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const AddProduct = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    name: '',
    value: '',
    quantity: '',
    url: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem('token');

    const productToSend = {
      ...formData,
      value: formData.value.replace(',', '.'),
    };

    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/api/products`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}` 
        },
        body: JSON.stringify(productToSend),
      });

      if (response.ok) {
        alert("Sucesso! Produto cadastrado via Site.");
        navigate('/orders'); 
      } else {
        const error = await response.json();
        alert(`Erro: ${error.message || 'Verifique o Token de Admin'}`);
      }
    } catch (error) {
      console.error("Erro na conexão:", error);
      alert("Erro de conexão com a API.");
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 p-8 font-sans text-gray-800">
      <div className="max-w-2xl mx-auto bg-white p-8 rounded-2xl shadow-xl">
        <h1 className="text-3xl font-black text-gray-800 mb-6">Novo Item no Cardápio</h1>
        
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-xs font-bold text-gray-500 uppercase mb-1">Nome do Prato</label>
            <input name="name" value={formData.name} onChange={handleChange} required className="w-full p-3 bg-gray-50 border rounded-lg focus:ring-2 focus:ring-orange-500 outline-none" placeholder="Ex: X-Salada Especial" />
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-gray-500 uppercase mb-1">Preço (R$)</label>
              <input name="value" value={formData.value} onChange={handleChange} required className="w-full p-3 bg-gray-50 border rounded-lg outline-none" placeholder="29,90" />
            </div>
            <div>
              <label className="block text-xs font-bold text-gray-500 uppercase mb-1">Quantidade</label>
              <input type="number" name="quantity" value={formData.quantity} onChange={handleChange} required className="w-full p-3 bg-gray-50 border rounded-lg outline-none" placeholder="100" />
            </div>
          </div>

          <div>
            <label className="block text-xs font-bold text-gray-500 uppercase mb-1">URL da Imagem</label>
            <input name="url" value={formData.url} onChange={handleChange} className="w-full p-3 bg-gray-50 border rounded-lg outline-none" placeholder="https://link-da-foto.jpg" />
          </div>

          <button type="submit" className="w-full bg-orange-600 text-white font-bold p-4 rounded-xl hover:bg-orange-700 transition shadow-lg mt-4">
            CADASTRAR PRODUTO
          </button>
        </form>
      </div>
    </div>
  );
};

export default AddProduct;