<template>
  <v-container>
    <v-card>
      <v-card-title class="text-h1">Retrieve Medical Record</v-card-title>
      <v-card-text v-if="!submitted">
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            v-model="healthInsuranceNumber"
            label="Health Insurance Number"
            :rules="[rules.required]"
            required
          ></v-text-field>
          <v-btn :disabled="!valid" color="primary" class="mr-4" @click="fetchMedicalRecord">
            Retrieve Record
          </v-btn>
        </v-form>
      </v-card-text>
      <v-card-text v-else>
        <div>
          <h2>Medical Record Details</h2>
          <p><strong>Medical Record ID:</strong> {{ response.medicalRecordId }}</p>
          <p><strong>Health Insurance Number:</strong> {{ response.patient.healthInsuranceNumber }}</p>
          <p><strong>Patient Name:</strong> {{ response.patient.firstName }} {{ response.patient.lastName }}</p>
          <p><strong>Gender:</strong> {{ response.patient.genre }}</p>
          <p><strong>Date of Birth:</strong> {{ response.patient.dateOfBirth }}</p>
          <p><strong>City of Birth:</strong> {{ response.patient.cityOfBirth }}</p>
          <p><strong>Established Diagnosis:</strong> {{ response.patient.establishedDiagnosis }}</p>

          <h3>Known Parents:</h3>
          <ul>
            <li v-for="parent in response.patient.knownParentList" :key="parent.firstName">
              {{ parent.firstName }} {{ parent.lastName }}
            </li>
          </ul>

          <h3>Contact Information:</h3>
          <p>Addresses:</p>
          <ul>
            <li v-for="address in response.patient.contactInformation.residentialAddressList" :key="address.address">
              {{ address.address }}
            </li>
          </ul>
          <p>Phone Numbers:</p>
          <ul>
            <li v-for="phone in response.patient.contactInformation.phoneNumberList" :key="phone.number">
              {{ phone.number }}
            </li>
          </ul>
          <p>Emails:</p>
          <ul>
            <li v-for="email in response.patient.contactInformation.emailAddressList" :key="email.email">
              {{ email.email }}
            </li>
          </ul>

          <h3>Medical Visits:</h3>
          <ul>
            <li v-for="visit in response.medicalVisitList" :key="visit.medicalVisitId">
              <strong>Visit ID:</strong> {{ visit.medicalVisitId }} at {{ visit.visitedEstablishment }}
              by Dr. {{ visit.doctorSeen.firstName }} {{ visit.doctorSeen.lastName }}
              ({{ visit.doctorSeen.specialization }}) on {{ visit.visitDate }}.
              <strong>Summary:</strong> {{ visit.summaryOfTheVisitByDoctor }}
              <strong>Notes for Other Doctors:</strong> {{ visit.notesForOtherDoctors }}
              <ul>
                <li v-for="diagnosis in visit.diagnosisList" :key="diagnosis.description">
                  Diagnosis: {{ diagnosis.description }} - Treatment: {{ diagnosis.treatment }}
                </li>
              </ul>
            </li>
          </ul>

          <h3>Medical History:</h3>
          <ul>
            <li v-for="history in response.medicalHistoryList" :key="history.medicalHistoryId">
              <strong>History ID:</strong> {{ history.medicalHistoryId }} - Diagnosis: {{ history.diagnosis }}
              Treatment: {{ history.treatment }}
              <p><strong>Primary Care Doctor:</strong> {{ history.primaryCareDoctor.firstName }} {{ history.primaryCareDoctor.lastName }} ({{ history.primaryCareDoctor.specialization }})</p>
              <ul>
                <li v-for="illness in history.illnessList" :key="illness.description">
                  Illness: {{ illness.description }} from {{ illness.onsetOfIllnessDate }} to {{ illness.endOfIllnessDate }}
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </v-card-text>
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
      healthInsuranceNumber: '',
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
    async fetchMedicalRecord() {
      if (this.$refs.form.validate()) {
        this.error = '';
        try {
          const { data } = await axiosInstance.get('/medical-records/patient', {
            headers: {
              'healthInsuranceNumber': this.healthInsuranceNumber
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
