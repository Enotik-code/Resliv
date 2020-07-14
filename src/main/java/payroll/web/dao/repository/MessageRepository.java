package payroll.web.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import payroll.web.bean.Message;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Message getMessageByName(String name);
}
