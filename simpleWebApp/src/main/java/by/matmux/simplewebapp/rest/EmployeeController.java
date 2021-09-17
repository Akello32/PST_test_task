package by.matmux.simplewebapp.rest;

import by.matmux.simplewebapp.dto.EmployeeDto;
import by.matmux.simplewebapp.entity.Employee;
import by.matmux.simplewebapp.facade.EmployeeFacade;
import by.matmux.simplewebapp.payload.request.EmployeeToSave;
import by.matmux.simplewebapp.service.EmployeeService;
import by.matmux.simplewebapp.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeFacade employeeFacade;
    @Autowired
    private ResponseErrorValidation errorValidation;

    @PostMapping("/create")
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody EmployeeToSave employeeToSave, BindingResult bindingResult) {
        ResponseEntity<Object> errors = errorValidation.mapValidationService(bindingResult);

        if (!ObjectUtils.isEmpty(errors)) {
            return errors;
        }

        Employee employee = employeeService.createEmployee(employeeToSave);
        EmployeeDto savedEmployee = employeeFacade.employeeToEmployeeDto(employee);

        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getModel(), HttpStatus.BAD_REQUEST);
        }

        Employee employee = employeeService.updateEmployee(employeeDto);
        EmployeeDto savedEmployee = employeeFacade.employeeToEmployeeDto(employee);

        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}/delete")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(Long.parseLong(employeeId));

        return new ResponseEntity<>("Employee was deleted", HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeProfile(@PathVariable String employeeId) {
        Employee employee = employeeService.getById(Long.parseLong(employeeId));
        EmployeeDto employeeDto = employeeFacade.employeeToEmployeeDto(employee);

        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @GetMapping("/{pageNumber}/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@PathVariable String pageNumber) {
        List<EmployeeDto> employees = employeeService.getAll(Integer.parseInt(pageNumber))
                .stream()
                .map(employeeFacade::employeeToEmployeeDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
