<template>
  <v-container fluid class="grey lighten-5">
    <v-card outlined>
      <v-card-title class="justify-center text-h4 my-4">Delete Medical Visit</v-card-title>
      <v-card-text v-if="!submitted">
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            v-model="form.healthInsuranceNumber"
            label="Health Insurance Number"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-text-field>
          <v-text-field
            v-model="form.medicalVisitId"
            label="Medical Visit ID"
            type="number"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-text-field>
          <v-btn :disabled="!valid" color="black" class="mr-4" @click="submitForm">
            Delete Medical Visit
          </v-btn>
        </v-form>
      </v-card-text>
      <v-card-text v-else class="pa-4">
        <v-timeline align-top dense>
          <v-timeline-item fill-dot>
            <template v-slot:opposite>
              <strong>Deleted Medical Visit Details</strong>
            </template>
            <div>
              <p><strong>Visit Establishment:</strong> {{ response.visitedEstablishment }}</p>
              <p><strong>Doctor Seen:</strong> Dr. {{ response.doctorSeen.firstName }} {{ response.doctorSeen.lastName }} ({{ response.doctorSeen.specialization }})</p>
              <p><strong>Visit Date:</strong> {{ response.visitDate }}</p>
              <p><strong>Summary of the Visit:</strong> {{ response.summaryOfTheVisitByDoctor }}</p>
              <p><strong>Notes for Other Doctors:</strong> {{ response.notesForOtherDoctors }}</p>
              <div v-if="response.diagnosisList && response.diagnosisList.length">
                <h3>Diagnoses:</h3>
                <ul>
                  <li v-for="(diagnosis, index) in response.diagnosisList" :key="index">
                    {{ index + 1 }}. {{ diagnosis.description }} - Treatment: {{ diagnosis.treatment }}
                  </li>
                </ul>
              </div>
            </div>
          </v-timeline-item>
        </v-timeline>
      </v-card-text>
      <!-- Alerts for error and success messages -->
      <v-alert v-if="error" type="error" :value="true">
        {{ error }}
      </v-alert>
    </v-card>
  </v-container>
</template>

<script>
import axiosInstance from '@/axiosInstance';

export default {
  data() {
    return {
      valid: true,
      form: {
        healthInsuranceNumber: '',
        medicalVisitId: null,
      },
      submitted: false,
      error: '',
      response: {},
      rules: {
        required: value => !!value || 'Required.'
      }
    };
  },
  methods: {
    async submitForm() {
      if (this.$refs.form.validate()) {
        this.error = '';
        try {
          const { data } = await axiosInstance.delete('/medical-records/delete-medical-visit', {
            headers: {
              'healthInsuranceNumber': this.form.healthInsuranceNumber
            },
            params: {
              medicalVisitId: this.form.medicalVisitId
            }
          });
          this.response = data;
          this.submitted = true;
        } catch (error) {
          this.error = error.response.data;
          this.submitted = false;
        }
      }
    }
  }
};
</script>

<style scoped>
</style>
