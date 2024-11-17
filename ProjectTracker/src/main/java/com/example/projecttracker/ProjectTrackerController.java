package com.example.projecttracker;

import com.example.projecttracker.ApiResponse.ApiResponse;
import com.example.projecttracker.Model.ProjectTracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project-tracker")
public class ProjectTrackerController {

    ArrayList<ProjectTracker> projectTrackers = new ArrayList<>();

    @PostMapping("/add")
    public ApiResponse addProject(@RequestBody ProjectTracker projectTracker) {
        projectTrackers.add(projectTracker);
        return new ApiResponse("Successfully added project tracker");
    }

    //• Create a new project (ID,title , description , status, companyName)
    @GetMapping("/get")
    public ArrayList getProject() {
        return projectTrackers;
    }
    //• Display all project .
    @PutMapping("/update/{index}")
    public ApiResponse updateProject(@PathVariable int index, @RequestBody ProjectTracker projectTracker) {
        projectTrackers.set(index, projectTracker);
        return new ApiResponse("Successfully updated project tracker");
    }
    //• Update a project
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteProject(@PathVariable int index) {
        projectTrackers.remove(index);
        return new ApiResponse("Successfully deleted project tracker");
    }
    //• Delete a project
    @PutMapping("/update-status/{index}")
    public ApiResponse updateProjectStatus(@PathVariable int index) {
        for (ProjectTracker projectTracker : projectTrackers) {
            if (projectTracker.getStatus().toLowerCase().contains("not done")){projectTracker.setStatus("done");}
        }
        return new ApiResponse("Successfully updated status project tracker");
    }
    //• Change the project status as done or not done
    @GetMapping("/search/{title}")
    public ArrayList searchProject(@PathVariable String title) {
        ArrayList<ProjectTracker> filteredProjectTrackers = new ArrayList<>();
        for (ProjectTracker projectTracker : projectTrackers) {
            if (projectTracker.getTitle().toLowerCase().contains(title.toLowerCase())) {filteredProjectTrackers.add(projectTracker);}
        }
        return filteredProjectTrackers;
    }
    //• Search for a project by given title
    @GetMapping("/get-allprojects/{companyName}")
    public ArrayList getAllProjects(@PathVariable String companyName) {
        ArrayList<ProjectTracker> filteredProjectTrackers = new ArrayList<>();
        for (ProjectTracker projectTracker : projectTrackers) {
            if (projectTracker.getCompanyName().toLowerCase().contains(companyName.toLowerCase())) {filteredProjectTrackers.add(projectTracker);}
        }
        return filteredProjectTrackers;
    }
    //• Display All project for one company by companyName.
}
