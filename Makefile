# Usado para buildar a aplicação
build:
	cd backend/reservas-system && ./mvnw clean install -DskipTests

# Rodar todos os testes
test:
	cd backend/reservas-system && ./mvnw test

# Sobe a aplicação local usando o docker compose
run:
	docker compose -f infra/docker-compose.local.yml up --build -d

# Para e remove os containers criados usando o run
stop:
	docker compose -f infra/docker-compose.local.yml down