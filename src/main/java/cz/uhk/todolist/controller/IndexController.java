package cz.uhk.todolist.controller;

import cz.uhk.todolist.model.Project;
import cz.uhk.todolist.model.ProjectStore;
import cz.uhk.todolist.services.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    ProjectRepository projectRepository;

    ProjectStore projectStore;

    @GetMapping({"/"})
    public ModelAndView Hello ()
    {
        ModelAndView model = new ModelAndView("index");
        List<Project> projects = projectRepository.findAll();
        projectStore = new ProjectStore(projects);
        System.out.println("projectsSize: " + projects.size());

        model.addObject(projectStore);
        return model;
    }
}
