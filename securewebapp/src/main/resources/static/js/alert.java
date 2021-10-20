import org.springframework.web.bind.annotation.GetMapping;

@GetMapping("/login")
    public String getLoginPage() {
	alert('Im Alive');
        return "login";
        
    }