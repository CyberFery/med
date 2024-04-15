<template>
  <v-toolbar color="deep-orange-lighten-4">
    <v-toolbar-title>Doctor - Centralized Medical Records</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-btn icon v-for="item in navigationItems" :key="item.pageName" @click="handleNavigation(item)">
      <v-icon>{{ item.icon }}</v-icon>
    </v-btn>
  </v-toolbar>
</template>

<script setup>
import {computed} from 'vue';
import {useRouter} from 'vue-router';
import {authStore} from '@/stores/AuthStore';

const {isAuthenticated, clearToken} = authStore();
const router = useRouter();

const navigationItems = computed(() => [
  {pageName: "Home", icon: "mdi-home", path: "/"},
  {pageName: "About", icon: "mdi-information", path: "/about"},
  ...isAuthenticated.value
    ? [{pageName: "Logout", icon: "mdi-logout", action: clearToken}]
    : [
      {pageName: "Login", icon: "mdi-login", path: "/login"},
    ],
]);

function handleNavigation(item) {
  if (item.action) {
    item.action();
    router.push('/');
  } else {
    router.push(item.path);
  }
}
</script>
