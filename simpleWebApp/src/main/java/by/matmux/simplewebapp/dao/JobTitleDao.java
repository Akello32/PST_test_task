package by.matmux.simplewebapp.dao;

import by.matmux.simplewebapp.entity.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobTitleDao extends JpaRepository<JobTitle, Long> {
    Optional<JobTitle> findByName(String name);
}
