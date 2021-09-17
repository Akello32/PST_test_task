package by.matmux.simplewebapp.service;

import by.matmux.simplewebapp.dao.JobTitleDao;
import by.matmux.simplewebapp.dto.JobTitleDto;
import by.matmux.simplewebapp.entity.JobTitle;
import by.matmux.simplewebapp.exception.JobTitleNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JobTitleService {
    private final JobTitleDao jobTitleDao;

    public JobTitleService(JobTitleDao jobTitleDao) {
        this.jobTitleDao = jobTitleDao;
    }

    public JobTitle createJobTitle(JobTitleDto jobTitleDto) {
        JobTitle jobTitle = new JobTitle();

        jobTitle.setName(jobTitleDto.getName());
        return jobTitleDao.save(jobTitle);
    }

    public JobTitle updateJobTitle(JobTitleDto jobTitleDto) {
        JobTitle jobTitle = jobTitleDao.findById(jobTitleDto.getId())
                .orElseThrow(() -> new JobTitleNotFoundException("Job title not found"));

        jobTitle.setName(jobTitleDto.getName());
        return jobTitleDao.save(jobTitle);
    }

    public void deleteJobTitle(Long id) {
        jobTitleDao.delete(getById(id));
    }

    public JobTitle getById(Long id) {
        return jobTitleDao.findById(id)
                .orElseThrow(() -> new JobTitleNotFoundException("Job title not found"));
    }
}
