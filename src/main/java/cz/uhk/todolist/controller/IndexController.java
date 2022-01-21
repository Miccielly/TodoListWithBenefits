package cz.uhk.todolist.controller;

import cz.uhk.todolist.model.Process;
import cz.uhk.todolist.model.Project;
import cz.uhk.todolist.services.ProcessRepository;
import cz.uhk.todolist.services.TaskRepository;
import cz.uhk.todolist.utils.ProjectStore;
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

    @Autowired
    ProcessRepository processRepository;

    @Autowired
    TaskRepository taskRepository;

    ProjectStore projectStore;

    @GetMapping({"/"})
    public ModelAndView showMainpage () {
        ModelAndView model = new ModelAndView("index");
        List<Project> projects = projectRepository.findAll();

        for (int i = 0; i < projects.size(); i++)
        {
            List<Process> processes = processRepository.findByParentId(projects.get(i).getId());

            projects.get(i).setProcesses(processes);
        }



            projectStore = new ProjectStore(projects);


        model.addObject(projectStore);
        return model;
    }

}
