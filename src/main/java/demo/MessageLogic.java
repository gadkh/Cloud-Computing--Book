package demo;

import java.util.List;
import java.util.Optional;

public interface MessageLogic {

	public Optional<Message> getMessageById(Long id);

	public List<Message> getAllMessage(int page, int size);

	public Message createMessage(Message message);

	public Like like(String user, Long msgId);

}
