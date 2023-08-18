package backend.findjob.repository;

import backend.findjob.dto.ListDTO;
import backend.findjob.entity.CompanyEntity;
import backend.findjob.entity.Enum.TypeWork;
import backend.findjob.entity.Enum.TypeWorkPlace;
import backend.findjob.entity.JobEntity;
import backend.findjob.entity.ProvinceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.JoinTable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.*;

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
//    )
//    List<JobEntity> findJobByFilter(
//            @Param("keyword") String  keyword,
//            @Param("city") String code_city
//            );
    default ListDTO findJobByFilTer(TypeWorkPlace workplace, TypeWork jobtype
            , String position, String city, String experience, List<String> specialization
            , Double salary_min, Double salary_max, Pageable pageable)
    {

//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

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

                //"province ở đây là thuộc tính province trong JobEntity"
                Join<JobEntity, ProvinceEntity> provinceJoin = root.join("province", JoinType.INNER);

//                System.out.println(provinceJoin.get("id"));
//                System.out.println(root.get("location"));
//
//                System.out.println(root.get("province"));

                predicates.add(criteriaBuilder.equal(provinceJoin.get("code"), city));
//                predicates.add(criteriaBuilder.like(root.get("location"),"%"+city+"%"));

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
        Long totalItem = count(specification);
        List<JobEntity> list = findAll(specification,pageable).stream().toList();
        ListDTO listRespone = new ListDTO(totalItem, (List<JobEntity>)list);
        return listRespone;
    }


    default ListDTO findJobByCodeCityAndKeyword(String code_city, String keyword, Pageable pageable)
    {
        Specification<JobEntity> specification = (root, query, criteriaBuilder) ->{
            List<Predicate> predicates = new ArrayList<>();

            if(keyword != null)
            {

                Predicate titlePredicate = criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), "%"+keyword.toUpperCase()+"%");

                Join<JobEntity, CompanyEntity> companyJoin = root.join("company", JoinType.INNER);
                Predicate companyPredicate = criteriaBuilder.like(criteriaBuilder.upper(companyJoin.get("name")),"%"+keyword.toUpperCase()+"%");

                Predicate orPredicate = criteriaBuilder.or(titlePredicate,companyPredicate);


                predicates.add(orPredicate);

            }
            if(code_city != null )
            {

                //"province ở đây là thuộc tính province trong JobEntity"
                Join<JobEntity, ProvinceEntity> provinceJoin = root.join("province", JoinType.INNER);

//                System.out.println(provinceJoin.get("id"));
//                System.out.println(root.get("location"));
//
//                System.out.println(root.get("province"));

                predicates.add(criteriaBuilder.equal(provinceJoin.get("code"), code_city));
//                predicates.add(criteriaBuilder.like(root.get("location"),"%"+city+"%"));

            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };

        Long totalItem = count(specification);
        List<JobEntity> list = findAll(specification,pageable).stream().toList();
        ListDTO listRespone = new ListDTO(totalItem, (List<JobEntity>)list);
        return listRespone;
    }




}
