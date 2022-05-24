# KNX Project - ResIoT
#### Julien ORNAT, Kévin DUVAL
##### ESIR2
---
Dans le cadre du module de Réseau IoT, nous avions pour objectif d'implémenter un système permettant de contrôler une maquette KNX depuis une interface web. Cette interface devait permettre d'allumer des leds et de lancer différents chenillards à différentes vitesses.

#### Architecture du projet
Ce projet est composé de trois parties :
- L'interface web client, développée en HTML, CSS, JS
- La maquette KNX, contrôlée en Java grâce à la librairie Calimero
- Le serveur, implémenté en Java, chargé de faire le lien entre l'interface web et la maquette KNX

### Différents fichiers de ce projet
Les différents fichiers de ce projet se trouvent dans la branche master de ce GitHub
##### Fichiers serveur
- ```Chenillard.java```: interface implémentée par les différents motifs de chenillards développés 
- ```Chenillard0.java```,```Chenillard1.java```,```Chenillard2.java```: différents motifs de chenillards
- ```GroupMonitor.java```: permet d'établir la connexion dans une méthode run() et de détecter tous les changements d'états dans une methode groupeWrite(), c'est le programme principal pour manipuler les éléments du KNX
- ```Instructions.java```: objet métier destiné à faire le lien entre les requêtes envoyées par le client web et les commandes KNX
- ```CreateTunnelingLink.java```: permet de créer un lien etre KNXN et et le protocole IP
- ```DiscoverKNX.java```:  permet de découvrir les serveurs actifs KNXnet/IP dans un réseau IP
- ```ProcessCommunication.java```: permet de lire et d'écrire des booleens dans le réseaux KNX dans le but de changer les états des leds par exemple
- ```RestKNX.java```: servlet qui permet la gestion des requêtes API Rest reçues par le serveur

##### Fichiers interface web
- ```index.jsp```: fichier contenant le HTML implémentant le site web
- ```script.js```: fichier implémentant les différentes fonctions JS utiles telles que l'envoi de requêtes POST au serveur
- ```style.css```: fichier contenant le CSS du site web

### Exécuter le projet
Pour exécuter le serveur, il faut lancer le fichier ```RestKNX.java``` sur un serveur Tomcat créé précédemment directement via Eclipse. Après cela, on peut accéder à l'interface web à l'adresse ```localhost:8081/knx/knx```. Le lien entre l'interface web et la maquette KNX n'étant pas fonctionnel, c'est le fichier ```GroupMonitor.java``` qu'il faut lancer afin de pouvoir interagir avec la maquette KNX. Il sera ensuite possible d'utiliser les différents boutons de la maquette pour effectuer diverses actions comme lancer un chenillard, augmenter ou diminuer sa vitesse, le stopper ou le remettre en marche.
