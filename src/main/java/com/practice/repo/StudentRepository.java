package com.practice.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.practice.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {
	
	Optional<Student> findByemail(String email);
	                  
	List<Student> findBycourse(String course);
	
	@Query("select s from Student s where s.email=:email and s.mobile=:mobile ")
	Optional<Student> findByEmailandMobile(@Param("email")String email ,@Param("mobile") int mobile);
	
	/*@Query("SELECT s FROM Student s WHERE s.email = ?1 AND s.mobile = ?2")
	Student findByEmailandCourse(String email, int mobile);
	
	@Query(value = "SELECT email, mobile FROM student WHERE email = :email AND mobile = :mobile", nativeQuery = true)
	EmailMobileProjection findEmailAndMobile(@Param("email") String email, @Param("mobile") int mobile);
	*/


}
