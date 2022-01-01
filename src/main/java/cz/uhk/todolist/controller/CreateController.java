package cz.uhk.todolist.controller;

import cz.uhk.todolist.model.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CreateController {

    @GetMapping({"/create.do"})
    public String createForm(Model model)
    {
        System.out.println("create.do shown!");
        return "create";
    }

    @PostMapping("/createProject")
    public String createProject(@ModelAttribute Project project, Model model)
    {
        model.addAttribute("project", project);
        System.out.println("CreateProject!");
        return "redirect:process.do";
    }
}
