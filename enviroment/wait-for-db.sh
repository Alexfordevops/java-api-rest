#!/bin/bash
set -e

HOST="${DATABASE_HOST:-db}"
PORT="${DATABASE_PORT:-5432}"
DB="${DATABASE_NAME:-mydb}"
USER="${DATABASE_USER:-postgres}"
PASSWORD=$(cat "${DATABASE_PASSWORD_FILE:-/run/secrets/db_password}")

echo "Aguardando PostgreSQL em $HOST:$PORT..."

until PGPASSWORD="$PASSWORD" psql -h "$HOST" -p "$PORT" -U "$USER" -d "$DB" -c '\q' > /dev/null 2>&1; do
  echo "Ainda não disponível, esperando..."
  sleep 5
done

echo "PostgreSQL está pronto! Iniciando a aplicação Spring Boot..."
exec "$@"
