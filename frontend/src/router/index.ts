
import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';
import HomePage from '../views/HomePage.vue';
import AboutPage from '../views/AboutPage.vue';
import LoginPage from "@/views/LoginPage.vue";
import RegisterPage from "@/views/RegisterPage.vue";
import RetrieveMedicalRecord from "@/components/RetrieveMedicalRecord.vue";
import UpdateMedicalVisit from "@/components/UpdateMedicalVisit.vue";
import DeleteMedicalVisit from "@/components/DeleteMedicalVisit.vue";
import DeleteMedicalHistory from "@/components/DeleteMedicalHistory.vue";
import UpdateMedicalHistory from "@/components/UpdateMedicalHistory.vue"; // Ensure this import is correct
import { useAuthStore } from '@/stores/useAuthStore';
import AboutPage from "@/views/AboutPage.vue";

declare module 'vue-router' {
  interface RouteMeta {
    requiresAuth?: boolean;
    guest?: boolean;
  }
}

const routes: Array<RouteRecordRaw> = [
  { path: '/', name: 'home', component: HomePage, meta: { guest: true } },
  { path: '/about', name:'about', component: AboutPage, meta:  { guest: true } },
  { path: '/login', name: 'login', component: LoginPage, meta: { guest: true } },
  { path: '/register', name: 'register', component: RegisterPage, meta: { guest: true } },
  { path: '/retrieve-medical-record', name: 'RetrieveMedicalRecord', component: RetrieveMedicalRecord, meta: { requiresAuth: true } },
  { path: '/update-medical-visit', name: 'UpdateMedicalVisit', component: UpdateMedicalVisit, meta: { requiresAuth: true } },
  { path: '/delete-medical-visit', name: 'DeleteMedicalVisit', component: DeleteMedicalVisit, meta: { requiresAuth: true } },
  { path: '/delete-medical-history', name: 'DeleteMedicalHistory', component: DeleteMedicalHistory, meta: { requiresAuth: true } },
  { path: '/update-medical-history', name: 'UpdateMedicalHistory', component: UpdateMedicalHistory, meta: { requiresAuth: true } }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach((to, from, next) => {
  const { isAuthenticated } = useAuthStore();

  if (to.meta.requiresAuth && !isAuthenticated.value) {
    next('/login');
  } else if (to.name === "/login" && isAuthenticated.value) {
    next('/');
  } else {
    next();
  }
});

export default router;
