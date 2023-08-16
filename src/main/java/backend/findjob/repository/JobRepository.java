package backend.findjob.repository;

import backend.findjob.entity.Enum.TypeWork;
import backend.findjob.entity.Enum.TypeWorkPlace;
import backend.findjob.entity.JobEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long>, JpaSpecificationExecutor<JobEntity> {


//    @Query(
//            value = " SELECT job from JobEntity as job " +
//                    " where (:workplace is null or job.type_work_place = :workplace) " +
//                    " and (:jobtype is null or job.type_work = :jobtype)" +
//                    " and (:position is null or job.position = :position)" +
//                    " and (:city is null or job.location like %:city%)" +
//                    " and (:experience is null or job.experience = :experience)" +
//                    " and (:specialization is null or  job.specialization in :specialization)"
//
//
//    )
//    List<JobEntity> findJobByFilter(
//            @Param("workplace") TypeWorkPlace workplace,
//            @Param("jobtype") TypeWork jobtype,
//            @Param("position") String position,
//            @Param("city") String city,
//            @Param("experience") String experience,
//            @Param("specialization") List<String> specialization
//            );
    default List<JobEntity> findJobByFilTer(TypeWorkPlace workplace, TypeWork jobtype
            ,  String position, String city, String experience,List<String> specialization
            ,Double salary_min, Double salary_max)
    {
        Specification<JobEntity> specification = (root, query, criteriaBuilder) ->{
            List<Predicate> predicates = new ArrayList<>();
            if(workplace != null && workplace.getClass() == TypeWorkPlace.class)
            {
                predicates.add(criteriaBuilder.equal(root.get("type_work_place"),workplace));

            }
            if(jobtype != null && jobtype.getClass() == TypeWork.class)
            {
                predicates.add(criteriaBuilder.equal(root.get("type_work"),jobtype));

            }
            if(experience != null)
            {
                predicates.add(criteriaBuilder.equal(root.get("experience"),experience));

            }
            if(position != null)
            {
                predicates.add(criteriaBuilder.equal(root.get("position"),position));

            }
            if(city != null )
            {
                predicates.add(criteriaBuilder.like(root.get("location"),"%"+city+"%"));

            }
            if(specialization != null && specialization.isEmpty() != true)
            {
                predicates.add(root.get("specialization").in(specialization));
            }
            if(salary_max != null && salary_min != null)
            {
                Predicate compareGreater = criteriaBuilder.greaterThanOrEqualTo(root.get("salary"),salary_min);
                Predicate compareLess = criteriaBuilder.lessThanOrEqualTo(root.get("salary"),salary_max);
                Predicate andCompare = criteriaBuilder.and(compareGreater,compareLess);
                predicates.add(andCompare);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
        return findAll(specification);
    }




}
