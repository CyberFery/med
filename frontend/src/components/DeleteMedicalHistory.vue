<template>
  <div class="form-container">
    <h1>Delete Medical History</h1>
    <form @submit.prevent="deleteMedicalHistory">
      <div class="input-group">
        <label for="healthInsuranceNumber">Health Insurance Number:</label>
        <input id="healthInsuranceNumber" v-model="historyInfo.healthInsuranceNumber" type="text" required>
      </div>
      <div class="input-group">
        <label for="medicalHistoryId">Medical History ID:</label>
        <input id="medicalHistoryId" v-model="historyInfo.medicalHistoryId" type="text" required>
      </div>
      <button type="submit">Delete History</button>
    </form>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useAuthStore } from '@/stores/useAuthStore';

const { isAuthenticated } = useAuthStore();
const historyInfo = ref({
  healthInsuranceNumber: '',
  medicalHistoryId: ''
});

async function deleteMedicalHistory() {
  if (!isAuthenticated.value) return;

  try {
    const response = await axios.delete(`http://localhost:8080/api/medical-records/delete-medical-history?healthInsuranceNumber=${historyInfo.value.healthInsuranceNumber}&medicalHistoryId=${historyInfo.value.medicalHistoryId}`);
    console.log('Medical history deleted:', response.data);
    alert('Medical history deleted successfully.');
    historyInfo.value = { healthInsuranceNumber: '', medicalHistoryId: '' }; // Reset form
  } catch (error) {
    console.error('Failed to delete medical history:', error.response.data);
    alert('Failed to delete medical history: ' + (error.response.data || 'Unknown error'));
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
