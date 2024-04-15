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

    <v-footer app fixed>
      <Footer/>
    </v-footer>

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
  justify-content: center; /* Center content vertically */
  height: calc(100vh - (48px + 48px)); /* Adjust based on actual height of AppBar and Footer */
}

.sidebar {
  height: 100%; /* Ensures the sidebar takes only the available height */
  overflow-y: auto; /* Adds scroll to sidebar if content is too long */
  background-color: #424242; /* Use the theme's primary color */
}
</style>
