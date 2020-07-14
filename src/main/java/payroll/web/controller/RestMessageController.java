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

import org.springframework.web.servlet.ModelAndView;
import payroll.web.exception.MessageNotFoundException;
import payroll.web.dao.repository.MessageRepository;
import payroll.web.dao.service.MessageService;
import payroll.web.bean.Message;

@RestController
public class RestMessageController {

	@Autowired
	private final MessageRepository repository;

	@Autowired
	private MessageService messageService;

	RestMessageController(MessageRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/messages")
	public CollectionModel<EntityModel<Message>> all() {
		List<EntityModel<Message>> messeges = repository.findAll().stream()
				.map(message -> EntityModel.of(message,
						linkTo(methodOn(RestMessageController.class).one(message.getId())).withSelfRel(),
						linkTo(methodOn(RestMessageController.class).all()).withRel("messeges")))
				.collect(Collectors.toList());
		return CollectionModel.of(messeges, linkTo(methodOn(RestMessageController.class).all()).withSelfRel());
	}

	@PostMapping("/messages")
    public Message newMessage(@RequestBody Message newMessage) {
		return repository.save(newMessage);
	}

	@GetMapping("/messages/{id}")
	public EntityModel<Message> one(@PathVariable Long id) {
		Message message = repository.findById(id)
				.orElseThrow(() -> new MessageNotFoundException(id));
		return EntityModel.of(message,
				linkTo(methodOn(RestMessageController.class).one(id)).withSelfRel(),
				linkTo(methodOn(RestMessageController.class).all()).withRel("messagess"));
	}

	@PutMapping("/messages/{id}")
    public Message replaceMessage(@RequestBody Message message, @PathVariable Long id) {
		return repository.findById(id) //
				.map(mes -> {
					mes.setName(message.getName());
					mes.setDescription(message.getDescription());
					return repository.save(mes);
				})
				.orElseGet(() -> {
					message.setId(id);
					return repository.save(message);
				});
	}

	@DeleteMapping("/messagess/{id}")
	public void deleteMessage(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
