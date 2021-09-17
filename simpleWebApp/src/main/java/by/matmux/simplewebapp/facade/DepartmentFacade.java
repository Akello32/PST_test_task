package by.matmux.simplewebapp.facade;

import by.matmux.simplewebapp.dto.DepartmentDto;
import by.matmux.simplewebapp.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentFacade {
    public DepartmentDto departmentToDepartmentDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();

        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());

        return departmentDto;
    }
}
