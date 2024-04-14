<template>
  <div class="form-container">
    <h1>Update Medical Visit</h1>
    <form @submit.prevent="updateMedicalVisit">
      <div class="input-group">
        <label for="healthInsuranceNumber">Health Insurance Number:</label>
        <input id="healthInsuranceNumber" v-model="visitInfo.healthInsuranceNumber" type="text" required>
      </div>
      <div class="input-group">
        <label for="visitDetails">Visit Details:</label>
        <textarea id="visitDetails" v-model="visitInfo.visitDetails" placeholder="Enter visit details" required></textarea>
      </div>
      <button type="submit">Update Visit</button>
    </form>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useAuthStore } from '@/stores/useAuthStore';

const { isAuthenticated } = useAuthStore();
const visitInfo = ref({
  healthInsuranceNumber: '',
  visitDetails: ''
});

async function updateMedicalVisit() {
  if (!isAuthenticated.value) return;

  try {
    const response = await axios.put('http://localhost:8080/api/medical-records/update-medical-visit', visitInfo.value);
    console.log('Medical visit updated:', response.data);
    alert('Medical visit updated successfully.');
    visitInfo.value = { healthInsuranceNumber: '', visitDetails: '' }; // Reset form
  } catch (error) {
    console.error('Failed to update medical visit:', error.response.data);
    alert('Failed to update medical visit: ' + (error.response.data || 'Unknown error'));
  }
}
</script>

<style scoped>
.form-container {
  max-width: 400px;
  margin: auto;
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
input[type="text"], textarea {
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
</style>
