<template>
  <v-container>
    <v-card>
      <v-card-title class="text-h1">Update Medical Visit</v-card-title>
      <v-card-text>
        <v-form v-if="!response" ref="form" v-model="valid" lazy-validation>
          <v-text-field
            v-model="form.healthInsuranceNumber"
            label="Health Insurance Number"
            :rules="[rules.required]"
            required
          ></v-text-field>

          <v-text-field
            v-model="form.medicalVisit.visitedEstablishment"
            label="Visited Establishment"
            :rules="[rules.required]"
            required
          ></v-text-field>

          <!-- Doctor's details -->
          <v-text-field
            v-model="form.medicalVisit.doctorSeen.firstName"
            label="Doctor's First Name"
            :rules="[rules.required]"
            required
          ></v-text-field>
          <v-text-field
            v-model="form.medicalVisit.doctorSeen.lastName"
            label="Doctor's Last Name"
            :rules="[rules.required]"
            required
          ></v-text-field>
          <v-text-field
            v-model="form.medicalVisit.doctorSeen.specialization"
            label="Specialization"
            :rules="[rules.required]"
            required
          ></v-text-field>

          <!-- Visit date picker -->
          <v-menu
            ref="menu"
            v-model="menu"
            :close-on-content-click="false"
            :return-value.sync="form.medicalVisit.visitDate"
            transition="scale-transition"
            offset-y
            min-width="auto"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="form.medicalVisit.visitDate"
                label="Visit Date"
                readonly
                v-bind="attrs"
                v-on="on"
                :rules="[rules.required]"
                required
              ></v-text-field>
            </template>
            <v-date-picker v-model="form.medicalVisit.visitDate" no-title scrollable>
              <v-spacer></v-spacer>
              <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
              <v-btn text color="primary" @click="$refs.menu.save(form.medicalVisit.visitDate)">OK</v-btn>
            </v-date-picker>
          </v-menu>

          <!-- Dynamic Diagnosis List -->
          <div v-for="(diagnosis, index) in form.medicalVisit.diagnosisList" :key="index">
            <v-text-field
              v-model="diagnosis.description"
              :label="`Diagnosis ${index + 1} Description`"
              :rules="[rules.required]"
              required
            ></v-text-field>
            <v-text-field
              v-model="diagnosis.treatment"
              :label="`Diagnosis ${index + 1} Treatment`"
              :rules="[rules.required]"
              required
            ></v-text-field>
            <v-btn icon color="red" @click="removeDiagnosis(index)">
              <v-icon>mdi-delete</v-icon>
            </v-btn>
          </div>
          <v-btn color="primary" @click="addDiagnosis">Add Diagnosis</v-btn>

          <!-- Textareas for summary and notes -->
          <v-textarea
            v-model="form.medicalVisit.summaryOfTheVisitByDoctor"
            label="Summary of the Visit"
            :rules="[rules.required]"
            required
          ></v-textarea>
          <v-textarea
            v-model="form.medicalVisit.notesForOtherDoctors"
            label="Notes for Other Doctors"
            :rules="[rules.required]"
            required
          ></v-textarea>

          <!-- Submit button -->
          <v-btn :disabled="!valid" color="primary" class="mr-4" @click="submitForm">
            Update Medical Visit
          </v-btn>
        </v-form>

        <!-- Display updated visit details on success -->
        <div v-if="response" class="updated-details">
          <h2>Medical Visit Updated Successfully!</h2>
          <p>Visit ID: {{ response.medicalVisitId }}</p>
          <p>Establishment: {{ response.visitedEstablishment }}</p>
          <p>Doctor Name: {{ response.doctorSeen.firstName }} {{ response.doctorSeen.lastName }}</p>
          <p>Specialization: {{ response.doctorSeen.specialization }}</p>
          <p>Visit Date: {{ response.visitDate }}</p>
          <p>Summary: {{ response.summaryOfTheVisitByDoctor }}</p>
          <p>Notes: {{ response.notesForOtherDoctors }}</p>
          <div v-for="(diagnosis, index) in response.diagnosisList" :key="index">
            <p>Diagnosis {{ index + 1 }}: {{ diagnosis.description }}</p>
            <p>Treatment: {{ diagnosis.treatment }}</p>
          </div>
          <v-btn color="primary" @click="resetForm">Edit Another Visit</v-btn>
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
      menu: false,
      form: {
        healthInsuranceNumber: '',
        medicalVisit: {
          visitedEstablishment: '',
          doctorSeen: {
            firstName: '',
            lastName: '',
            specialization: ''
          },
          visitDate: new Date().toISOString().substr(0, 10),
          diagnosisList: [],
          summaryOfTheVisitByDoctor: '',
          notesForOtherDoctors: ''
        }
      },
      error: '',
      response: '',
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
          const { data } = await axiosInstance.put('/medical-records/update-medical-visit', this.form.medicalVisit, {
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
    },
    resetForm() {
      this.response = '';
      this.form = {
        healthInsuranceNumber: '',
        medicalVisit: {
          visitedEstablishment: '',
          doctorSeen: {
            firstName: '',
            lastName: '',
            specialization: ''
          },
          visitDate: new Date().toISOString().substr(0, 10),
          diagnosisList: [],
          summaryOfTheVisitByDoctor: '',
          notesForOtherDoctors: ''
        }
      };
      this.valid = true;
    }
  }
};
</script>

<style scoped>
</style>
