package backend.findjob.repository;

import backend.findjob.entity.CVEntity;
import backend.findjob.entity.WorkExpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkExpRepository extends JpaRepository<WorkExpEntity, Long> {

}
