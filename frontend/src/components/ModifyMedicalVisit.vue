<template>
  <v-container fluid class="grey lighten-5">
    <v-card outlined>
      <v-card-title class="justify-center text-h4 my-4">Update Medical Visit</v-card-title>
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
            v-model="form.medicalVisit.medicalVisitId"
            label="Medical Visit ID"
            type="number"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-text-field>
          <v-text-field
            v-model="form.medicalVisit.visitedEstablishment"
            label="Visited Establishment"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-text-field>

          <v-text-field
            v-model="form.medicalVisit.doctorSeen.firstName"
            label="Doctor's First Name"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-text-field>
          <v-text-field
            v-model="form.medicalVisit.doctorSeen.lastName"
            label="Doctor's Last Name"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-text-field>
          <v-text-field
            v-model="form.medicalVisit.doctorSeen.specialization"
            label="Specialization"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-text-field>

          <v-text-field
            v-model="form.medicalVisit.visitDate"
            label="Visit Date"
            type="date"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-text-field>

          <!-- Dynamic diagnosis input fields -->
          <div v-for="(diagnosis, index) in form.medicalVisit.diagnosisList" :key="index">
            <v-text-field
              v-model="diagnosis.description"
              :label="`Diagnosis ${index + 1} Description`"
              :rules="[rules.required]"
              required
              color="black"
              outlined
            ></v-text-field>
            <v-text-field
              v-model="diagnosis.treatment"
              :label="`Diagnosis ${index + 1} Treatment`"
              :rules="[rules.required]"
              required
              color="black"
              outlined
            ></v-text-field>
            <v-btn icon color="black" @click="removeDiagnosis(index)">
              <v-icon>mdi-delete</v-icon>
            </v-btn>
          </div>
          <v-btn color="black" @click="addDiagnosis">Add Diagnosis</v-btn>

          <v-textarea
            v-model="form.medicalVisit.summaryOfTheVisitByDoctor"
            label="Summary of the Visit"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-textarea>
          <v-textarea
            v-model="form.medicalVisit.notesForOtherDoctors"
            label="Notes for Other Doctors"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-textarea>

          <v-btn :disabled="!valid" color="black" class="mr-4" @click="submitForm">
            Update Medical Visit
          </v-btn>
        </v-form>

        <v-timeline v-if="submitted" align-top dense>
          <v-timeline-item fill-dot>
            <template v-slot:opposite>
              <strong>Medical Visit Updated Successfully</strong>
            </template>
            <div>
              <p><strong>Visit ID:</strong> {{ response.medicalVisitId }}</p>
              <p><strong>Establishment:</strong> {{ response.visitedEstablishment }}</p>
              <p><strong>Doctor Name:</strong> Dr. {{ response.doctorSeen.firstName }} {{ response.doctorSeen.lastName }} ({{ response.doctorSeen.specialization }})</p>
              <p><strong>Visit Date:</strong> {{ response.visitDate }}</p>
              <p><strong>Summary:</strong> {{ response.summaryOfTheVisitByDoctor }}</p>
              <p><strong>Notes:</strong> {{ response.notesForOtherDoctors }}</p>
              <div v-for="(diagnosis, index) in response.diagnosisList" :key="index">
                <p><strong>Diagnosis {{ index + 1 }}:</strong> {{ diagnosis.description }}</p>
                <p><strong>Treatment:</strong> {{ diagnosis.treatment }}</p>
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
        medicalVisit: {
          medicalVisitId: '',
          visitedEstablishment: '',
          doctorSeen: {
            firstName: '',
            lastName: '',
            specialization: ''
          },
          visitDate: '',
          diagnosisList: [],
          summaryOfTheVisitByDoctor: '',
          notesForOtherDoctors: ''
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
    addDiagnosis() {
      this.form.medicalVisit.diagnosisList.push({
        description: '',
        treatment: ''
      });
    },
    removeDiagnosis(index) {
      this.form.medicalVisit.diagnosisList.splice(index, 1);
    },
    async submitForm() {
      if (this.$refs.form.validate()) {
        this.error = '';
        try {
          const { data } = await axiosInstance.put('/medical-records/update-medical-visit-by-id', this.form.medicalVisit, {
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
