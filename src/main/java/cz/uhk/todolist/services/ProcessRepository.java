package cz.uhk.todolist.services;

import cz.uhk.todolist.model.Process;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProcessRepository extends MongoRepository<Process, String> {
}