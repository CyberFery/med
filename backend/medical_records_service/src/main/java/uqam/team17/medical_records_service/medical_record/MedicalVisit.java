package uqam.team17.medical_records_service.medical_record;

import uqam.team17.medical_records_service.model.user.Physician;

import java.util.Date;
import java.util.List;

class MedicalVisit {
    private String visitedEstablishment;
    private Physician physicianSeen;
    private Date visitDate;
    private List<Diagnosis> diagnosisList;
    private String summaryOfTheVisitByDoctor;
    private String notesForOtherDoctors;

    MedicalVisit(String visitedEstablishment, Physician physicianSeen, Date visitDate, List<Diagnosis> diagnosisList, String summaryOfTheVisitByDoctor, String notesForOtherDoctors) {
        this.visitedEstablishment = visitedEstablishment;
        this.physicianSeen = physicianSeen;
        this.visitDate = visitDate;
        this.diagnosisList = diagnosisList;
        this.summaryOfTheVisitByDoctor = summaryOfTheVisitByDoctor;
        this.notesForOtherDoctors = notesForOtherDoctors;
    }

    public String getVisitedEstablishment() {
        return visitedEstablishment;
    }

    public void setVisitedEstablishment(String visitedEstablishment) {
        this.visitedEstablishment = visitedEstablishment;
    }

    public Physician getPhysicianSeen() {
        return physicianSeen;
    }

    public void setPhysicianSeen(Physician physicianSeen) {
        this.physicianSeen = physicianSeen;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public List<Diagnosis> getDiagnosisList() {
        return diagnosisList;
    }

    public void setDiagnosisList(List<Diagnosis> diagnosisList) {
        this.diagnosisList = diagnosisList;
    }

    public String getSummaryOfTheVisitByDoctor() {
        return summaryOfTheVisitByDoctor;
    }

    public void setSummaryOfTheVisitByDoctor(String summaryOfTheVisitByDoctor) {
        this.summaryOfTheVisitByDoctor = summaryOfTheVisitByDoctor;
    }

    public String getNotesForOtherDoctors() {
        return notesForOtherDoctors;
    }

    public void setNotesForOtherDoctors(String notesForOtherDoctors) {
        this.notesForOtherDoctors = notesForOtherDoctors;
    }
}

