package cz.uhk.todolist.services;

import cz.uhk.todolist.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
    public Task findByParentId(String parentId);
}
