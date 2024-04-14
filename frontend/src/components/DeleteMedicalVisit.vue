<template>
  <div class="form-container">
    <h1>Delete Medical Visit</h1>
    <form @submit.prevent="deleteMedicalVisit">
      <div class="input-group">
        <label for="healthInsuranceNumber">Health Insurance Number:</label>
        <input id="healthInsuranceNumber" v-model="deleteInfo.healthInsuranceNumber" type="text" required>
      </div>
      <div class="input-group">
        <label for="medicalVisitId">Medical Visit ID:</label>
        <input id="medicalVisitId" v-model="deleteInfo.medicalVisitId" type="text" required>
      </div>
      <button type="submit">Delete Visit</button>
    </form>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useAuthStore } from '@/stores/useAuthStore';

const { isAuthenticated } = useAuthStore();
const deleteInfo = ref({
  healthInsuranceNumber: '',
  medicalVisitId: ''
});

async function deleteMedicalVisit() {
  if (!isAuthenticated.value) return;

  try {
    const response = await axios.delete(`http://localhost:8080/api/medical-records/delete-medical-visit?healthInsuranceNumber=${deleteInfo.value.healthInsuranceNumber}&medicalVisitId=${deleteInfo.value.medicalVisitId}`);
    console.log('Medical visit deleted:', response.data);
    alert('Medical visit deleted successfully.');
    deleteInfo.value = { healthInsuranceNumber: '', medicalVisitId: '' }; // Reset form
  } catch (error) {
    console.error('Failed to delete medical visit:', error.response.data);
    alert('Failed to delete medical visit: ' + (error.response.data || 'Unknown error'));
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
input[type="text"] {
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
