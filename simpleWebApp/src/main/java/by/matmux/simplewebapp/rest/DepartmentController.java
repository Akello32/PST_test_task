package by.matmux.simplewebapp.rest;

import by.matmux.simplewebapp.dto.DepartmentDto;
import by.matmux.simplewebapp.entity.Department;
import by.matmux.simplewebapp.facade.DepartmentFacade;
import by.matmux.simplewebapp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@CrossOrigin
@RequestMapping("api/department")
public class DepartmentController {
    @Autowired
    private DepartmentFacade departmentFacade;
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<Object> createDepartment(@Valid @RequestBody DepartmentDto departmentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getModel(), HttpStatus.BAD_REQUEST);
        }

        Department department = departmentService.createDepartment(departmentDto);
        DepartmentDto createdDepartment = departmentFacade.departmentToDepartmentDto(department);

        return new ResponseEntity<>(createdDepartment, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateDepartment(@Valid @RequestBody DepartmentDto departmentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getModel(), HttpStatus.BAD_REQUEST);
        }

        Department department = departmentService.updateDepartment(departmentDto);
        DepartmentDto updatedDepartment = departmentFacade.departmentToDepartmentDto(department);

        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}/delete")
    public ResponseEntity<String> deleteDepartment(@PathVariable String departmentId) {
        departmentService.deleteDepartment(Long.parseLong(departmentId));

        return new ResponseEntity<>("Department was deleted", HttpStatus.OK);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable String departmentId) {
        DepartmentDto departmentDto = departmentFacade.departmentToDepartmentDto(
                departmentService.getById(Long.parseLong(departmentId)));
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }
}
