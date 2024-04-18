// Plugins
import {registerPlugins} from '@/plugins'
import { createApp } from 'vue';
import App from './App.vue';
import router from './routers'; // Import the router configuration

const app = createApp(App);
registerPlugins(app)
app.use(router); // Use the router with the Vue application
app.mount('#app'); // Mount the application to the DOM
