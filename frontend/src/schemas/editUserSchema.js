import * as z from 'zod';

export const editUserSchema = z.object({
  name: z.string().min(1, 'O nome é obrigatório'),
  password: z.string().min(6, 'A palavra-passe deve ter pelo menos 6 caracteres').optional().or(z.literal('')),
  street: z.string().min(1, 'A rua é obrigatória'),
  number: z.string().min(1, 'O número é obrigatório'),
  city: z.string().min(1, 'A cidade é obrigatória'),
  state: z.string().min(1, 'O estado é obrigatório'),
});