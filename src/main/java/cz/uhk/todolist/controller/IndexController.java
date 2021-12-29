package cz.uhk.todolist.controller;

import cz.uhk.todolist.model.Process;
import cz.uhk.todolist.model.Project;
import cz.uhk.todolist.model.Task;
import cz.uhk.todolist.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {
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
        ProjectService ps = new ProjectService();

        Project project = ps.getProjects().get(0);
        List<Process> processes = project.getProcesses();
        Process process = processes.get(0);

        System.out.println(process.getDescription());

        for (Task task: process.getTasks()) {
            System.out.println(task.getDescription() + " deadLine: "
                    + task.getDeadline() + " previousTask: "
                    + task.getPreviousTaskId() + " nextTask: "
                    + task.getNextTaskId() + " position= " + (task.getPreviousTaskId()+1));
        }
        model.addObject("process", process);
        model.addObject("project", project);


        return model;
    }

}
