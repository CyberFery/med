<template>
  <div class="form-container">
    <h1>Update Medical History</h1>
    <form @submit.prevent="updateMedicalHistory">
      <div class="input-group">
        <label for="healthInsuranceNumber">Health Insurance Number:</label>
        <input id="healthInsuranceNumber" v-model="historyInfo.healthInsuranceNumber" type="text" required>
      </div>
      <div class="input-group">
        <label for="medicalHistoryId">Medical History ID:</label>
        <input id="medicalHistoryId" v-model="historyInfo.medicalHistoryId" type="text" required>
      </div>
      <div class="input-group">
        <label for="historyDetails">History Details:</label>
        <textarea id="historyDetails" v-model="historyInfo.historyDetails" placeholder="Enter updated history details" required></textarea>
      </div>
      <button type="submit">Update History</button>
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
  medicalHistoryId: '',
  historyDetails: ''
});

async function updateMedicalHistory() {
  if (!isAuthenticated.value) return;

  try {
    const response = await axios.put('http://localhost:8080/api/medical-records/update-medical-history', historyInfo.value);
    console.log('Medical history updated:', response.data);
    alert('Medical history updated successfully.');
    historyInfo.value = { healthInsuranceNumber: '', medicalHistoryId: '', historyDetails: '' }; // Reset form
  } catch (error) {
    console.error('Failed to update medical history:', error.response.data);
    alert('Failed to update medical history: ' + (error.response.data || 'Unknown error'));
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
