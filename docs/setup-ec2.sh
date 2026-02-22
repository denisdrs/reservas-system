#!/bin/bash
# =============================================================
#  docs/setup-ec2.sh – Configuração inicial da EC2
#  Execute via SSH logo após criar a instância:
#  ssh -i chave.pem ubuntu@<IP> 'bash -s' < docs/setup-ec2.sh
# =============================================================
set -e

echo "============================================="
echo " Setup EC2 – Sistema de Reservas"
echo "============================================="

echo "[1/5] Atualizando sistema..."
sudo apt update && sudo apt upgrade -y

echo "[2/5] Instalando Docker..."
curl -fsSL https://get.docker.com | sudo sh
sudo usermod -aG docker ubuntu

echo "[3/5] Instalando Docker Compose plugin..."
sudo apt install -y docker-compose-plugin

echo "[4/5] Instalando cliente PostgreSQL..."
sudo apt install -y postgresql-client

echo "[5/5] Criando estrutura de pastas..."
mkdir -p ~/projeto

echo ""
echo "============================================="
echo " Setup concluido!"
echo " PROXIMOS PASSOS:"
echo "  scp docker-compose.yml  ubuntu@<IP>:~/projeto/"
echo "  scp nginx.conf          ubuntu@<IP>:~/projeto/"
echo "  scp env.ec2             ubuntu@<IP>:~/projeto/.env"
echo "  (preencha o .env com valores reais)"
echo "  cd ~/projeto && docker compose up -d"
echo "============================================="
sudo reboot
