package cz.uhk.todolist.controller;

import cz.uhk.todolist.model.Process;
import cz.uhk.todolist.model.Project;
import cz.uhk.todolist.model.ProjectBank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ProjectController {

    private ProjectBank projectBank;


    @GetMapping({"/"})
    public String Hello(@RequestParam(value = "name",defaultValue = "World", required = true) String name, Model model)
    {
        model.addAttribute("name", name);
        return "index";
    }

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


}
