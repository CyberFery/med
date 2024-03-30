/**
 * main.ts
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */

// Plugins
import {registerPlugins} from '@/plugins'

// Components
import App from '@/App.vue'
import HomePage from '@/views/HomePage.vue'
import LoginPage from '@/views/LoginPage.vue'
import NotFoundPage from '@/views/NotFoundPage.vue'
import AboutPage from '@/views/AboutPage.vue'
// TODO add more components as needed

// Composables
import {createApp} from 'vue'

// vue-router
import { createRouter, createWebHistory } from 'vue-router'

// Create the router instance and define your routes
const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', component: HomePage },
    { path: '/login', component: LoginPage },
    { path: '/404', component: NotFoundPage },
    { path: '/about', component: AboutPage }
    // TODO Add more routes as needed
  ]
})

// Create the app instance and register plugins
const app = createApp(App)
registerPlugins(app)

// Mount the app with the router
app.mount('#app')

// Mount the app with the router
app.use(router).mount('#app')
