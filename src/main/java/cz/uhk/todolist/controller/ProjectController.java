package cz.uhk.todolist.controller;

import cz.uhk.todolist.model.Project;
import cz.uhk.todolist.model.Process;
import cz.uhk.todolist.model.ProjectStore;
import cz.uhk.todolist.model.Task;
import cz.uhk.todolist.services.ProcessRepository;
import cz.uhk.todolist.services.ProjectRepository;
import cz.uhk.todolist.services.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProcessRepository processRepository;
    @Autowired
    private TaskRepository taskRepository;

    private ProjectStore projectStore;
    @GetMapping({"/project/{id}"})
    private ModelAndView showProject(@PathVariable String id)
    {
        ModelAndView model = new ModelAndView("project");

        Project project = projectRepository.findById(id).get();
        System.out.println("project id: " + id);

        if(project != null) {
            Process process = processRepository.findByParentId(project.getId());

            if(process != null)
            {
                List<Task> tasks = taskRepository.findByParentId(process.getId());
                process.addTasks(tasks);
                process.calculateCriticalPath();
                model.addObject(process);
            }

            model.addObject(project);

        }


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
