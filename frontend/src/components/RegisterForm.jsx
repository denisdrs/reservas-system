import { useState } from 'react';

const RegisterForm = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
    address: {
      street: '',
      number: '',
      city: '',
      state: '',
    },
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name.includes('.')) {
      const [parent, child] = name.split('.');
      setFormData((prev) => ({
        ...prev,
        [parent]: {
          ...prev[parent],
          [child]: value,
        },
      }));
    } else {
      setFormData((prev) => ({ ...prev, [name]: value }));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const payload = {
      ...formData,
      address: {
        ...formData.address,
        number: parseInt(formData.address.number, 10),
      },
    };

    try {
      const response = await fetch('http://localhost:8080/api/users', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
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
        name="name"
        type="text"
        placeholder="Nome Completo"
        className="border-b border-gray-300 py-2 outline-none focus:border-orange-500"
        onChange={handleChange}
        required
      />
      <input
        name="email"
        type="email"
        placeholder="Email"
        className="border-b border-gray-300 py-2 outline-none focus:border-orange-500"
        onChange={handleChange}
        required
      />
      <input
        name="address.street"
        type="text"
        placeholder="Rua"
        className="border-b border-gray-300 py-2 outline-none focus:border-orange-500"
        onChange={handleChange}
        required
      />
      <input
        name="address.number"
        type="number"
        placeholder="Número"
        className="border-b border-gray-300 py-2 outline-none focus:border-orange-500"
        onChange={handleChange}
        required
      />
      <input
        name="address.city"
        type="text"
        placeholder="Cidade"
        className="border-b border-gray-300 py-2 outline-none focus:border-orange-500"
        onChange={handleChange}
        required
      />
      <input
        name="address.state"
        type="text"
        placeholder="Estado"
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