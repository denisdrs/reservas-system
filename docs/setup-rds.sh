#!/bin/bash
# =============================================================
#  docs/setup-rds.sh – Inicializa bancos no RDS
#  Execute na EC2 após o RDS estar disponível
#  Uso: bash docs/setup-rds.sh <ENDPOINT> <USER> <PASS>
# =============================================================
set -e

RDS_HOST=$1; RDS_USER=$2; RDS_PASS=$3

if [ -z "$RDS_HOST" ] || [ -z "$RDS_USER" ] || [ -z "$RDS_PASS" ]; then
  echo "Uso: bash docs/setup-rds.sh <endpoint> <usuario> <senha>"
  exit 1
fi

export PGPASSWORD=$RDS_PASS

echo "Criando databases..."
psql -h $RDS_HOST -U $RDS_USER -d postgres -c "CREATE DATABASE reservas_dev;"  2>/dev/null || echo "reservas_dev ja existe"
psql -h $RDS_HOST -U $RDS_USER -d postgres -c "CREATE DATABASE reservas_prod;" 2>/dev/null || echo "reservas_prod ja existe"

echo "Inicializando reservas_dev..."
psql -h $RDS_HOST -U $RDS_USER -d reservas_dev  -f docs/init.sql

echo "Inicializando reservas_prod..."
psql -h $RDS_HOST -U $RDS_USER -d reservas_prod -f docs/init.sql

echo "RDS configurado! Databases: reservas_dev e reservas_prod"
