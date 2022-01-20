package cz.uhk.todolist.controller;

import cz.uhk.todolist.model.Project;
import cz.uhk.todolist.model.Process;
import cz.uhk.todolist.model.Task;
import cz.uhk.todolist.services.ProcessRepository;
import cz.uhk.todolist.services.ProjectRepository;
import cz.uhk.todolist.services.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CreateController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProcessRepository processRepository;

    @Autowired
    TaskRepository taskRepository;


    //VYTVÁŘENÍ PROJEKTŮ
    @GetMapping({"/createProject"})
    public String showCreateForm(Model model)
    {
        Project project = new Project();
        model.addAttribute("project", project);
        System.out.println("create form shown!");
        return "createProject";
    }

    @PostMapping("/createProject")
    public String createProject(@ModelAttribute Project project, Model model)
    {
//        System.out.println("CreateProjectDescription: " + project.getDescription());
//        System.out.println("CreateProjectDeadline: " + project.getDeadline());
        //TODO přidat validaci před nahráním do db
        projectRepository.save(project);
        return "redirect:/";
    }

    //VYTVÁŘENÍ PROCESŮ
    @GetMapping({"/createProcess/{parentId}"})
    public String showCreateProcessForm(Model model, @PathVariable String parentId)
    {
        Process process = new Process();
        Project project = projectRepository.findById(parentId).get();
        model.addAttribute("project", project);
        model.addAttribute("process", process);
        System.out.println("createProcessFormShown!");
        return "createProcess";
    }

    @PostMapping({"/createProcess/{parentId}"})
    public String createProcess(@ModelAttribute Process process, Model model, @PathVariable String parentId)
    {
        //System.out.println("CreateProcessParentId: " + process.getParentId());
        //System.out.println("CreateProcessDescription: " + process.getDescription());
        //System.out.println("CreateProcessDeadline: " + process.getDeadline());

        processRepository.save(process);
        return "redirect:/";
    }

    //VYTVÁŘENÍ TASKŮ
    @GetMapping({"/createTask/{parentId}"})
    public String showCreateTaskForm(Model model, @PathVariable String parentId)
    {
        Task task = new Task();
        Process process = processRepository.findById(parentId).get();



        model.addAttribute("process", process);
        model.addAttribute("task", task);

        System.out.println("parentId found: " + process.getId() );


        return "createTask";
    }

    @PostMapping({"/createTask/{parentId}"})
    public String createTask(@ModelAttribute Task task, Model model, @PathVariable String parentId)
    {
        System.out.println("CreateTaskPrevious: " + task.getPreviousTaskId());
        System.out.println("CreateTaskNext: " + task.getNextTaskId());
        System.out.println("CreateTaskParentId: " + task.getParentId());
        System.out.println("CreateTaskDescription: " + task.getDescription());
        System.out.println("CreateTaskDeadline: " + task.getDeadline());

        //taskRepository.save(task);
        return "redirect:/";
    }
}
