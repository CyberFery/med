<template>
  <v-container fluid class="grey lighten-5">
    <v-card outlined>
      <v-card-title class="justify-center text-h4 my-4">Delete Medical History</v-card-title>
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
            v-model="form.medicalHistoryId"
            label="Medical History ID"
            type="number"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-text-field>
          <v-btn :disabled="!valid" color="black" class="mr-4" @click="submitForm">
            Delete Medical History
          </v-btn>
        </v-form>
      </v-card-text>
      <v-card-text v-else class="pa-4">
        <v-timeline align-top dense>
          <v-timeline-item fill-dot>
            <template v-slot:opposite>
              <strong>Deleted Medical History Details</strong>
            </template>
            <div>
              <p><strong>Medical History ID:</strong> {{ response.medicalHistoryId }}</p>
              <p><strong>Diagnosis:</strong> {{ response.diagnosis }}</p>
              <p><strong>Treatment:</strong> {{ response.treatment }}</p>
              <p><strong>Primary Care Doctor:</strong> Dr. {{ response.primaryCareDoctor.firstName }} {{ response.primaryCareDoctor.lastName }} ({{ response.primaryCareDoctor.specialization }})</p>
              <h3>Illnesses:</h3>
              <ul>
                <li v-for="(illness, index) in response.illnessList" :key="index">
                  {{ index + 1 }}. {{ illness.description }} - From: {{ illness.onsetOfIllnessDate }} To: {{ illness.endOfIllnessDate }}
                </li>
              </ul>
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
        medicalHistoryId: null
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
          const { data } = await axiosInstance.delete('/medical-records/delete-medical-history', {
            headers: {
              'healthInsuranceNumber': this.form.healthInsuranceNumber
            },
            params: {
              medicalHistoryId: this.form.medicalHistoryId
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
