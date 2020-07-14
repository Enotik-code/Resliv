package payroll.web.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import payroll.web.exception.MessageNotFoundException;
import payroll.web.dao.repository.MessageRepository;
import payroll.web.dao.service.MessageService;
import payroll.web.bean.Message;

@RestController
public class MessageController {

	@Autowired
	private final MessageRepository repository;

	@Autowired
	private MessageService messageService;

	MessageController(MessageRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/messages")
	CollectionModel<EntityModel<Message>> all() {
		List<EntityModel<Message>> messeges = repository.findAll().stream()
				.map(message -> EntityModel.of(message,
						linkTo(methodOn(MessageController.class).one(message.getId())).withSelfRel(),
						linkTo(methodOn(MessageController.class).all()).withRel("messeges")))
				.collect(Collectors.toList());
		return CollectionModel.of(messeges, linkTo(methodOn(MessageController.class).all()).withSelfRel());
	}

	@PostMapping("/messages")
    Message newEmployee(@RequestBody Message newMessage) {
		return repository.save(newMessage);
	}

	@GetMapping("/messages/{id}")
	EntityModel<Message> one(@PathVariable Long id) {
		Message message = repository.findById(id)
				.orElseThrow(() -> new MessageNotFoundException(id));
		return EntityModel.of(message,
				linkTo(methodOn(MessageController.class).one(id)).withSelfRel(),
				linkTo(methodOn(MessageController.class).all()).withRel("messagess"));
	}

	@PutMapping("/messages/{id}")
    Message replaceEmployee(@RequestBody Message newMessage, @PathVariable Long id) {
		return repository.findById(id) //
				.map(message -> {
					message.setName(newMessage.getName());
					message.setDescription(newMessage.getDescription());
					return repository.save(message);
				})
				.orElseGet(() -> {
					newMessage.setId(id);
					return repository.save(newMessage);
				});
	}

	@DeleteMapping("/messagess/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@GetMapping
	public String allTourismMessages(){
		return messageService.list().toString();
	}

}
