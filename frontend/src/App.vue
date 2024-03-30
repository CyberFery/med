<script setup lang="ts">
import AppBar from "@/components/AppBar.vue";
import Footer from "@/components/Footer.vue";
import Tabs from "@/components/Tabs.vue";

</script>

<template>
  <v-app>
    <AppBar/>
    <v-main>
      <Tabs/>
      <router-view/>
    </v-main>
    <Footer/>
  </v-app>
</template>


