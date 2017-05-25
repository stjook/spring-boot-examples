package com.stjook.sbexamples.repository;

import com.stjook.sbexamples.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by spournar on 24/5/2017.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query("select count(e)>0 from Employee e where e.socialSecurity = :socialSecurity")
	boolean existsBySocialSecurity(@Param("socialSecurity") long socialSecurity);
}
