<template>
  <v-list dense class="sidebar-color">
    <v-list-item v-for="item in items" :key="item.title" @click="navigateTo(item.path)" class="sidebar-item">
      <v-list-item-icon>
        <v-icon>{{ item.icon }}</v-icon>
      </v-list-item-icon>
      <v-list-item-content>
        <v-list-item-title>{{ item.title }}</v-list-item-title>
      </v-list-item-content>
    </v-list-item>
  </v-list>
</template>

<script setup>
import { useRouter } from 'vue-router';

const items = [
  { title: 'Retrieve Medical Record', icon: 'mdi-file-find', path: '/retrieve-medical-record' },
  { title: 'Add Medical Visit', icon: 'mdi-plus', path: '/update-medical-visit' },
  { title: 'Delete Medical Visit', icon: 'mdi-delete', path: '/delete-medical-visit' },
  { title: 'Modify Medical Visit', icon: 'mdi-pencil', path: '/modify-medical-visit' },
  { title: 'Add Medical History', icon: 'mdi-plus', path: '/update-medical-history' },
  { title: 'Delete Medical History', icon: 'mdi-delete', path: '/delete-medical-history' },
  { title: 'Modify Medical History', icon: 'mdi-pencil', path: '/modify-medical-history' }
];
const router = useRouter();

function navigateTo(path) {
  router.push(path);
}
</script>

<style scoped>
.sidebar-color {
  background-color: #FFFFFF; /* Coral */
}

.sidebar-item {
  background-color: #000000;
  color: white;
  margin-bottom: 10px;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  transition: box-shadow 0.3s ease;
  padding: 8px;
}

.sidebar-item:hover {
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
}
</style>
