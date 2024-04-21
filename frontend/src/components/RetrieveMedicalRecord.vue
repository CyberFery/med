<template>
  <v-container fluid class="grey lighten-5">
    <v-card outlined>
      <v-card-title class="justify-center text-h4 my-4">Retrieve Medical Record</v-card-title>
      <v-card-text v-if="!submitted">
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            v-model="healthInsuranceNumber"
            label="Health Insurance Number"
            :rules="[rules.required]"
            required
            color="black"
            outlined
          ></v-text-field>
          <v-btn :disabled="!valid" class="mr-4" color="black" @click="fetchMedicalRecord">
            Retrieve Record
          </v-btn>
        </v-form>
      </v-card-text>
      <v-card-text v-else class="pa-4">
        <v-timeline align-top dense >
          <v-timeline-item  fill-dot>
            <template v-slot:opposite>
              <strong>Patient Information</strong>
            </template>
            <div>
              <p><strong>ID:</strong> {{ response.medicalRecordId }}</p>
              <p><strong>Health Insurance Number:</strong> {{ response.patient.healthInsuranceNumber }}</p>
              <p><strong>Name:</strong> {{ response.patient.firstName }} {{ response.patient.lastName }}</p>
              <p><strong>Gender:</strong> {{ response.patient.genre }}</p>
              <p><strong>Date of Birth:</strong> {{ response.patient.dateOfBirth }}</p>
              <p><strong>City of Birth:</strong> {{ response.patient.cityOfBirth }}</p>
              <p><strong>Diagnosis:</strong> {{ response.patient.establishedDiagnosis }}</p>
            </div>
          </v-timeline-item>

          <v-timeline-item  fill-dot>
            <template v-slot:opposite>
              <strong>Contact Information</strong>
            </template>
            <div>
              <p><strong>Addresses:</strong></p>
              <ul>
                <li v-for="address in response.patient.contactInformation.residentialAddressList" :key="address.address">
                  {{ address.address }}
                </li>
              </ul>
              <p><strong>Phone Numbers:</strong></p>
              <ul>
                <li v-for="phone in response.patient.contactInformation.phoneNumberList" :key="phone.number">
                  {{ phone.number }}
                </li>
              </ul>
              <p><strong>Emails:</strong></p>
              <ul>
                <li v-for="email in response.patient.contactInformation.emailAddressList" :key="email.email">
                  {{ email.email }}
                </li>
              </ul>
            </div>
          </v-timeline-item>

          <v-timeline-item  fill-dot>
            <template v-slot:opposite>
              <strong>Known Parents</strong>
            </template>
            <div>
              <ul>
                <li v-for="parent in response.patient.knownParentList" :key="parent.firstName">
                  {{ parent.firstName }} {{ parent.lastName }}
                </li>
              </ul>
            </div>
          </v-timeline-item>

          <v-timeline-item  fill-dot>
            <template v-slot:opposite>
              <strong>Medical Visits</strong>
            </template>
            <div>
              <ul>
                <li v-for="visit in response.medicalVisitList" :key="visit.medicalVisitId">
                  <p><strong>Visit ID:</strong> {{ visit.medicalVisitId }}</p>
                  <p><strong>Establishment:</strong> {{ visit.visitedEstablishment }}</p>
                  <p><strong>Doctor:</strong> Dr. {{ visit.doctorSeen.firstName }} {{ visit.doctorSeen.lastName }} ({{ visit.doctorSeen.specialization }})</p>
                  <p><strong>Date:</strong> {{ visit.visitDate }}</p>
                  <p><strong>Summary:</strong> {{ visit.summaryOfTheVisitByDoctor }}</p>
                  <p><strong>Notes for Other Doctors:</strong> {{ visit.notesForOtherDoctors }}</p>
                  <p><strong>Diagnoses:</strong></p>
                  <ul>
                    <li v-for="diagnosis in visit.diagnosisList" :key="diagnosis.description">
                      <p>{{ diagnosis.description }}</p>
                      <p><strong>Treatment:</strong> {{ diagnosis.treatment }}</p>
                    </li>
                  </ul>
                </li>
              </ul>
            </div>
          </v-timeline-item>

          <v-timeline-item  fill-dot>
            <template v-slot:opposite>
              <strong>Medical History</strong>
            </template>
            <div>
              <ul>
                <li v-for="history in response.medicalHistoryList" :key="history.medicalHistoryId">
                  <p><strong>History ID:</strong> {{ history.medicalHistoryId }}</p>
                  <p><strong>Diagnosis:</strong> {{ history.diagnosis }}</p>
                  <p><strong>Treatment:</strong> {{ history.treatment }}</p>
                  <p><strong>Primary Care Doctor:</strong> Dr. {{ history.primaryCareDoctor.firstName }} {{ history.primaryCareDoctor.lastName }} ({{ history.primaryCareDoctor.specialization }})</p>
                  <p><strong>Illnesses:</strong></p>
                  <ul>
                    <li v-for="illness in history.illnessList" :key="illness.description">
                      <p>{{ illness.description }} from {{ illness.onsetOfIllnessDate }} to {{ illness.endOfIllnessDate }}</p>
                    </li>
                  </ul>
                </li>
              </ul>
            </div>
          </v-timeline-item>
        </v-timeline>
      </v-card-text>
      <v-alert v-if="error" type="error" :value="true" dense>
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
          const {data} = await axiosInstance.get('/medical-records/patient', {
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
