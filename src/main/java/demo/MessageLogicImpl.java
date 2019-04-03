package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageLogicImpl implements MessageLogic {
	private MessageDao messageDao;
	private LikeDao likeDao;
	
	@Autowired	
	public MessageLogicImpl(MessageDao messageDao, LikeDao likeDao) {
		this.messageDao = messageDao;
		this.likeDao = likeDao;
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Message> getMessageById(Long id) {
		return this.messageDao.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Message> getAllMessage(int page, int size) {
		List<Message> rv = new ArrayList<>();
		this.messageDao.findAll()
			.forEach(rv::add);
		
		// TODO replace this with actual pagination over the DB
		return rv
				.stream()
				.skip(page*size)
				.limit(size)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Message createMessage(Message message) {
		return this.messageDao.save(message);
	}

	@Override
	@Transactional
	public Like like(String user, Long msgId) {
		Message theMessage = this.messageDao.findById(msgId)
				.orElseThrow(()->new RuntimeException("no message to be liked"));
		Like theLike = this.likeDao.save(new Like(user));
		
		theMessage.addLike(theLike);
		this.messageDao.save(theMessage);

		return theLike;
	}

}
