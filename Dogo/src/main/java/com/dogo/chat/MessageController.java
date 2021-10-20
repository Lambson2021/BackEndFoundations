package com.dogo.chat;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://172.29.224.1:8081")

public class MessageController {
//2.
	@Autowired
	MessageRepository dao;  //gives us access to crud methods needed to communicate
	
	@GetMapping("math/add/{x}/{y}")
	public int getMath(@PathVariable("x") int x,@PathVariable("y") int y ) {
	    return x + y;
	}
	//3.
	@GetMapping("/chat") //localhost:8080/chat Empty Array
	public List<Message>getMessages(){
		List<Message> foundMessages = dao.findAll(); //returns all messages and assigns them
		return foundMessages;
	}
	
	@GetMapping("/chat/id") //localhost:8080/chat Empty Array
	public ResponseEntity<Message> getMessage(@PathVariable(value="id")Integer id) {

	    // Saving to DB using an instance of the repo interface.
	    Message foundMessage = dao.findById(id).orElse(null);
	    
	    if(foundMessage == null) {
	    	return ResponseEntity.notFound().header("Message", "Nothing found with that id").build();
	    }
	    

	    // RespEntity crafts response to include correct status codes.
	    return ResponseEntity.ok(foundMessage);
	
	}
	//Add a post method to send data
	
	@PostMapping("/chat") //405 error restart serverx	
	public ResponseEntity<Message> postMessage(@RequestBody Message message) {

	    // Saving to DB using an instance of the repo interface.
	    Message createdMessage = dao.save(message);

	    // RespEntity crafts response to include correct status codes.
	    return ResponseEntity.ok(createdMessage);
	}
	
	@DeleteMapping("/chat/{id}") //405 error restart serverx	
	public ResponseEntity<Message> deleteMessage(@PathVariable(value="id")Integer id) {
	    Message foundMessage = dao.findById(id).orElse(null);
	    if(foundMessage ==null) {

	    // RespEntity crafts response to include correct status codes.
	    return ResponseEntity.notFound().header("Message", "Nothing found with that id").build();
	    
	}else {
		dao.delete(foundMessage);
		
	}
	    return ResponseEntity.ok().build();
}
}
