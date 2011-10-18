/**
 * 
 */
package org.zkoss.mongodb.sample.controller;

import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.zkoss.mongodb.sample.model.Task;
import org.zkoss.mongodb.sample.service.TaskService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;

/**
 * @author Ashish
 *
 */
@org.springframework.stereotype.Component("todoCtrl")
@Scope("prototype")
public class SpringTODOController extends GenericForwardComposer {

	Listbox tasks;
	Textbox name;
	Intbox priority;
	Datebox date;
	
	@Resource(name="taskService")
	private TaskService taskService;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		tasks.setModel(new ListModelList(taskService.findAll()));
		tasks.setItemRenderer(new ListitemRenderer() {
			public void render(Listitem item, Object data) throws Exception {
				Task task = (Task) data;
				item.setValue(task);
				new Listcell(task.getName()).setParent(item); 
				new Listcell("" + task.getPriority()).setParent(item); 
				new Listcell(new SimpleDateFormat("yyyy-MM-dd").format(task.getExecutionDate())).setParent(item);
			}
		});
		
	}
	public void onSelect$tasks(SelectEvent evt) {
		Task task = (Task) tasks.getSelectedItem().getValue();
		name.setValue(task.getName());
		priority.setValue(task.getPriority());
		date.setValue(task.getExecutionDate());
	}

	
	public void onClick$add(Event evt) {
		Task newTask = new Task(UUID.randomUUID().toString(), name.getValue(), priority.getValue(), date.getValue());
		taskService.add(newTask);
		tasks.setModel(new ListModelList(taskService.findAll()));
	}
	
	public void onClick$update() {
		Task task = (Task) tasks.getSelectedItem().getValue();
		task.setName(name.getValue());
		task.setPriority(priority.getValue());
		task.setExecutionDate(date.getValue());
		taskService.update(task);
		tasks.setModel(new ListModelList(taskService.findAll()));
	}
	
	public void onClick$delete() {
		Task task = (Task) tasks.getSelectedItem().getValue();
		taskService.delete(task);
		tasks.setModel(new ListModelList(taskService.findAll()));
	}	
}
