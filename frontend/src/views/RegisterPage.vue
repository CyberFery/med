<script setup lang="ts">
import { ref } from 'vue';
import { useForm, useField } from 'vee-validate';
import * as yup from 'yup';
import axios from 'axios';

// Validation schema
const schema = yup.object({
  username: yup.string().required('Username is required').min(2, 'Username must be at least 2 characters long').max(20, 'Username must not exceed 20 characters'),
  email: yup.string().required('Email is required').email('Email must be a valid email'),
  password: yup.string().required('Password is required').matches(
    /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&#^])[A-Za-z\d@$!%*?&#^]{8,}$/,
    'Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character (!@#$%^&*), and be at least 8 characters long'
  )
});

const { handleSubmit, resetForm, isSubmitting, setErrors } = useForm({
  validationSchema: schema,
});

const { value: username, errorMessage: usernameError } = useField('username');
const { value: email, errorMessage: emailError } = useField('email');
const { value: password, errorMessage: passwordError } = useField('password');
const registrationSuccess = ref(false);
const serverMessage = ref('');

const onSubmit = handleSubmit(async values => {
  try {
    const response = await axios.post('http://localhost:8080/auth/register', values);
    serverMessage.value = response.data;
    registrationSuccess.value = true;
    resetForm(); // Clear form values
  } catch (err) {
    serverMessage.value = err.response.data || 'An error occurred during registration.';
    registrationSuccess.value = false;
  }
});

const clearForm = () => {
  resetForm(); // Clear form values
  setErrors({}); // Clear all validation errors
  if (!registrationSuccess.value) {
    serverMessage.value = ''; // Clear server message if registration wasn't successful
  }
};
</script>

<template>
  <div class="form-container">
    <h1>Register</h1>
    <form v-if="!registrationSuccess" @submit.prevent="onSubmit">
      <div>
        <label for="username">Username:</label>
        <input id="username" v-model="username" type="text" :class="{'error-field': usernameError}">
        <span class="error-message">{{ usernameError }}</span>
      </div>
      <div>
        <label for="email">Email:</label>
        <input id="email" v-model="email" type="email" :class="{'error-field': emailError}">
        <span class="error-message">{{ emailError }}</span>
      </div>
      <div>
        <label for="password">Password:</label>
        <input id="password" v-model="password" type="password" :class="{'error-field': passwordError}">
        <span class="error-message">{{ passwordError }}</span>
      </div>
      <div class="actions">
        <button type="submit" :disabled="isSubmitting">Register</button>
        <button type="button" @click="clearForm">Clear</button>
      </div>
      <p class="server-message" v-if="serverMessage">{{ serverMessage }}</p>
    </form>
    <p v-else class="success-message">{{ serverMessage }}</p>
  </div>
</template>

<style scoped>

.form-container {
  max-width: 400px;
  margin: 20px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f9f9f9;
}

label {
  display: block;
  margin-bottom: 5px;
}

input[type="text"],
input[type="email"],
input[type="password"] {
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.error-field {
  border-color: red;
}

.error-message, .server-message {
  color: red;
  font-size: 14px;
}

.success-message {
  color: #008080;
  font-size: 14px;
}

.actions {
  display: flex;
  justify-content: space-between;
}

button {
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button[type="submit"] {
  background-color: #008080;
  color: white;
}

button[type="button"] {
  background-color: #f44336;
  color: white;
}
</style>
