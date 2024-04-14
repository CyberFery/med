<template>
  <v-container>
    <v-card>
      <v-card-title class="text-h1">Update Medical History</v-card-title>
      <v-card-text v-if="!submitted">
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            v-model="form.healthInsuranceNumber"
            label="Health Insurance Number"
            :rules="[rules.required]"
            required
          ></v-text-field>

          <v-text-field
            v-model="form.medicalHistory.diagnosis"
            label="Diagnosis"
            :rules="[rules.required]"
            required
          ></v-text-field>

          <v-text-field
            v-model="form.medicalHistory.treatment"
            label="Treatment"
            :rules="[rules.required]"
            required
          ></v-text-field>

          <!-- Dynamic Illness List -->
          <div v-for="(illness, index) in form.medicalHistory.illnessList" :key="index">
            <v-text-field
              v-model="illness.description"
              :label="`Illness ${index + 1} Description`"
              :rules="[rules.required]"
              required
            ></v-text-field>
            <v-text-field
              v-model="illness.onsetOfIllnessDate"
              label="Onset Date"
              :rules="[rules.required]"
              required
              type="date"
            ></v-text-field>
            <v-text-field
              v-model="illness.endOfIllnessDate"
              label="End Date"
              :rules="[rules.required]"
              required
              type="date"
            ></v-text-field>
            <v-btn icon color="red" @click="removeIllness(index)">
              <v-icon>mdi-delete</v-icon>
            </v-btn>
          </div>
          <v-btn color="primary" @click="addIllness">Add Illness</v-btn>

          <!-- Doctor's Details -->
          <v-text-field
            v-model="form.medicalHistory.primaryCareDoctor.firstName"
            label="Doctor's First Name"
            :rules="[rules.required]"
            required
          ></v-text-field>
          <v-text-field
            v-model="form.medicalHistory.primaryCareDoctor.lastName"
            label="Doctor's Last Name"
            :rules="[rules.required]"
            required
          ></v-text-field>
          <v-text-field
            v-model="form.medicalHistory.primaryCareDoctor.specialization"
            label="Specialization"
            :rules="[rules.required]"
            required
          ></v-text-field>

          <v-btn :disabled="!valid" color="primary" class="mr-4" @click="submitForm">
            Update Medical History
          </v-btn>
        </v-form>
      </v-card-text>
      <v-card-text v-else>
        <div>
          <h2>Updated Medical History Details</h2>
          <p><strong>Medical History ID:</strong> {{ response.medicalHistoryId }}</p>
          <p><strong>Diagnosis:</strong> {{ response.diagnosis }}</p>
          <p><strong>Treatment:</strong> {{ response.treatment }}</p>
          <p><strong>Primary Care Doctor:</strong> {{ response.primaryCareDoctor.firstName }} {{ response.primaryCareDoctor.lastName }} ({{ response.primaryCareDoctor.specialization }})</p>
          <h3>Illnesses:</h3>
          <ul>
            <li v-for="(illness, index) in response.illnessList" :key="index">
              {{ index + 1 }}. {{ illness.description }} - From: {{ illness.onsetOfIllnessDate }} To: {{ illness.endOfIllnessDate }}
            </li>
          </ul>
        </div>
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
        medicalHistory: {
          diagnosis: '',
          treatment: '',
          illnessList: [],
          primaryCareDoctor: {
            firstName: '',
            lastName: '',
            specialization: ''
          }
        }
      },
      submitted: false,
      error: '',
      response: '',
      rules: {
        required: value => !!value || 'Required.'
      }
    };
  },
  methods: {
    addIllness() {
      this.form.medicalHistory.illnessList.push({
        description: '',
        onsetOfIllnessDate: new Date().toISOString().substr(0, 10),
        endOfIllnessDate: new Date().toISOString().substr(0, 10)
      });
    },
    removeIllness(index) {
      this.form.medicalHistory.illnessList.splice(index, 1);
    },
    async submitForm() {
      if (this.$refs.form.validate()) {
        this.error = '';
        try {
          const { data } = await axiosInstance.put('/medical-records/update-medical-history', this.form);
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
