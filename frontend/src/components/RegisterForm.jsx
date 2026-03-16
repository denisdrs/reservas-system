import { useState } from 'react';

const RegisterForm = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: '',
    address: '', // Novo campo adicionado
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/api/users/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        alert('Conta criada com sucesso!');
      } else {
        alert('Erro ao cadastrar. Tente novamente.');
      }
    } catch (error) {
      console.error('Erro de conexão:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="flex flex-col gap-4">
      <input
        name="email"
        type="email"
        placeholder="Email"
        className="border-b border-gray-300 py-2 outline-none focus:border-orange-500"
        onChange={handleChange}
        required
      />
      
      {/* Novo campo de Endereço */}
      <input
        name="address"
        type="text"
        placeholder="Endereço Completo (Rua, Nº, Bairro)"
        className="border-b border-gray-300 py-2 outline-none focus:border-orange-500"
        onChange={handleChange}
        required
      />

      <input
        name="password"
        type="password"
        placeholder="Senha"
        className="border-b border-gray-300 py-2 outline-none focus:border-orange-500"
        onChange={handleChange}
        required
      />
      
      <button type="submit" className="mt-6 bg-black py-3 text-white font-bold hover:bg-gray-800 transition-all">
        Registrar
      </button>
    </form>
  );
};

export default RegisterForm;