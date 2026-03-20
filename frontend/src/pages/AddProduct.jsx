import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const AddProduct = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    name: '',
    price: '',
    category: 'Entradas',
    image: '',
    description: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem('token');

    const produtoParaEnviar = {
      name: formData.name,
      value: formData.price.replace(',', '.'), 
      quantity: 100, 
      category: formData.category,
      description: formData.description,
      image: formData.image
    };

    try {
      const response = await fetch('http://3.213.13.51/dev/api/products', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}` 
        },
        body: JSON.stringify(produtoParaEnviar),
      });

      if (response.ok) {
        alert("Sucesso! Produto cadastrado via Site.");
        navigate('/orders'); 
      } else {
        const erro = await response.json();
        alert(`Erro: ${erro.message || 'Verifique o Token de Admin'}`);
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
              <input name="price" value={formData.price} onChange={handleChange} required className="w-full p-3 bg-gray-50 border rounded-lg outline-none" placeholder="29,90" />
            </div>
            <div>
              <label className="block text-xs font-bold text-gray-500 uppercase mb-1">Categoria</label>
              <select name="category" value={formData.category} onChange={handleChange} className="w-full p-3 bg-gray-50 border rounded-lg outline-none">
                <option value="Entradas">Entradas</option>
                <option value="Hambúrgueres">Hambúrgueres</option>
                <option value="Bebidas">Bebidas</option>
                <option value="Sobremesas">Sobremesas</option>
              </select>
            </div>
          </div>

          <div>
            <label className="block text-xs font-bold text-gray-500 uppercase mb-1">URL da Imagem</label>
            <input name="image" value={formData.image} onChange={handleChange} className="w-full p-3 bg-gray-50 border rounded-lg outline-none" placeholder="https://link-da-foto.jpg" />
          </div>

          <div>
            <label className="block text-xs font-bold text-gray-500 uppercase mb-1">Descrição</label>
            <textarea name="description" value={formData.description} onChange={handleChange} className="w-full p-3 bg-gray-50 border rounded-lg outline-none" rows="3" placeholder="Pão brioche, carne 180g..."></textarea>
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