package cz.uhk.todolist;

import cz.uhk.todolist.model.Project;
import cz.uhk.todolist.model.Process;
import cz.uhk.todolist.model.Task;
import cz.uhk.todolist.services.ProcessRepository;
import cz.uhk.todolist.services.ProjectRepository;
import cz.uhk.todolist.services.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class TodoListWithBenefitsApplication implements CommandLineRunner {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProcessRepository processRepository;

    @Autowired
    private TaskRepository taskRepository;

    public static void main(String[] args) {
        SpringApplication.run(TodoListWithBenefitsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        SaveTest();

    }

    private void SaveTest()
    {
        Project p = new Project("Day",1440f);
        projectRepository.save(p);

        Optional<Project> parentProject = Optional.ofNullable(projectRepository.findByDescription("Day"));
        Process process = new Process("Morning Routine", 400f, parentProject.get().getId() );
        processRepository.save(process);

/*
        Task task0 = new Task("Spát", 360);
        Task task1 = new Task("Vstát", 30);
        Task process3 = new Task("Instagram", 15);
        Task process4 = new Task("Záchod", 7);
        Task process5 = new Task("Koš", 5);
*/

    }
}
