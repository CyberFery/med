<template>
  <v-app>
    <v-app-bar app fixed>
      <AppBar />
    </v-app-bar>

    <v-main class="main-content">
      <v-container fluid class="fill-height">
        <v-row no-gutters align="center" justify="center">
          <v-col cols="12" md="2" class="sidebar primary" v-if="isAuthenticated">
            <SideBar />
          </v-col>
          <v-col :cols="isAuthenticated ? '10' : '12'" :md="isAuthenticated ? '10' : '12'">
            <router-view />
          </v-col>
        </v-row>
      </v-container>
    </v-main>

  </v-app>

</template>

<script setup>
import AppBar from "@/components/AppBar.vue";
import Footer from "@/components/Footer.vue";
import SideBar from "@/components/SideBar.vue";
import {authStore} from "@/stores/AuthStore";

const {isAuthenticated} = authStore();
</script>

<style scoped>
.main-content {
  flex: 1 1 auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: calc(100vh - (48px + 48px));
}

.sidebar {
  height: 100%;
  overflow-y: auto;
  background-color: #424242;
}
</style>
