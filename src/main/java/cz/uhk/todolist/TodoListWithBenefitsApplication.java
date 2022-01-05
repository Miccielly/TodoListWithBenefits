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
        Optional<Task> task = Optional.ofNullable(taskRepository.findByProcessId("61cf3498d9859f15988b5da7"));
        System.out.println(task.get().getDescription());

        Optional<Process> process = processRepository.findById("61cf3498d9859f15988b5da7");
        //Project pr = project.get();
        System.out.println("Process: " + process.get().getDescription());
        //System.out.println("FindByProcessId: " + taskRepository.findByProcessId("61cf3498d9859f15988b5da7"));


    }

    private void SaveTest()
    {

        List<Process> processes = new ArrayList<>();

        Process process1 = new Process("Process1", 5);
        Process process2 = new Process("Process2", 7);

        processes.add(process1);
        processes.add(process2);

        Project p = new Project("Projekt03", processes, 15f);

        //projectRepository.save(p);

    }
}
