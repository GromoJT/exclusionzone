package pl.gromotj.exclusionzone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.gromotj.exclusionzone.service.EmailService;

@SpringBootApplication
public class ExclusionzoneApplication {

//	@Autowired
//	private EmailService emailService;
	public static void main(String[] args) {
		SpringApplication.run(ExclusionzoneApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void sendMail(){
//		emailService.sendEmail("janek.j113@gmail.com","TEst","Test sie udał :)");
//	}
}
