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
import org.springframework.scheduling.TaskScheduler;

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
    public void run(String... args) throws Exception {

        ShowTest();
    }

    private void ShowTest()
    {
        Task[] tasks = new Task[7];
        tasks = taskRepository.findAll().toArray(new Task[0]);

        Process process = processRepository.findById(tasks[0].getParentId()).get();
        process.addTasks(tasks);
        process.sortTasks();
        //process.calculateCriticalPath();

    }
    private void SaveTest() {

        Project p = new Project("Day", 1440f);
        projectRepository.save(p);

        Optional<Project> parentProject = Optional.ofNullable(projectRepository.findByDescription("Day"));
        Process process = new Process("Morning Routine", 400f, parentProject.get().getId());
        processRepository.save(process);

        String processId = processRepository.findByDescription(process.getDescription()).getId();

        Task[] tasks = new Task[7];

        tasks[0] = new Task(true, processId);
        tasks[1] = new Task("Spát", 360, processId);
        tasks[2] = new Task("Vstát", 30, processId);
        tasks[3] = new Task("Instagram", 15, processId);
        tasks[4] = new Task("Záchod", 7, processId);
        tasks[5] = new Task("Koš", 5, processId);
        tasks[6] = new Task(false, processId);

        for (int i = 0; i < tasks.length; i++) {
            taskRepository.save(tasks[i]);
        }
    }


}
