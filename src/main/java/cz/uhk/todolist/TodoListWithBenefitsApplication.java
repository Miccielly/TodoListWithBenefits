package cz.uhk.todolist;

import cz.uhk.todolist.model.Project;
import cz.uhk.todolist.services.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class TodoListWithBenefitsApplication implements CommandLineRunner {

    @Autowired
    private ProjectRepository projectRepository;

    public static void main(String[] args) {
        SpringApplication.run(TodoListWithBenefitsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        // fetch all customers
        System.out.println("Projects found with findAll():");
        System.out.println("-------------------------------");
        //for (Project project : projectRepository.findAll()) {
          //  System.out.println(project);
        //}
        System.out.println();

        // fetch an individual customer
        System.out.println("Project found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        //System.out.println(repository.findByDescription("Projekt02"));
        Optional<Project> project = projectRepository.findById("61cf3447d9859f15988b5da6");
        System.out.println("FindByID: " + project);
        System.out.println("FindAll: " + projectRepository.findAll());
        projectRepository.save(new Project("ProjectTest", 35f));

    }
}
