<script setup lang="ts">
import {useField, useForm} from 'vee-validate'
import axios from 'axios';

const {handleSubmit, handleReset} = useForm({
  validationSchema: {
    userName(value: any) {
      if (value?.length >= 2 && value?.length <= 20) return true

      return 'User name needs to be at least 2 and maximum 20 characters.'
    },

    password(value: any) {
      if (/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&#^])[A-Za-z\d@$!%*?&#^]{8,}$/.test(value)) return true

      return 'Password must meet the following criteria: at least one uppercase letter, at least one lowercase letter, at least one digit, at least one special character (!@#$%^&*), and a minimum length of 8 characters.'
    },
  },
})

const userName = useField('userName')
const password = useField('password')

const submit = handleSubmit(async (values) => {
  try {
    const response = await axios.post('http://localhost:8080/authentication', {
      userName: values.userName,
      password: values.password,
    });
    console.log(response.data); // Handle the response as needed
  } catch (error) {
    console.error('Error submitting form:', error);
  }
});
</script>

<template>
  <h1 class="text-center text-h2 mt-6 mb-4">User Secure Login</h1>

  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="6">
      <form class="text-center" @submit.prevent="submit">
        <v-text-field
          class="my-6"
          v-model="userName.value.value"
          :counter="20"
          :error-messages="userName.errorMessage.value"
          label="User Name"
        />

        <v-text-field
          class="mt-6"
          v-model="password.value.value"
          :error-messages="password.errorMessage.value"
          label="Password"
          type="password"
        />

        <v-container>
          <v-btn
            class="bg-green-accent-1 me-4"
            type="submit"
          >
            Submit
          </v-btn>

          <v-btn class="bg-red-accent-1" @click="handleReset">
            Clear
          </v-btn>
        </v-container>

      </form>
    </v-col>
  </v-row>
</template>

<style scoped>
</style>
