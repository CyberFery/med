<template>
  <v-container class="form-container">
    <v-card class="pa-4" outlined>
      <v-card-title class="display-1">Login</v-card-title>
      <v-card-text>
        <v-form @submit.prevent="login">
          <v-text-field
            v-model="credentials.username"
            label="Username"
            id="username"
            type="text"
            required
            outlined
            color="black"
          ></v-text-field>
          <v-text-field
            v-model="credentials.password"
            label="Password"
            id="password"
            type="password"
            required
            outlined
            color="black"
          ></v-text-field>
          <v-btn type="submit" color="black" block depressed>Login</v-btn>
          <v-alert
            v-if="errorMessage"
            type="error"
            dense
            text
            class="mt-4"
          >{{ errorMessage }}</v-alert>
        </v-form>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { authStore } from '@/stores/AuthStore';

const credentials = ref({ username: '', password: '' });
const errorMessage = ref('');
const router = useRouter();
const { setToken } = authStore();

async function login() {
  errorMessage.value = '';
  try {
    const response = await axios.post('http://localhost:8080/auth/token', credentials.value);
    setToken(response.data);  // Assume the response data is the token
    router.push('/').catch(err => console.error('Router push failed:', err));
  } catch (err) {
    if (axios.isAxiosError(err) && err.response) {
      errorMessage.value = err.response.data || 'An error occurred during login.';
    } else {
      errorMessage.value = 'An unexpected error occurred.';
    }
  }
}
</script>

<style scoped>
.form-container {
  max-width: 600px; /* Optional for adjusting form size */
  margin-top: 20vh; /* Centrally aligning the form vertically */
}
</style>
