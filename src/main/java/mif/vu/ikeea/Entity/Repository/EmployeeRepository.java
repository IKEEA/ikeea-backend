package mif.vu.ikeea.Entity.Repository;

import mif.vu.ikeea.Entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
