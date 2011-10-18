/**
 * 
 */
package org.zkoss.mongodb.sample.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.data.document.mongodb.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.mongodb.sample.model.Task;
import static org.springframework.data.document.mongodb.query.Criteria.where;

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
		} catch(Exception e) {
			
		}
	}
	public void update(Task task) {
		Query query = new Query(where("id").is(task.getId()));
		try {
			Update update = new Update();
			update.set("name", task.getName());
			mongoTemplate.updateMulti(query, update);
			update.set("priority", task.getName());
			mongoTemplate.updateMulti(query, update);
		} catch(Exception e) {
			
		}
	}
	public void delete(Task task) {
		try {
			Query query = new Query(where("id").is(task.getId()));
	         // Run the query and delete the entry
	         mongoTemplate.remove(query);
		} catch(Exception e) {
			
		}
	}
}
