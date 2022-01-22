package cz.uhk.todolist.services;

import cz.uhk.todolist.model.Process;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProcessRepository extends MongoRepository<Process, String> {
    public Process findByDescription(String description);
    //public Process findByParentId(String parentId);
    public List<Process> findByParentId(String parentId);
    public void deleteByParentId(String parentId);
}