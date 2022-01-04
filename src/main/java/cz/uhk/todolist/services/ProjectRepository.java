package cz.uhk.todolist.services;

import cz.uhk.todolist.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
    public Project findByDescription(String description);
}