package com.stjook.sbexamples.repository;

import com.stjook.sbexamples.exceptions.EmployeeNotFoundException;
import com.stjook.sbexamples.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by spournar on 4/7/2017.
 */
@Transactional
@Repository
public class BasicEmployeeRepository implements EmployeeRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Employee getEmployeeById(long employeeId) throws EmployeeNotFoundException {
		Employee employee = entityManager.find(Employee.class, employeeId);

		if (employee == null) {
			throw new EmployeeNotFoundException();
		}
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		String hql = "FROM Employee as empl ORDER BY empl.employeeId";
		return (List<Employee>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public void addEmployee(Employee employee) {
		entityManager.persist(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		Employee empl = getEmployeeById(employee.getEmployeeId());
		empl.setFirstName(employee.getFirstName());
		empl.setLastName(employee.getLastName());
		empl.setSocialSecurity(employee.getSocialSecurity());
		empl.setSalary(employee.getSalary());
		entityManager.flush();
	}

	@Override
	public void deleteEmployee(long employeeId) {
		entityManager.remove(getEmployeeById(employeeId));
	}

	@Override
	public void deleteAllEmployees() {
		entityManager.clear();
	}

	@Override
	public boolean isEmployeeExists(long socialSecurity) {
		String hql = "FROM Employee as empl WHERE empl.socialSecurity = ?";
		int count = entityManager.createQuery(hql).setParameter(1, socialSecurity).getResultList().size();
		return count > 0 ? true : false;
	}

}
