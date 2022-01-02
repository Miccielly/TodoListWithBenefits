package cz.uhk.todolist.services;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProcessRepository extends MongoRepository<Process, String> {
    //public Process findByDescription(String description);
    //public List<Process> findAll();
}

