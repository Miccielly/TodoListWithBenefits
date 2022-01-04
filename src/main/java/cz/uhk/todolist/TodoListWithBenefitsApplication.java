package cz.uhk.todolist;

import cz.uhk.todolist.services.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoListWithBenefitsApplication implements CommandLineRunner {

    @Autowired
    private ProjectRepository repository;

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
        //System.out.println(projectRepository.findByDescription("Projekt02"));
    }
}
