package cz.uhk.todolist.controller;

import cz.uhk.todolist.model.*;
import cz.uhk.todolist.services.ProcessRepository;
import cz.uhk.todolist.services.ProjectRepository;
import cz.uhk.todolist.services.TaskRepository;
import cz.uhk.todolist.utils.ProcessStore;
import cz.uhk.todolist.utils.ProjectStore;
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

        //TODO v thymeleafu je potřeba ošetřit když je list s procesy prázdný, aby se nesnažili zobrazit
        if(project != null) {
            ProcessStore processStore = new ProcessStore(processRepository.findByParentId(project.getId()));
            System.out.println("processStore.getProcesses == null " + (processStore.getProcesses() == null));
            System.out.println(processStore.getProcesses().size());

            if(processStore != null && processStore.getProcesses().size() > 0)
            {
                System.out.println("processesSize: " + processStore.getProcesses().size());

                for(int i = 0; i < processStore.getProcesses().size(); i++)
                {
                    List<Task> tasks = taskRepository.findByParentId(processStore.getProcesses().get(i).getId());
                    processStore.getProcesses().get(i).addTasks(tasks);
                    processStore.getProcesses().get(i).calculateCriticalPath();
                }
                model.addObject(processStore);
            }
            else
            {
                System.out.println("Nemá procesy, nebo chybí projekt!");
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
