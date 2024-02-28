# Utilisez une image OpenJDK 11 comme image de base
FROM openjdk:11

# Définit les variables d'argument pour l'URL Nexus et le chemin de l'artefact
ARG NEXUS_URL=http://192.168.33.10:8081/repository/maven-snapshots/
ARG ARTIFACT_PATH=tn/esprit/spring/kaddem/4.0/kaddem-4.0.jar
# Crée un répertoire pour stocker le JAR téléchargé
RUN mkdir /app

# Télécharge le JAR depuis Nexus et le copie dans le conteneur
RUN wget -O /app/kaddem-4.0.jar $NEXUS_URL/$ARTIFACT_PATH

# Expose le port 8082 (si votre application écoute sur ce port)
EXPOSE 8082

# Définit la commande d'entrée pour exécuter l'application Java
ENTRYPOINT ["java", "-jar", "/app/kaddem-4.0.jar"]