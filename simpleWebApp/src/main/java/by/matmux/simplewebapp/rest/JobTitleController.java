package by.matmux.simplewebapp.rest;

import by.matmux.simplewebapp.dto.JobTitleDto;
import by.matmux.simplewebapp.facade.JobTitleFacade;
import by.matmux.simplewebapp.service.JobTitleService;
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
@RequestMapping("api/jobTitle")
public class JobTitleController {
    @Autowired
    private JobTitleFacade jobTitleFacade;
    @Autowired
    private JobTitleService jobTitleService;

    @PostMapping("/create")
    public ResponseEntity<Object> createJobTitle(@Valid @RequestBody JobTitleDto jobTitleDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getModel(), HttpStatus.BAD_REQUEST);
        }

        JobTitleDto createdJobTitle =
                jobTitleFacade.departmentToDepartmentDto(jobTitleService.createJobTitle(jobTitleDto));
        return new ResponseEntity<>(createdJobTitle, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateJobTitle(@Valid @RequestBody JobTitleDto jobTitleDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getModel(), HttpStatus.BAD_REQUEST);
        }

        JobTitleDto updatedJobTitle =
                jobTitleFacade.departmentToDepartmentDto(jobTitleService.updateJobTitle(jobTitleDto));
        return new ResponseEntity<>(updatedJobTitle, HttpStatus.OK);
    }

    @DeleteMapping("/{jobTitleId}/delete")
    public ResponseEntity<String> deleteJobTitle(@PathVariable String jobTitleId) {
        jobTitleService.deleteJobTitle(Long.parseLong(jobTitleId));

        return new ResponseEntity<>("Job title was deleted", HttpStatus.OK);
    }

    @GetMapping("/{jobTitleId}")
    public ResponseEntity<JobTitleDto> getJobTitleById(@PathVariable String jobTitleId) {
        JobTitleDto jobTitleDto =
                jobTitleFacade.departmentToDepartmentDto(jobTitleService.getById(Long.parseLong(jobTitleId)));
        return new ResponseEntity<>(jobTitleDto, HttpStatus.OK);
    }
}

