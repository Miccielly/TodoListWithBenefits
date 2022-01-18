package cz.uhk.todolist.controller;

import cz.uhk.todolist.model.ProjectStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;


@Controller
public class ProjectController {

    private ProjectStore projectBank;

    @GetMapping({"/project/{id}"})
    private ModelAndView showProject(@PathVariable String id)
    {
        ModelAndView model = new ModelAndView("test");
        System.out.println("project id: " + id);
        return  model;
    }

/*
    @GetMapping({"/process.do"})
    public ModelAndView zobraz()
    {
        ModelAndView model = new ModelAndView("project");
        projectBank = new ProjectBank();

        if(projectBank.getProjects().size() > 0) {
            Project project = projectBank.getProjects().get(0);
            List<Process> processes = project.getProcesses();
            Process process = processes.get(0);

            model.addObject("process", process);
            model.addObject("project", project);
        }
        else
        System.out.println("Žádné projekty na vykreslení!");
        return model;
    }
*/

}
