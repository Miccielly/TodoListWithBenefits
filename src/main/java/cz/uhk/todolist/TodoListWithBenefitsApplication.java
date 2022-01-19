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


    }

    private void ShowTest()
    {
        Task[] tasks = new Task[7];
        tasks = taskRepository.findAll().toArray(new Task[0]);

        Process process = processRepository.findById(tasks[0].getParentId()).get();
        process.addTasks(tasks);

        Project project = projectRepository.findById(process.getParentId()).get();
        project.addProcess(process);

        process.calculateCriticalPath();
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

    private void addProcess()
    {
        Project project = projectRepository.findByDescription("Day");
        Process lunch = new Process("Polévka", 60, project.getId());
        processRepository.save(lunch);

        Process p = processRepository.findByDescription(lunch.getDescription());

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Nakrájení zeleniny", 10, p.getId()));
        tasks.add(new Task("Vaření zeleniny", 20, p.getId()));
        tasks.add(new Task("Kořenění", 1, p.getId()));
        tasks.add(new Task("Jedení", 15, p.getId()));

        for (Task t:tasks) {
            taskRepository.save(t);
        }
    }

}
