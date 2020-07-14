package payroll.web.bean;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message implements Serializable {

	private @Id @GeneratedValue Long id;
	private String name;
	private String description;

	Message() {}

	public Message(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof Message))
			return false;
		Message message = (Message) o;
		return Objects.equals(this.id, message.id) && Objects.equals(this.name, message.name)
				&& Objects.equals(this.description, message.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.description);
	}

	@Override
	public String toString() {
		return "Message{" + "id=" + this.id + ", name='" + this.name + '\'' + ", description='" + this.description + '\'' + '}';
	}

}
