package in.com.ehousing.ctl;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.com.ehousing.dto.UserDTO;
import in.com.ehousing.form.UserDetailForm;
import in.com.ehousing.service.AllocateServiceInt;

@Controller
public class WelcomeCtl extends BaseCtl {
	
		@Autowired
		private AllocateServiceInt allocateService;
	
	 @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	    public String welcome(Model model) {
		 
	        return "welcome";
	    }
	 
	 @RequestMapping(value = {"/contactUs" }, method = RequestMethod.GET)
	    public String contactUs(Model model) {
	        return "contactUs";
	    }
	 
	 @RequestMapping(value = {"/userDetail" }, method = RequestMethod.GET)
	    public String demo(HttpSession session,Model model,@ModelAttribute("form") UserDetailForm form) {
		 	UserDTO uDto=(UserDTO)session.getAttribute("user");
		 	model.addAttribute("allocate",allocateService.findByUserName(uDto.getLogin()));
	        return "userDetail";
	    }
	 
	 @RequestMapping(value = {"/demoList" }, method = RequestMethod.GET)
	    public String demoList(Model model) {
	        return "demoList";
	    }
}
