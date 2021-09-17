package by.matmux.simplewebapp.facade;

import by.matmux.simplewebapp.dto.JobTitleDto;
import by.matmux.simplewebapp.entity.JobTitle;
import org.springframework.stereotype.Component;

@Component
public class JobTitleFacade {
    public JobTitleDto departmentToDepartmentDto(JobTitle jobTitle) {
        JobTitleDto jobTitleDto = new JobTitleDto();

        jobTitleDto.setId(jobTitle.getId());
        jobTitleDto.setName(jobTitle.getName());

        return jobTitleDto;
    }
}
