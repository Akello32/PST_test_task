package by.matmux.simplewebapp.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class EmployeeDto {
    private Long id;
    private String firstname;
    private String lastname;
    private List<String> jobTitles;
    private String department;
}
