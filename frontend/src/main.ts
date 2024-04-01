// Plugins
import {registerPlugins} from '@/plugins'

// Components
import App from '@/App.vue'
import HomePage from '@/views/HomePage.vue'
import LoginPage from '@/views/LoginPage.vue'
import NotFoundPage from '@/views/NotFoundPage.vue'
import AboutPage from '@/views/AboutPage.vue'

// Composables
import {createApp} from 'vue'

// vue-router
import {createRouter, createWebHistory} from 'vue-router'

// Create the router instance and define your routes
const router = createRouter({
  history: createWebHistory(),
  routes: [
    {path: '/', component: HomePage},
    {path: '/login', component: LoginPage},
    {path: '/not-found', component: NotFoundPage},
    {path: '/about', component: AboutPage}
    // TODO Add more routes as needed
    // example with authentication required :
    // { path: '/patient/1', component: Patient, meta: { requiresAuth: true } }
  ]
})

// Function to check if the user is authenticated
const checkAuth = () => {
  const token = localStorage.getItem('token');
  return !!token; // Check if token exists
};

// Check authentication on every route change
router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth && !checkAuth()) {
    next('/login'); // Redirect to log in if route requires authentication and user is not authenticated
  } else {
    next();
  }
});

// Create the app instance and register plugins
const app = createApp(App)
registerPlugins(app)

// Mount the app with the router
app.use(router).mount('#app')
