<script setup lang="ts">
import { ref } from 'vue';

const items = ref([
  {
    pageName: "Home",
    icon: "mdi-home",
    path: "/"
  },
  {
    pageName: "Login",
    icon: "mdi-login",
    path: "/login"
  },
  {
    pageName: "About",
    icon: "mdi-information",
    path: "/about"
  }
]);
</script>

<template>
  <v-toolbar class="bg-grey-darken-3" elevation="8">
    <v-toolbar-title>Doctor - Centralized Medical Records</v-toolbar-title>

    <v-spacer/>

    <v-btn>
      <v-icon>mdi-magnify</v-icon>
    </v-btn>

    <v-btn>
      <v-icon>mdi-dots-vertical</v-icon>
    </v-btn>

    <template v-slot:extension>
      <v-tabs
        center-active
        align-tabs="center"
      >
        <v-tab
          v-for="item in items"
          :key="item.path"
          :to="item.path"
        >
          <v-icon>{{ item.icon }}</v-icon>
          {{ item.pageName }}
        </v-tab>
      </v-tabs>
    </template>
  </v-toolbar>
</template>

<style scoped>
</style>
