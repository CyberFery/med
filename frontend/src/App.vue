<script setup lang="ts">
import AppBar from "@/components/AppBar.vue";
import Footer from "@/components/Footer.vue";

</script>

<template>
  <v-app>
    <AppBar/>
    <v-main>
      <router-view/>
    </v-main>
    <Footer/>
  </v-app>
</template>


