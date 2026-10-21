package in.com.ehousing.ctl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.com.ehousing.dto.AllocateDTO;
import in.com.ehousing.dto.UserDTO;
import in.com.ehousing.exception.DuplicateRecordException;
import in.com.ehousing.form.AllocateForm;
import in.com.ehousing.service.AllocateServiceInt;
import in.com.ehousing.service.HouseServiceInt;
import in.com.ehousing.service.SocietyServiceInt;
import in.com.ehousing.service.UserServiceInt;

@Controller
@RequestMapping("/ctl/allocate")
public class AllocateCtl extends BaseCtl {

	@Autowired
	private AllocateServiceInt service;
	
	@Autowired
	private SocietyServiceInt societyService;
	
	@Autowired
	private HouseServiceInt houseService;
	
	
	@Autowired
	private UserServiceInt userService;

	@ModelAttribute
	public void preload(Model model) {
		model.addAttribute("societyList",societyService.search(null));
		model.addAttribute("houseList",houseService.search(null));
	}

	@GetMapping
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") AllocateForm form,
			Model model) {
		if (form.getId() > 0) {
			AllocateDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "allocate";
	}

	@PostMapping
	public String submit(@RequestParam("image") MultipartFile file, @Valid @ModelAttribute("form") AllocateForm form,
			BindingResult bindingResult, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/allocate";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "allocate";
				}

				AllocateDTO bean = (AllocateDTO) form.getDTO();
				bean.setImage(file.getBytes());
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Allocate update Successfully!!!!");
				} else {
					UserDTO uDto=new UserDTO();
					uDto.setFirstName(bean.getFirstName());
					uDto.setLastName(bean.getLastName());
					uDto.setLogin(bean.getUserName());
					uDto.setPassword(bean.getPassword());
					uDto.setRoleId(2L);
					userService.add(uDto);
					service.add(bean);
					model.addAttribute("success", "Allocate Added Successfully!!!!");
				}
				return "allocate";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "allocate";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") AllocateForm form, @RequestParam(required = false) String operation,
			Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/allocate/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/allocate";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					AllocateDTO dto = new AllocateDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		AllocateDTO dto = (AllocateDTO) form.getDTO();
		List<AllocateDTO> list = service.search(dto, pageNo, pageSize);
		List<AllocateDTO> totallist = service.search(dto);
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
		}

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return "allocateList";
	}
	
	@GetMapping("/getImage/{id}")
	public void getImage(HttpServletResponse response, @PathVariable("id") long id) throws Exception {
		response.setContentType("image/jpeg");
		Blob blb=service.getImageById(id);
		byte[] bytes = blb.getBytes(1, (int) blb.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}

}
