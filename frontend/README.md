# Vuetify (Par défaut)

Ceci est l'outil de base officiel pour Vuetify, conçu pour vous donner un coup de pouce dans la construction de votre
nouvelle application Vuetify. Il met en place un modèle de base avec toutes les configurations nécessaires et une
structure de répertoires standard, vous permettant de commencer le développement sans les tracas de la configuration du
projet à partir de zéro.

## ❗️ Liens Importants

- 📄 [Documentation](https://vuetifyjs.com/)
- 🚨 [Problèmes](https://issues.vuetifyjs.com/)
- 🏬 [Boutique](https://store.vuetifyjs.com/)
- 🎮 [Espace de jeu](https://play.vuetifyjs.com/)
- 💬 [Discord](https://community.vuetifyjs.com)

## 💿 Installation

Configurez votre projet en utilisant votre gestionnaire de paquets préféré. Utilisez la commande correspondante pour
installer les dépendances :

| Gestionnaire de Paquets                                   | Commande       |
|-----------------------------------------------------------|----------------|
| [yarn](https://yarnpkg.com/getting-started)               | `yarn install` |
| [npm](https://docs.npmjs.com/cli/v7/commands/npm-install) | `npm install`  |
| [pnpm](https://pnpm.io/installation)                      | `pnpm install` |
| [bun](https://bun.sh/#getting-started)                    | `bun install`  |

Une fois l'installation terminée, votre environnement est prêt pour le développement Vuetify.

## ✨ Fonctionnalités

- 🖼️ **Stack Front-End Optimisée** : Utilisez les dernières versions de Vue 3 et Vuetify 3 pour une expérience de
  développement d'interface utilisateur moderne et
  réactive. [Vue 3](https://v3.vuejs.org/) | [Vuetify 3](https://vuetifyjs.com/en/)
- 🗃️ **Gestion de l'État** : Intégré avec [Pinia](https://pinia.vuejs.org/), la solution de gestion de l'état intuitive
  et modulaire pour Vue.
- 🚦 **Routing et Mises en Page** : Utilise Vue Router pour la navigation SPA et vite-plugin-vue-layouts pour organiser
  les mises en page des fichiers
  Vue. [Vue Router](https://router.vuejs.org/) | [vite-plugin-vue-layouts](https://github.com/JohnCampionJr/vite-plugin-vue-layouts)
- 💻 **Expérience de Développement Améliorée** : Bénéficiez de la vérification de type statique de TypeScript et de la
  suite de plugins ESLint pour Vue, assurant la qualité et la cohérence du
  code. [TypeScript](https://www.typescriptlang.org/) | [Plugin ESLint Vue](https://eslint.vuejs.org/)
- ⚡ **Outils de Nouvelle Génération** : Alimenté par Vite, bénéficiez de démarrages rapides et de remplacement
  instantané des modules chauds (HMR). [Vite](https://vitejs.dev/)
- 🧩 **Importation Automatique des Composants** : Simplifiez votre flux de travail avec unplugin-vue-components, qui
  importe automatiquement les composants au fur et à mesure de leur
  utilisation. [unplugin-vue-components](https://github.com/antfu/unplugin-vue-components)
- 🛠️ **Vue à Typage Fort** : Utilisez vue-tsc pour vérifier le type de vos composants Vue et profitez d'une expérience
  de développement robuste. [vue-tsc](https://github.com/johnsoncodehk/volar/tree/master/packages/vue-tsc)

Ces fonctionnalités sont conçues pour offrir une expérience de développement fluide de la configuration au déploiement,
garantissant que votre application Vuetify est à la fois puissante et maintenable.

## 💡 Utilisation

Cette section couvre comment démarrer le serveur de développement et construire votre projet pour la production.

### Démarrage du Serveur de Développement

Pour démarrer le serveur de développement avec le rechargement à chaud, exécutez la commande suivante. Le serveur sera
accessible à [http://localhost:3000](http://localhost:3000) :

```bash
yarn dev
```

(Répétez pour npm, pnpm et bun avec les commandes respectives.)

> NODE_OPTIONS='--no-warnings' est ajouté pour supprimer les avertissements d'importation JSON qui se produisent dans le
> cadre de l'association d'

importation de Vuetify. Si vous utilisez Node [v21.3.0](https://nodejs.org/en/blog/release/v21.3.0) ou une version
supérieure, vous pouvez changer cela en NODE_OPTIONS='--disable-warning=5401'. Si les avertissements ne vous dérangent
pas, vous pouvez supprimer ceci de votre script dev package.json.

### Construction pour la Production

Pour construire votre projet pour la production, utilisez :

```bash
yarn build
```

(Répétez pour npm, pnpm et bun avec les commandes respectives.)

Une fois le processus de construction terminé, votre application sera prête pour le déploiement dans un environnement de
production.

## 💪 Soutenir le Développement Vuetify

Ce projet est construit avec [Vuetify](https://vuetifyjs.com/en/), une bibliothèque d'interface utilisateur avec une
collection complète de composants Vue. Vuetify est un projet Open Source sous licence MIT rendu possible grâce aux
généreuses contributions de nos [sponsors et backers](https://vuetifyjs.com/introduction/sponsors-and-backers/). Si vous
êtes intéressé par le soutien de ce projet, veuillez envisager :

- [Demander un Support Entreprise](https://support.vuetifyjs.com/)
- [Sponsoriser John sur Github](https://github.com/users/johnleider/sponsorship)
- [Sponsoriser Kael sur Github](https://github.com/users/kaelwd/sponsorship)
- [Soutenir l'équipe sur Open Collective](https://opencollective.com/vuetify)
- [Devenir sponsor sur Patreon](https://www.patreon.com/vuetify)
- [Devenir abonné sur Tidelift](https://tidelift.com/subscription/npm/vuetify)
- [Faire un don ponctuel avec Paypal](https://paypal.me/vuetify)

## 📑 Licence

[MIT](http://opensource.org/licenses/MIT)

Droits d'auteur (c) 2016-présent Vuetify, LLC