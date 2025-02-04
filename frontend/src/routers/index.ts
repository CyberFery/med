
import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';
import HomePage from "@/views/HomePage.vue";
import LoginPage from "@/views/LoginPage.vue";
import RetrieveMedicalRecord from "@/components/RetrieveMedicalRecord.vue";
import UpdateMedicalVisit from "@/components/UpdateMedicalVisit.vue";
import DeleteMedicalVisit from "@/components/DeleteMedicalVisit.vue";
import ModifyMedicalVisit from "@/components/ModifyMedicalVisit.vue";
import DeleteMedicalHistory from "@/components/DeleteMedicalHistory.vue";
import UpdateMedicalHistory from "@/components/UpdateMedicalHistory.vue";
import ModifyMedicalHistory from "@/components/ModifyMedicalHistory.vue";
import { authStore } from '@/stores/AuthStore';




declare module 'vue-router' {
  interface RouteMeta {
    requiresAuth?: boolean;
    guest?: boolean;
  }
}

const routes: Array<RouteRecordRaw> = [
  { path: '/', name: 'home', component: HomePage, meta: { guest: true } },
  { path: '/login', name: 'login', component: LoginPage, meta: { guest: true } },
  { path: '/retrieve-medical-record', name: 'RetrieveMedicalRecord', component: RetrieveMedicalRecord, meta: { requiresAuth: true } },
  { path: '/update-medical-visit', name: 'UpdateMedicalVisit', component: UpdateMedicalVisit, meta: { requiresAuth: true } },
  { path: '/delete-medical-visit', name: 'DeleteMedicalVisit', component: DeleteMedicalVisit, meta: { requiresAuth: true } },
  { path: '/modify-medical-visit', name: 'ModifyMedicalVisit', component: ModifyMedicalVisit, meta: { requiresAuth: true } },
  { path: '/delete-medical-history', name: 'DeleteMedicalHistory', component: DeleteMedicalHistory, meta: { requiresAuth: true } },
  { path: '/update-medical-history', name: 'UpdateMedicalHistory', component: UpdateMedicalHistory, meta: { requiresAuth: true } },
  { path: '/modify-medical-history', name: 'ModifyMedicalHistory', component: ModifyMedicalHistory, meta: { requiresAuth: true } }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach((to, from, next) => {
  const { isAuthenticated } = authStore();

  if (to.meta.requiresAuth && !isAuthenticated.value) {
    next('/login');
  } else if (to.name === "/login" && isAuthenticated.value) {
    next('/');
  } else {
    next();
  }
});

export default router;
