package personal.israel.apidemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.israel.apidemo.models.SchoolDistrict;

@Repository
public interface SchoolDistrictRepository extends JpaRepository<SchoolDistrict, Integer> {
    SchoolDistrict findById(Long id);
}
