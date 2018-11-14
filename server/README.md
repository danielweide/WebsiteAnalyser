##  Auteur      : Louis GREGOIRE-Pollin
##  Date        : 20 Février 2017
##  Description : REST API de justification d'un texte monoespacé. Test technique pour la candidature de stage chez Abricot.

** Description des fichiers implémentés **

  - server.js : fichier de configuration du server, définit le port.

  - app.js : fichier contenant tous les middlewares de l'application et définissant
    les endpoints possibles. Contient aussi un contrôles des erreurs de CORS et un
    gestionnaire d'erreur.

  - api/
    - justify.js : fichier correspondant au endpoint `/api/justify`.
      Contient le middleware de gestion de requêtes *POST* ainsi que les fonctions
      de justification du texte envoyé en requête. Implemente aussi une authentification
      de token http et un cache redis pour la limitation d'utilisation.

    - token.js : fichier simple correspondant au endpoint `/api/token`.
      Utilise simplement le module *'jsonwebtoken'* pour fournir un token en echange
      d'un email fourni dans une requête http avec un body json de la forme `{"email": "foo@bar.com"}`.
      Ce fichier fait aussi appel au [module de validation](./test/validation/email.js) qui vérifie le bon
      format de requête.

  - test/
    - redis/redis.js : Simple fichier de configuration du serveur redis.

    - validation/email.js : Fichier de validation utilisant le module `Joi`pour
      valider le format du corps des requêtes envoyé au endpoint `/api/token`.

** Tests **

  Les tests ont été effectués avec le logiciel **Postman**.

** Modules Node.js utilisés **

  - *express*
  - *jsonwebtoken*
  - *express-validation*
  - *morgan*
  - *body-parser*
  - *nodemon*
