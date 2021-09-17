package by.matmux.simplewebapp.dao;

import by.matmux.simplewebapp.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentDao extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
}
