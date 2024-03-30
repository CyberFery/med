<script setup lang="ts">
</script>

<template>
  <v-tabs
    class="bg-indigo-lighten-1"
    center-active
    align-tabs="center"
    stacked
  >
    <v-tab value="home" to="/">
      <v-icon>mdi-home</v-icon>
      Home
    </v-tab>

    <v-tab value="login" to="/login">
      <v-icon>mdi-login</v-icon>
      Login
    </v-tab>

    <v-tab value="about" to="/about">
      <v-icon>mdi-information</v-icon>
      About
    </v-tab>
  </v-tabs>
</template>

<style scoped>

</style>
