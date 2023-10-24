package backend.findjob.repository;

import backend.findjob.entity.AppreciateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppreciateRepository extends JpaRepository<AppreciateEntity,Long> {
}
