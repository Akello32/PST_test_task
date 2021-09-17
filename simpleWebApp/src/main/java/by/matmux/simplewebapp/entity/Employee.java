package by.matmux.simplewebapp.entity;

import by.matmux.simplewebapp.entity.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, updatable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(updatable = false)
    private LocalDate dateOfBirth;

    @Column(updatable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    private Department department;

    @ManyToMany(mappedBy = "employees", fetch = FetchType.EAGER)
    private List<JobTitle>  jobTitles;
}
