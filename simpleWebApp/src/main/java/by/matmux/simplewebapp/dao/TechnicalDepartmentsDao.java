package by.matmux.simplewebapp.dao;

import by.matmux.simplewebapp.entity.TechnicalDepartments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalDepartmentsDao extends JpaRepository<TechnicalDepartments, Long> {
}
