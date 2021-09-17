package by.matmux.simplewebapp.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeToSave {
    @NotEmpty(message = "Firstname cannot be empty")
    private String firstname;
    @NotEmpty(message = "Lastname cannot be empty")
    private String lastname;
    @NotEmpty(message = "Job title cannot be empty")
    private List<String> jobTitles;
    @NotEmpty(message = "Department cannot be empty")
    private String department;
    @NotEmpty(message = "Gender cannot be empty")
    private String gender;
    @NotNull(message = "Date of birth cannot be empty")
    private LocalDate dateOfBirth;
}
