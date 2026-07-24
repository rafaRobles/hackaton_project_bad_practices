#!/bin/bash

# Configuración
REPO_URL="https://github.com/rafaRobles/hackaton_md_files.git"
TARGET_DIR="ai-docs"
BRANCH="master"
INSTRUCTIONS_FILE=".github/copilot-instructions.md"

echo "Descargando contexto para la IA..."

# 1. Descarga los archivos (Lógica de macOS con --no-cone)
if [ ! -d "$TARGET_DIR/.git" ]; then
  git clone --no-checkout --depth 1 --filter=blob:none $REPO_URL $TARGET_DIR
  cd $TARGET_DIR
  git sparse-checkout init --no-cone
  git sparse-checkout set "*.md" "**/*.md"
  git checkout $BRANCH
  cd ..
else
  cd $TARGET_DIR
  git pull origin $BRANCH
  cd ..
fi

echo "Archivos descargados. Generando instrucciones para Copilot..."

# 2. Crea la carpeta oculta si no existe
mkdir -p .github

# 3. Escribe las reglas base en el archivo
cat << 'EOF' > $INSTRUCTIONS_FILE
Eres un experto desarrollador trabajando en el sistema interno llamado "Argos".
A continuación se incluye la documentación oficial y el contexto del sistema. 
USA ESTA INFORMACIÓN OBLIGATORIAMENTE para responder a mis preguntas, sin que yo tenga que pedirlo. 
Si te pregunto sobre arquitectura, seguridad, pruebas o radio de impacto, busca las respuestas en el siguiente texto antes de contestar:

================ DOCUMENTACIÓN DE ARGOS ================

EOF

# 4. Lee cada archivo .md descargado y pégalo dentro de las instrucciones
for file in "$TARGET_DIR"/*.md; do
  if [ -f "$file" ]; then
    # Extrae solo el nombre del archivo para usarlo como título
    FILENAME=$(basename "$file")
    
    echo "" >> $INSTRUCTIONS_FILE
    echo "--- INICIO DE ARCHIVO: $FILENAME ---" >> $INSTRUCTIONS_FILE
    echo "" >> $INSTRUCTIONS_FILE
    
    # Inyecta el contenido
    cat "$file" >> $INSTRUCTIONS_FILE
    
    echo "" >> $INSTRUCTIONS_FILE
    echo "--- FIN DE ARCHIVO: $FILENAME ---" >> $INSTRUCTIONS_FILE
    echo "" >> $INSTRUCTIONS_FILE
  fi
done

echo "¡Éxito! Contexto inyectado en $INSTRUCTIONS_FILE."