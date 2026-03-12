import * as z from 'zod';

export const loginSchema = z.object({
  email: z.string()
    .min(1, 'O email é obrigatório')
    .email('Introduza um email válido'),
  password: z.string()
    .min(6, 'A palavra-passe deve ter pelo menos 6 caracteres'),
});