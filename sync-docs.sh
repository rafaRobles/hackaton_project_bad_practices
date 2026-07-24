#!/bin/bash

# Asegúrate de poner aquí tu URL y rama correctas
REPO_URL="https://github.com/rafaRobles/hackaton_md_files.git"
TARGET_DIR="ai-docs"
BRANCH="master"

echo "Descargando contexto para la IA..."

if [ ! -d "$TARGET_DIR/.git" ]; then
  # Clonamos el repositorio sin descargar archivos
  git clone --no-checkout --depth 1 --filter=blob:none $REPO_URL $TARGET_DIR
  cd $TARGET_DIR
  
  # Forzamos a Git a desactivar Cone Mode y aceptar archivos .md
  git sparse-checkout init --no-cone
  
  # Descarga .md en la raíz y en cualquier subcarpeta
  git sparse-checkout set "*.md" "**/*.md"
  
  git checkout $BRANCH
else
  # Si ya existe, actualiza
  cd $TARGET_DIR
  git pull origin $BRANCH
fi

echo "¡Contexto listo en la carpeta ai-docs/!"