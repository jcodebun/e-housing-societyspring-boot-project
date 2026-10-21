package in.com.ehousing.ctl;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.com.ehousing.dto.AllocateDTO;
import in.com.ehousing.dto.SocietyDTO;
import in.com.ehousing.dto.UserDTO;
import in.com.ehousing.exception.DuplicateRecordException;
import in.com.ehousing.form.ChangePasswordForm;
import in.com.ehousing.form.ForgetPasswordForm;
import in.com.ehousing.form.LoginForm;
import in.com.ehousing.form.MyProfileForm;
import in.com.ehousing.form.SocietyForm;
import in.com.ehousing.form.UserRegistrationForm;
import in.com.ehousing.service.AllocateServiceInt;
import in.com.ehousing.service.SocietyServiceInt;
import in.com.ehousing.service.UserServiceInt;


@Controller
public class LoginCtl extends BaseCtl {

	private Logger log = Logger.getLogger(LoginCtl.class.getName());

	protected static final String OP_SIGNIN = "SignIn";
	protected static final String OP_SIGNUP = "SignUp";
	protected static final String OP_LOGOUT = "Logout";

	@Autowired
	private UserServiceInt service;
	
	@Autowired
	private AllocateServiceInt allocateService;
	
	@Autowired
	private SocietyServiceInt societyService;

	@GetMapping("/login")
	public String display(@ModelAttribute("form") LoginForm form, HttpSession session, Model model) {
		log.info("LoginCtl login display method start");
		if (session.getAttribute("user") != null) {
			session.invalidate();
			model.addAttribute("success", "You have logout Successfully!!!");
		}
		model.addAttribute("list",societyService.search(null));
		log.info("LoginCtl login display method End");
		return "login";
	}
	
	@ModelAttribute
	public void preload(Model model) {		

		HashMap<String,String> map=new HashMap<String, String>();
		map.put("Male", "Male");
		map.put("Female", "Female");
		model.addAttribute("gender",map);
		
	}

	@PostMapping("/login")
	public String submit(@RequestParam String operation, HttpSession session,
			@Valid @ModelAttribute("form") LoginForm form, BindingResult result, Model model) {
		log.info("LoginCtl login submit method start");
		System.out.println("In dopost  LoginCtl");

		if (OP_SIGNUP.equalsIgnoreCase(form.getOperation())) {
			return "redirect:signUp";
		}

		if (result.hasErrors()) {
			System.out.println(result);
			return "login";
		}

		UserDTO bean = service.authentication((UserDTO) form.getDTO());

		if (bean != null) {
			System.out.println(bean.toString());
			session.setAttribute("user", bean);
			if(bean.getRoleId()==1) {
			return "redirect:/welcome";
			}else {
				return "redirect:/userDetail";
			}
		}
		model.addAttribute("error", "Login Id Password Invalid");
		model.addAttribute("list",societyService.search(null));
		log.info("LoginCtl login submit method End");
		return "login";
	}

	@GetMapping("/signUp")
	public String display(@ModelAttribute("form") UserRegistrationForm form, Model model) {
		log.info("LoginCtl signUp display method start");
		log.info("LoginCtl signUp display method End");
		return "signUp";
	}

	@PostMapping("/signUp")
	public String submit(@RequestParam String operation, @Valid @ModelAttribute("form") UserRegistrationForm form,
			BindingResult bindingResult, Model model) {

		log.info("LoginCtl signUp submit method start");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:signUp";
		}

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return "signUp";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				System.out.println("in Save");

				UserDTO entity = (UserDTO) form.getDTO();
				System.out.println(entity.toString());
				entity.setRoleId(2L);
				service.add(entity);
				model.addAttribute("success", "User Registerd Successfully!!!!");
				return "signUp";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "signUp";
		}

		log.info("LoginCtl signUp submit method end");
		return "signUp";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String displayProfile(HttpSession session, @ModelAttribute("form") MyProfileForm form, Model model) {
		UserDTO entity = (UserDTO) session.getAttribute("user");
		AllocateDTO alDto=allocateService.findByUserName(entity.getLogin());
		form.populate(alDto);
		System.out.println("/Myprofile");
		return "myprofile";
	}

	
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String submitProfile(HttpSession session, @ModelAttribute("form") @Valid MyProfileForm form,BindingResult bindingResult,
			@RequestParam(required = false) String operation,  Model model) {


		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/profile";
		}

		if (bindingResult.hasErrors()) {
			return "myprofile";
		}
		UserDTO entity = (UserDTO) session.getAttribute("user");
		entity = service.findBypk(entity.getId());
		AllocateDTO alDto=allocateService.findByUserName(entity.getLogin());
		entity.setFirstName(form.getFirstName());
		entity.setLastName(form.getLastName());
		alDto.setFirstName(form.getFirstName());
		alDto.setLastName(form.getLastName());
		alDto.setEmail(form.getEmail());
		alDto.setMobileNo(form.getMobileNo());
		try {
			service.update(entity);
			allocateService.update(alDto);
		} catch (DuplicateRecordException e) {

		}
		model.addAttribute("success", "Profile Update successfully");
		return "myprofile";
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String displayChangePassword(@ModelAttribute("form") ChangePasswordForm form, Model model) {
		return "changePassword";
	}
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public String submitChangePassword(HttpSession session,
			@ModelAttribute("form") @Valid ChangePasswordForm form, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "changePassword";
		}
		if(form.getNewPassword().equalsIgnoreCase(form.getConfirmPassword())) {

		UserDTO dto = (UserDTO) session.getAttribute("user");
		dto = service.findBypk(dto.getId());
		
		if (service.changePassword(dto.getId(), form.getOldPassword(),form.getNewPassword())) {
			model.addAttribute("success","Password changed Successfully");
		} else {
			model.addAttribute("error","Old Passowors Does not Matched");
		}
		}else {
			model.addAttribute("error","New Password and confirm password does not matched");
		}
		return "changePassword";
	}
	
	
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") ForgetPasswordForm form, HttpSession session, Model model) {

		System.out.println("In doget LoginCtl forgetpassword");

		return "forgetPassword";

	}
	
	


	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public String display(@ModelAttribute("form") @Valid ForgetPasswordForm form,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "forgetPassword";
		}

		UserDTO dto = service.findByUserName(form.getLogin());

		if (dto == null) {
			model.addAttribute("error", "Login Id does not exist");
		}

		if(dto!=null){
		service.forgetPassword(form.getLogin());
		model.addAttribute("success","Password has been sent to your registered Email ID!!");
		
	//	model.addAttribute("success", "Password has been sent to your registered Email ID!!");
		}
		return "forgetPassword";
	}
	
	@PostMapping("/update/allocate/image")
	public String submitUserImage(@RequestParam("image") MultipartFile file,HttpSession session, Model model) {
		
		UserDTO uDto=(UserDTO)session.getAttribute("user");
		AllocateDTO alDto=allocateService.findByUserName(uDto.getLogin());
		try {
			alDto.setImage(file.getBytes());
			allocateService.update(alDto);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
		
		return "redirect:/userDetail";
	}
	
	@PostMapping("/update/society/image")
	public String submitSocietyImage(@RequestParam("image") MultipartFile file,HttpSession session, Model model) {
		
		UserDTO uDto=(UserDTO)session.getAttribute("user");
		AllocateDTO alDto=allocateService.findByUserName(uDto.getLogin());
		SocietyDTO sDto=alDto.getSociety();
		try {
			sDto.setImage(file.getBytes());
			societyService.update(sDto);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
		return "redirect:/userDetail";
	}
	

}
