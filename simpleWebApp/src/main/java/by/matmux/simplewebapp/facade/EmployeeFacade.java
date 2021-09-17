package by.matmux.simplewebapp.facade;

import by.matmux.simplewebapp.dto.EmployeeDto;
import by.matmux.simplewebapp.entity.Employee;
import by.matmux.simplewebapp.entity.JobTitle;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EmployeeFacade {
    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(employee.getId());
        employeeDto.setFirstname(employee.getFirstname());
        employeeDto.setLastname(employee.getLastname());
        employeeDto.setJobTitles(employee.getJobTitles()
                                        .stream()
                                        .map(JobTitle::getName)
                                        .collect(Collectors.toList()));
        employeeDto.setDepartment(employee.getDepartment().getName());

        return employeeDto;
    }
}
