package cz.uhk.todolist.controller;

import cz.uhk.todolist.model.Project;
import cz.uhk.todolist.model.Process;
import cz.uhk.todolist.services.ProcessRepository;
import cz.uhk.todolist.services.ProjectRepository;
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
        //model.addAttribute("project", project);
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
}
