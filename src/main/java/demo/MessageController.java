package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
	private MessageLogic messageLogic;
	
	@Autowired
	public void setMessageLogic(MessageLogic messageLogic) {
		this.messageLogic = messageLogic;
	}
	
	@RequestMapping(
		path="/message/{id}",
		method=RequestMethod.GET,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public Message getMessageById(
			@PathVariable("id") Long id){
		return this.messageLogic.getMessageById(id)
				.orElseThrow(()->new RuntimeException("message not found by id: " + id));
	}
	
	@RequestMapping(
			path="/message",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Message[] getAllMessage(
			@RequestParam(name="page", required=false, defaultValue="0") int page, 
			@RequestParam(name="size", required=false, defaultValue="10") int size){
		return this.messageLogic.getAllMessage(page, size)
				.toArray(new Message[0]);
	}

	@RequestMapping(
			path="/message",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public Message createMessage(
			@RequestBody Message message) {
		return this.messageLogic.createMessage(message);
	}

	@RequestMapping(
			path="/like/{user}/{msgId}",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Like like(
			@PathVariable("user") String user, 
			@PathVariable("msgId") Long msgId) {
		return this.messageLogic.like(user, msgId);
	}

	
}
