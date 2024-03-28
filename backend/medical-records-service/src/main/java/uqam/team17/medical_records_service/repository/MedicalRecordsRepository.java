package uqam.team17.medical_records_service.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uqam.team17.medical_records_service.model.Person;
@Repository
public interface MedicalRecordsRepository extends CrudRepository<Person, Long>{
}