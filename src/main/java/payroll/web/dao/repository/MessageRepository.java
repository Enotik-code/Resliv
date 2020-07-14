package payroll.web.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import payroll.web.bean.Message;

@Transactional
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Message getMessageByName(String name);
}
