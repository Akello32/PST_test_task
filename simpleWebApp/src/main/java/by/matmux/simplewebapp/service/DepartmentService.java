package by.matmux.simplewebapp.service;

import by.matmux.simplewebapp.dao.DepartmentDao;
import by.matmux.simplewebapp.dto.DepartmentDto;
import by.matmux.simplewebapp.entity.Department;
import by.matmux.simplewebapp.exception.DepartmentNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    private final DepartmentDao departmentDao;

    public DepartmentService(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public Department createDepartment(DepartmentDto departmentDto) {
        Department department = new Department();

        department.setName(departmentDto.getName());
        return departmentDao.save(department);
    }

    public Department updateDepartment(DepartmentDto departmentDto) {
        Department department = departmentDao.getById(departmentDto.getId());

        department.setName(departmentDto.getName());
        return departmentDao.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentDao.delete(getById(id));
    }

    public Department getById(Long departmentId) {
        return departmentDao.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
    }
}

