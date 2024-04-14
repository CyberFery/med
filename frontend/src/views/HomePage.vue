<script setup lang="ts">
import logo from '@/assets/logo.png'; // Make sure the path is correct
</script>

<template>
  <v-container>
    <h1 class="text-center text-h2">Home Page</h1>
    <img :src="logo" alt="Logo Image" /> <!-- Bind the imported image to the src attribute -->
  </v-container>
</template>

<style scoped>
img {
  display: block;  /* Optional: Ensures the image is a block-level element */
  margin: 0 auto;  /* Centers the image horizontally */
  max-width: 100%; /* Optional: Ensures the image scales down if it's too wide for the container */
}
</style>
