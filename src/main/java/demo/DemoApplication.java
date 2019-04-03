package demo;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DemoApplication implements CommandLineRunner{
	private String url;
	
	@Value("${message.service.url}")
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public void run(String... args) throws Exception {
		RestTemplate rest = new RestTemplate();
		
		Message newMessage = new Message("demo");
		newMessage.setDetails(new Details("none", "demo"));
		
		Long newMessageId = rest.postForObject(
				url + "message", 
				newMessage, 
				Message.class)
			.getId();
		
		Stream.of("Chuck", "Claud", "Ciril")
			.forEach(user->rest.postForObject(
					url + "like/{user}/{msgId}", 
					
					null, 
					
					Like.class,
					
					user, newMessageId));
		
		Message theMessageFromDb =
			rest.getForObject(
					this.url + "message/{msgId}",
					Message.class,
					newMessageId);
		
		System.err.println(theMessageFromDb + " - likes:  ");
		theMessageFromDb.getLike()
			.forEach(System.err::println);
				
		
	}

}
