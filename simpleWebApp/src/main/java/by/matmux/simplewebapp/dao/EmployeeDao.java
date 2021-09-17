package by.matmux.simplewebapp.dao;

import by.matmux.simplewebapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {
    Optional<Employee> findByFirstnameAndLastname(String firstname, String lastname);

    Optional<Employee> findById(Long id);
}
