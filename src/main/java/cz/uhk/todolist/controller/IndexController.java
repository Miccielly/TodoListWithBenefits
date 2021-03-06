package cz.uhk.todolist.controller;

import cz.uhk.todolist.model.Process;
import cz.uhk.todolist.model.Project;
import cz.uhk.todolist.model.User;
import cz.uhk.todolist.services.ProcessRepository;
import cz.uhk.todolist.services.TaskRepository;
import cz.uhk.todolist.services.UserRepository;
import cz.uhk.todolist.utils.ProjectStore;
import cz.uhk.todolist.services.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProcessRepository processRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    ProjectStore projectStore;

    @GetMapping({"/"})
    public ModelAndView showMainpage () {
        ModelAndView model = new ModelAndView("index");
        List<Project> projects = projectRepository.findAll();
//
//        //TODO získat informaci o nalogovaném uživateli a zobrazit jeho projekty
//        User user = userRepository.findByUsername("pavel");
//
          //zorazení projektů, které jsou přiřazeny uživateli
//        List<Project> projects = new ArrayList<>();

//        for(int i = 0; i < user.getProjectIds().size(); i++)
//        {
//            projects.add(projectRepository.findById(user.getProjectIds().get(i)).get());
//        }

        for (int i = 0; i < projects.size(); i++)
        {
            List<Process> processes = processRepository.findByParentId(projects.get(i).getId());

            projects.get(i).setProcesses(processes);
            projects.get(i).calculateElapsedTime();
//            System.out.println("elapsedTime: " + projects.get(i).getElapsedTime());
            projectRepository.save(projects.get(i));    //uložení elapsedTime do databáze
        }

            projectStore = new ProjectStore(projects);

        model.addObject(projectStore);
        return model;
    }

    @GetMapping({"/login"})
    public String showLogin()
    {
        return "login";
    }

    @GetMapping({"/test"})
    public String test()
    {
        return "test";
    }

}
