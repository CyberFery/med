<template>
  <div class="form-container">
    <h1>Login</h1>
    <form @submit.prevent="login">
      <div class="input-group">
        <label for="username">Username:</label>
        <input id="username" v-model="credentials.username" type="text" required>
      </div>
      <div class="input-group">
        <label for="password">Password:</label>
        <input id="password" v-model="credentials.password" type="password" required>
      </div>
      <button type="submit">Login</button>
      <p class="error-message" v-if="errorMessage">{{ errorMessage }}</p>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/useAuthStore';

const credentials = ref({ username: '', password: '' });
const errorMessage = ref('');
const router = useRouter();
const { setToken } = useAuthStore();

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
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f9f9f9;
}

.input-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input[type="text"],
input[type="password"] {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #008080;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

.error-message {
  color: red;
  margin-top: 10px;
}
</style>
