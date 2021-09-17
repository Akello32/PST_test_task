package by.matmux.simplewebapp.service;

import by.matmux.simplewebapp.dao.DepartmentDao;
import by.matmux.simplewebapp.dao.TechnicalDepartmentsDao;
import by.matmux.simplewebapp.dto.TechnicalDepartmentsDto;
import by.matmux.simplewebapp.entity.TechnicalDepartments;
import by.matmux.simplewebapp.exception.DepartmentNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TechnicalDepartmentsService {
    private final TechnicalDepartmentsDao technicalDepartmentsDao;
    private final DepartmentDao departmentDao;

    public TechnicalDepartmentsService(TechnicalDepartmentsDao technicalDepartmentsDao, DepartmentDao departmentDao) {
        this.technicalDepartmentsDao = technicalDepartmentsDao;
        this.departmentDao = departmentDao;
    }

    public TechnicalDepartments addToTechnicalDepartments(TechnicalDepartmentsDto techDepartmentsDto) {
        TechnicalDepartments technicalDepartments = new TechnicalDepartments();

        technicalDepartments.setDepartment(departmentDao.findByName(techDepartmentsDto.getDepartmentName())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found")));
        return technicalDepartmentsDao.save(technicalDepartments);
    }
}
