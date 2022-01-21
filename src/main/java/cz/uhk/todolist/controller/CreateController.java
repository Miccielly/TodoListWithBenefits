package cz.uhk.todolist.controller;

import cz.uhk.todolist.model.Project;
import cz.uhk.todolist.model.Process;
import cz.uhk.todolist.model.Task;
import cz.uhk.todolist.services.ProcessRepository;
import cz.uhk.todolist.services.ProjectRepository;
import cz.uhk.todolist.services.TaskRepository;
import cz.uhk.todolist.utils.TaskStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        //TODO ošetřit prázdné/nesmyslné inputy

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

        //TODO ošetřit prázdné/nesmyslné inputy
        processRepository.save(process);    //uložení procesu
        //TODO ošetřit aby jméno procesu bylo unikátní pro daný projekt
        Process p = processRepository.findByDescription(process.getDescription());  //získání procesu který jsme teď vytvořili (pro získání id z databáze)
        Task start = new Task(true, p.getId()); //vytvoření start úlohy
        Task end = new Task(false, p.getId());  //vytvoření end úlohy

        //uložení úloh do databáze
        taskRepository.save(start);
        taskRepository.save(end);


        return "redirect:/project/"+parentId;
    }

    //VYTVÁŘENÍ TASKŮ
    @GetMapping({"/createTask/{parentId}"})
    public String showCreateTaskForm(Model model, @PathVariable String parentId)
    {
        Task task = new Task();
        Process process = processRepository.findById(parentId).get();
        TaskStore taskStore = new TaskStore(taskRepository.findByParentId(process.getId()));


        model.addAttribute("process", process);
        model.addAttribute("task", task);
        model.addAttribute("otherTasks", taskStore);    //seznam tásků patřících do procesu (pro napojení previous a next tasků)
        System.out.println("parentId found: " + process.getId() );


        return "createTask";
    }

    @PostMapping({"/createTask/{parentId}"})
    public String createTask(@ModelAttribute Task task, Model model, @PathVariable String parentId)
    {
            Process process = processRepository.findById(parentId).get();
            String projectId = process.getParentId();
//TODO ošetřit prázdné/nesmyslné inputy

//        System.out.println("ProjectId: " + projectId);
//        System.out.println("CreateTaskPrevious: " + task.getPreviousTaskId());
//        System.out.println("CreateTaskNext: " + task.getNextTaskId());
//        System.out.println("CreateTaskParentId: " + task.getParentId());
//        System.out.println("CreateTaskDescription: " + task.getDescription());
//        System.out.println("CreateTaskDeadline: " + task.getDeadline());

        taskRepository.save(task);
        return "redirect:/project/"+projectId;
    }

    //EDITACE TASKŮ
    @GetMapping({"editProcess/{taskId}"})
    public String editTask(@ModelAttribute Task task, Model model, @PathVariable String taskId)
    {
        //stejné jako model.addAttribute? když máme @ModelAttribute v parametrech?
        task = taskRepository.findById(taskId).get();
        Process process = processRepository.findById(task.getParentId()).get();

        model.addAttribute("process", process);
        return "edit"; ?????????
    }
}
