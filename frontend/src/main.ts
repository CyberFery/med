// Plugins
import {registerPlugins} from '@/plugins'
import { createApp } from 'vue';
import App from './App.vue';
import router from './routers';

const app = createApp(App);
registerPlugins(app)
app.use(router);
app.mount('#app');
