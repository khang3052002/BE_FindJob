package backend.findjob.repository;

import backend.findjob.entity.ImageUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageUserRepository extends JpaRepository<ImageUserEntity, Long> {
}
