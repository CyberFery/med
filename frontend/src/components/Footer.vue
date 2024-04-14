<script setup lang="ts">
const icons = [
  'mdi-email',
  'mdi-linkedin',
];
</script>

<template>
  <v-footer
    class="bg-teal-lighten-5 text-center d-flex flex-column h-auto"
  >
    <div>
      <v-btn
        v-for="icon in icons"
        :key="icon"
        :icon="icon"
        class="mx-4"
        variant="text"
      ></v-btn>
    </div>

    <div class="pt-0">
      This application helps doctors efficiently manage medical records of patients, providing easy access to important health information. If you have any questions or feedback, please don't hesitate to contact us.
    </div>

    <v-spacer/>

    <div>
      {{ new Date().getFullYear() }} — <strong>Doctor - Centralized Medical Records</strong>
    </div>

    <v-spacer/>

    <div>
      <v-icon>mdi-copyright</v-icon> Team #17 - INF5153
    </div>
  </v-footer>
</template>

<style scoped>
</style>
