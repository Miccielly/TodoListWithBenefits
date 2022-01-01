package cz.uhk.todolist;

import cz.uhk.todolist.model.Process;
import cz.uhk.todolist.model.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class TodoListWithBenefitsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoListWithBenefitsApplication.class, args);
    }

}
