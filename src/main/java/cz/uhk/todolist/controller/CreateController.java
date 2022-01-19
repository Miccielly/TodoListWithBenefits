package cz.uhk.todolist.controller;

import cz.uhk.todolist.model.Project;
import cz.uhk.todolist.services.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CreateController {

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping({"/create"})
    public String showCreateForm(Model model)
    {
        Project project = new Project();
        model.addAttribute("project", project);
        System.out.println("create form shown!");
        return "create";
    }

    @PostMapping("/create")
    public String createProject(@ModelAttribute Project project, Model model)
    {
        System.out.println("CreateProject: " + project.getDescription());
        //TODO přidat validaci před nahráním do db
        projectRepository.save(project);
        return "redirect:/";
    }
}
