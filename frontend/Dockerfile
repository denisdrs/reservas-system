# Estágio 1: Build
FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
# Garante que o .env.production é lido pelo Vite no build
ENV NODE_ENV=production
RUN npm run build

# Estágio 2: Runtime
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx-spa.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
