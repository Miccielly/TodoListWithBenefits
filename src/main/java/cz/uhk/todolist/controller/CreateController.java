package cz.uhk.todolist.controller;

import cz.uhk.todolist.model.Project;
import cz.uhk.todolist.model.Process;
import cz.uhk.todolist.model.Task;
import cz.uhk.todolist.services.ProcessRepository;
import cz.uhk.todolist.services.ProjectRepository;
import cz.uhk.todolist.services.TaskRepository;
import cz.uhk.todolist.utils.TaskStore;
import org.apache.tomcat.jni.Proc;
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
    public String createProjectForm(Model model)
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
    public String createProcessForm(Model model, @PathVariable String parentId)
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
    public String createTaskForm(Model model, @PathVariable String parentId)
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



//EDITACEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
    //EDITACE PROJEKTŮ
    @GetMapping({"editProject/{projectId}"})
    public String editProjectForm(Model model, @PathVariable String projectId)
    {
        Project project = projectRepository.findById(projectId).get();
        //System.out.println("before: " + project.getId() + " " + project.getDescription() + " " + project.getDeadline());
        model.addAttribute("project", project);
        return "editProject";
    }

    @PostMapping("/editProject/{projectId}")
    public String editProject(@ModelAttribute Project project, @PathVariable String projectId)
    {
        Project editedProject = projectRepository.findById(projectId).get();

        editedProject.setDescription(project.getDescription());
        editedProject.setDeadline(project.getDeadline());

        //System.out.println("after: " + newProject.getId() + " " + newProject.getDescription() + " " + newProject.getDeadline());
        projectRepository.save(editedProject);
        return "redirect:/";
    }

    //EDITACE PROCESU
    @GetMapping({"/editProcess/{processId}"})
    public String editProcessForm(Model model, @PathVariable String processId)
    {
        Process process = processRepository.findById(processId).get();
        model.addAttribute("process", process);
        return "editProcess";
    }

    @PostMapping({"/editProcess/{processId}"})
    public String editProcess(@ModelAttribute Process process, Model model, @PathVariable String processId)
    {
        Process editedProcess = processRepository.findById(processId).get();

        editedProcess.setDescription(process.getDescription());
        editedProcess.setDeadline(process.getDeadline());

        processRepository.save(editedProcess);    //uložení procesu
        return "redirect:/project/"+editedProcess.getParentId();
    }

    //EDITACE TASKŮ
    @GetMapping({"editTask/{taskId}"})
    public String editTaskForm(Model model, @PathVariable String taskId)
    {
        //TODO ZAMEZIT EDITACI TASKŮ END A START
        Task task = taskRepository.findById(taskId).get();

        TaskStore taskStore = new TaskStore(taskRepository.findByParentId(task.getParentId()));
        for(int i = 0; i < taskStore.getTasks().size(); i++)
        {
            if(taskStore.getTasks().get(i).getId().equals(task.getId()))
            {
                taskStore.getTasks().remove(i); //odstranění tasku, který upravujeme aby se nenapojil sám na sebe
            }
        }

        model.addAttribute("task", task);
        model.addAttribute("otherTasks", taskStore);

        return "editTask";
    }

    @PostMapping({"/editTask/{taskId}"})
    public String editTask(@ModelAttribute Task task, Model model, @PathVariable String taskId)
    {
        Task editedTask = taskRepository.findById(taskId).get();
        Process process = processRepository.findById(editedTask.getParentId()).get();


        editedTask.setDescription(task.getDescription());
        editedTask.setDeadline(task.getDeadline());
        editedTask.setPreviousTaskId(task.getPreviousTaskId());
        editedTask.setNextTaskId(task.getNextTaskId());

        taskRepository.save(editedTask);
        return "redirect:/project/"+process.getParentId();
    }

    //MAZÁNÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ
    //MAZÁNÍ PROJEKTŮ

    @GetMapping({"/deleteProject/{projectId}"})
    public String deleteProjectForm(Model model, @PathVariable String projectId)
    {
        Project project = projectRepository.findById(projectId).get();
        model.addAttribute("project", project);

        return "deleteProject";
    }

    @PostMapping({"/deleteProject/{projectId}"})
    public String deleteProject(@ModelAttribute Task task, Model model, @PathVariable String projectId)
    {
        List<Process> processes = processRepository.findByParentId(projectId);    //list procesů v projektu

        //projití procesů
        for(int i = 0; i < processes.size(); i ++)
        {
            taskRepository.deleteByParentId(processes.get(i).getId());    //smazaní tasků, v procesu
        }
        processRepository.deleteByParentId(projectId);  //smazání procesů

        projectRepository.deleteById(projectId);    //smazání projektu
        return "redirect:/";
    }

    //MAZÁNÍ PROCESŮ
    @GetMapping({"/deleteProcess/{processId}"})
    public String deleteProcessForm(Model model, @PathVariable String processId)
    {
        Process process = processRepository.findById(processId).get();
        model.addAttribute("process", process);

        return "deleteProcess";
    }

    @PostMapping({"/deleteProcess/{processId}"})
    public String deleteProcess(@ModelAttribute Task task, Model model, @PathVariable String processId)
    {
        Process process = processRepository.findById(processId).get();  //process

        String projectId = process.getParentId();

        taskRepository.deleteByParentId(process.getId());   //smazání všech tasků
        processRepository.deleteById(processId);    //smazání procesu
        return "redirect:/project/"+projectId;
    }

    //MAZÁNÍ TASKŮ
    @GetMapping({"/deleteTask/{taskId}"})
    public String deleteTaskForm(Model model, @PathVariable String taskId)
    {
        Task task = taskRepository.findById(taskId).get();
        Process process = processRepository.findById(task.getParentId()).get();

        model.addAttribute("process", process);
        model.addAttribute("task", task);

        return "deleteTask";
    }

    @PostMapping({"/deleteTask/{taskId}"})
    public String deleteTask(@ModelAttribute Task task, Model model, @PathVariable String taskId)
    {
        Task t = taskRepository.findById(taskId).get();
        Process process = processRepository.findById(t.getParentId()).get();
        String projectId = process.getParentId();

        taskRepository.deleteById(taskId);
        return "redirect:/project/"+projectId;
    }

}
