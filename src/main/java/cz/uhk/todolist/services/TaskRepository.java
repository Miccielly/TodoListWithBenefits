package cz.uhk.todolist.services;

import cz.uhk.todolist.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    public List<Task> findByParentId(String parentId);
    public void deleteByParentId(String parentId);
}
