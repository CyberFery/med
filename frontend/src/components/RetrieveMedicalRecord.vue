<template>
  <v-container>
    <v-btn color="primary" @click="fetchMedicalRecords">
      Fetch Medical Records
    </v-btn>
    <v-alert v-if="error" type="error" class="mt-3">
      {{ error }}
    </v-alert>
    <v-card v-if="medicalRecord" class="mt-3">
      <v-card-title>
        Patient: {{ medicalRecord.patient.firstName }} {{ medicalRecord.patient.lastName }}
      </v-card-title>
      <v-card-text>
        <div>Health Insurance Number: {{ medicalRecord.patient.healthInsuranceNumber }}</div>
        <div>Birth Date: {{ medicalRecord.patient.dateOfBirth }}</div>
        <div>Diagnosis: {{ medicalRecord.patient.establishedDiagnosis }}</div>
        <div v-for="visit in medicalRecord.medicalVisitList" :key="visit.medicalVisitId">
          <h3>Visit to {{ visit.visitedEstablishment }} on {{ visit.visitDate }}</h3>
          <div>
            Doctor Seen: {{ visit.doctorSeen.firstName }} {{ visit.doctorSeen.lastName }} ({{ visit.doctorSeen.specialization }})
          </div>
          <div>Summary: {{ visit.summaryOfTheVisitByDoctor }}</div>
          <div>Notes: {{ visit.notesForOtherDoctors }}</div>
        </div>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      medicalRecord: null,
      error: null,
    };
  },
  methods: {
    async fetchMedicalRecords() {
      const requestOptions = {
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
        data: {
          healthInsuranceNumber: 'HETG7755583271'
        },
        url: 'http://localhost:8083/medical-records/patient',
      };
      try {
        const response = await axios(requestOptions);
        this.medicalRecord = response.data;
        this.error = null;
      } catch (error) {
        console.error('Error fetching medical records:', error);
        this.error = error.response ? error.response.data : 'An unknown error occurred';
        this.medicalRecord = null;
      }
    },
  },
};
</script>

<style scoped>
</style>
