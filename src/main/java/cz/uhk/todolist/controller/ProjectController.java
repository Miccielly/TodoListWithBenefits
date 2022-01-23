package cz.uhk.todolist.controller;

import cz.uhk.todolist.model.*;
import cz.uhk.todolist.model.Process;
import cz.uhk.todolist.services.ProcessRepository;
import cz.uhk.todolist.services.ProjectRepository;
import cz.uhk.todolist.services.TaskRepository;
import cz.uhk.todolist.utils.ProcessStore;
import cz.uhk.todolist.utils.ProjectStore;
import cz.uhk.todolist.utils.TimeStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    private ModelAndView showProject(@PathVariable String id) {
        ModelAndView model = new ModelAndView("project");


        Project project = projectRepository.findById(id).get();
        //System.out.println("project id: " + id);

        if (project != null) {
            ProcessStore processStore = new ProcessStore(processRepository.findByParentId(project.getId()));
            //System.out.println("processStore.getProcesses == null " + (processStore.getProcesses() == null));
            //System.out.println(processStore.getProcesses().size());

            if (processStore != null && processStore.getProcesses().size() > 0) {
                //System.out.println("processesSize: " + processStore.getProcesses().size());

                for (int i = 0; i < processStore.getProcesses().size(); i++) {
                    List<Task> tasks = taskRepository.findByParentId(processStore.getProcesses().get(i).getId());
                    processStore.getProcesses().get(i).addTasks(tasks);
                    processStore.getProcesses().get(i).calculateCriticalPath();
                    processRepository.save(processStore.getProcesses().get(i)); //aktualizuje v databázi hodnoty cost, deadlinesum atd...
                }
                TimeStore timeStore = new TimeStore();  //objekt na uložení výsledku časovače
                timeStore.setProcessStore(processStore);    //přidání seznamu procesů pro časovač

                model.addObject("timeStore", timeStore);
                model.addObject(processStore);


            } else {
                System.out.println("Nemá procesy, nebo chybí projekt!");
            }
            model.addObject(project);
        }


        return model;
    }

    @PostMapping({"/saveTime"})
    public String saveTimer(@ModelAttribute TimeStore timeStore) {
        Process process = processRepository.findById(timeStore.getSelectedProcessId()).get();

        float timerTime = timeStore.getMinutes() + (timeStore.getSeconds() * .01f);

        process.setElapsedTime( process.getElapsedTime() + timerTime);

        processRepository.save(process);
        return "redirect:/";
    }
}
