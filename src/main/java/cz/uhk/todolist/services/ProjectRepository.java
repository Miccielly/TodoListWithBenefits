package cz.uhk.todolist.services;

import cz.uhk.todolist.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectRepository extends MongoRepository<Project, String> {


}