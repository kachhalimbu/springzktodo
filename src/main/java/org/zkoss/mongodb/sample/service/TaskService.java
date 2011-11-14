/**
 * 
 */
package org.zkoss.mongodb.sample.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.mongodb.sample.model.Task;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @author Ashish
 *
 */
@Service("taskService")
@Transactional
public class TaskService {

	@Resource(name="mongoTemplate")
	private MongoTemplate mongoTemplate;
	
	public List<Task> findAll() {
		Query query = new Query(where("id").exists(true));
		List<Task> tasks = mongoTemplate.find(query, Task.class);
		return tasks;
	}
	
	public void add(Task task) {
		try {
			mongoTemplate.insert(task);
		} catch(Exception e) {}
	}
	public void update(Task task) {
		Query query = new Query(where("id").is(task.getId()));
		try {
			Update update = new Update();
			update.set("name", task.getName());
			update.set("priority", task.getPriority());
			update.set("executionDate", task.getExecutionDate());
			mongoTemplate.updateMulti(query, update, Task.class);
		} catch(Exception e) {}
	}
	public void delete(Task task) {
		try {
			Query query = new Query(where("id").is(task.getId()));
	         // Run the query and delete the entry
	         mongoTemplate.remove(query, Task.class);
		} catch(Exception e) {}
	}
}