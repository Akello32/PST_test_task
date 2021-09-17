package by.matmux.simplewebapp.service;

import by.matmux.simplewebapp.dao.DepartmentDao;
import by.matmux.simplewebapp.dao.EmployeeDao;
import by.matmux.simplewebapp.dao.JobTitleDao;
import by.matmux.simplewebapp.dto.EmployeeDto;
import by.matmux.simplewebapp.entity.Employee;
import by.matmux.simplewebapp.entity.JobTitle;
import by.matmux.simplewebapp.entity.enums.Gender;
import by.matmux.simplewebapp.exception.DepartmentNotFoundException;
import by.matmux.simplewebapp.exception.EmployeeNotFoundException;
import by.matmux.simplewebapp.exception.JobTitleNotFoundException;
import by.matmux.simplewebapp.payload.request.EmployeeToSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeDao employeeDao;
    private final DepartmentDao departmentDao;
    private final JobTitleDao jobTitleDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao, DepartmentDao departmentDao, JobTitleDao jobTitleDao) {
        this.employeeDao = employeeDao;
        this.departmentDao = departmentDao;
        this.jobTitleDao = jobTitleDao;
    }

    public Employee createEmployee(EmployeeToSave employeeToSave) {
        Employee employee = new Employee();

        List<JobTitle> jobTitles = new ArrayList<>();
        employeeToSave.getJobTitles().stream()
                .forEach(j -> jobTitles.add(jobTitleDao.findByName(j)
                        .orElseThrow(() -> new JobTitleNotFoundException("Job title not found"))));
        employee.setJobTitles(jobTitles);

        employee.setFirstname(employeeToSave.getFirstname());
        employee.setLastname(employeeToSave.getLastname());
        employee.setDateOfBirth(employeeToSave.getDateOfBirth());
        employee.setGender(Gender.valueOf(employeeToSave.getGender().toUpperCase()));
        employee.setDepartment(departmentDao.findByName(employeeToSave.getDepartment())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found")));

        Employee savedEmployee = employeeDao.save(employee);

        jobTitles.forEach(j -> j.getEmployees().add(savedEmployee));
        jobTitles.forEach(jobTitleDao::save);

        return savedEmployee;
    }

    public Employee updateEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeDao.getById(employeeDto.getId());

        employee.setFirstname(employeeDto.getFirstname());
        employee.setLastname(employeeDto.getLastname());

        if (employeeDto.getDepartment() != null) {
            employee.setDepartment(departmentDao.findByName(employeeDto.getDepartment())
                    .orElseThrow(() -> new DepartmentNotFoundException("Department not found")));
        }

        if (employeeDto.getJobTitles() != null) {
            List<JobTitle> jobTitles = new ArrayList<>();
            employeeDto.getJobTitles().forEach(j -> jobTitles.add(jobTitleDao.findByName(j)
                    .orElseThrow(() -> new JobTitleNotFoundException("Job title not found"))));
            employee.setJobTitles(jobTitles);

            Employee updatedEmployee = employeeDao.save(employee);

            jobTitles.forEach(j -> j.getEmployees().add(updatedEmployee));
            jobTitles.forEach(jobTitleDao::save);
            return updatedEmployee;
        }

        return employeeDao.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = getById(id);
        List<JobTitle> jobTitles = employee.getJobTitles();

        jobTitles.forEach(f -> f.getEmployees().remove(employee));

        employeeDao.delete(employee);
    }

    public Employee getById(Long employeeId) {
        return employeeDao.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    public List<Employee> getAll(Integer pageNumber) {
        return employeeDao.findAll(PageRequest.of(pageNumber, 5)).getContent();
    }
}
