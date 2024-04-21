<template>
  <v-container fluid class="grey lighten-5">
    <v-card outlined>
      <v-card-title class="justify-center text-h4 my-4">Modify Medical History</v-card-title>
      <v-card-text>
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
            v-model="form.medicalHistory.medicalHistoryId"
            label="Medical History ID"
            type="number"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-text-field>

          <v-text-field
            v-model="form.medicalHistory.diagnosis"
            label="Diagnosis"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-text-field>

          <v-text-field
            v-model="form.medicalHistory.treatment"
            label="Treatment"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-text-field>

          <!-- Dynamic illness input fields with delete option -->
          <div v-for="(illness, index) in form.medicalHistory.illnessList" :key="index" class="my-2">
            <v-text-field
              v-model="illness.description"
              :label="`Illness ${index + 1} Description`"
              :rules="[rules.required]"
              required
              color="black"
              outlined
            ></v-text-field>
            <v-text-field
              v-model="illness.onsetOfIllnessDate"
              label="Onset of Illness Date"
              type="date"
              :rules="[rules.required]"
              required
              color="black"
              outlined
            ></v-text-field>
            <v-text-field
              v-model="illness.endOfIllnessDate"
              label="End of Illness Date"
              type="date"
              :rules="[rules.required]"
              required
              color="black"
              outlined
            ></v-text-field>
            <v-btn icon color="black" @click="removeIllness(index)">
              <v-icon>mdi-delete</v-icon>
            </v-btn>
          </div>
          <v-btn color="black" @click="addIllness">Add Illness</v-btn>

          <!-- Primary Care Doctor Details -->
          <v-text-field
            v-model="form.medicalHistory.primaryCareDoctor.firstName"
            label="Doctor's First Name"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-text-field>
          <v-text-field
            v-model="form.medicalHistory.primaryCareDoctor.lastName"
            label="Doctor's Last Name"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-text-field>
          <v-text-field
            v-model="form.medicalHistory.primaryCareDoctor.specialization"
            label="Specialization"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-text-field>

          <v-btn :disabled="!valid" color="black" class="mr-4" @click="submitForm">
            Update Medical History
          </v-btn>
        </v-form>

        <v-timeline v-if="submitted" align-top dense>
          <v-timeline-item fill-dot>
            <template v-slot:opposite>
              <strong>Medical History Updated Successfully</strong>
            </template>
            <div>
              <p><strong>Medical History ID:</strong> {{ response.medicalHistoryId }}</p>
              <p><strong>Diagnosis:</strong> {{ response.diagnosis }}</p>
              <p><strong>Treatment:</strong> {{ response.treatment }}</p>
              <p><strong>Primary Care Doctor:</strong> {{ response.primaryCareDoctor.firstName }} {{ response.primaryCareDoctor.lastName }} ({{ response.primaryCareDoctor.specialization }})</p>
              <div v-for="(illness, index) in response.illnessList" :key="`response-illness-${index}`">
                <p><strong>Illness {{ index + 1 }} Description:</strong> {{ illness.description }}</p>
                <p><strong>Onset of Illness Date:</strong> {{ illness.onsetOfIllnessDate }}</p>
                <p><strong>End of Illness Date:</strong> {{ illness.endOfIllnessDate }}</p>
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
      form: {
        healthInsuranceNumber: '',
        medicalHistory: {
          medicalHistoryId: '',
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
      valid: true,
      submitted: false,
      error: '',
      response: {},
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
          const {data} = await axiosInstance.put('/medical-records/update-medical-history-by-id', this.form.medicalHistory, {
            headers: {
              'healthInsuranceNumber': this.form.healthInsuranceNumber
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
