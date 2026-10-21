package in.com.ehousing.ctl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import in.com.ehousing.dto.ComplainDTO;
import in.com.ehousing.dto.UserDTO;
import in.com.ehousing.exception.DuplicateRecordException;
import in.com.ehousing.form.ComplainForm;
import in.com.ehousing.service.ComplainServiceInt;

@Controller
@RequestMapping("/ctl/complain")
public class ComplainCtl extends BaseCtl {

	@Autowired
	private ComplainServiceInt service;
	

	@ModelAttribute
	public void preload(Model model) {
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("Pending","Pending");
		map.put("Complete","Complete");
		model.addAttribute("statusType",map);
	}

	@GetMapping
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") ComplainForm form,
			Model model) {
		if (form.getId() > 0) {
			ComplainDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "complain";
	}

	@PostMapping
	public String submit(@Valid @ModelAttribute("form") ComplainForm form,
			BindingResult bindingResult,HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/complain";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "complain";
				}

				ComplainDTO bean = (ComplainDTO) form.getDTO();
				
				if (bean.getId() > 0) {
					ComplainDTO cmDto=service.findBypk(bean.getId());
					bean.setUserId(cmDto.getUserId());
					service.update(bean);
					model.addAttribute("success", "Complain update Successfully!!!!");
				} else {
					bean.setStatus("Pending");
					UserDTO uDto=(UserDTO)session.getAttribute("user");
					bean.setUserId(uDto.getId());
					service.add(bean);
					model.addAttribute("success", "Complain Added Successfully!!!!");
				}
				return "complain";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "complain";
		} 
		return "";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") ComplainForm form, @RequestParam(required = false) String operation,
			Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/complain/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/complain";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					ComplainDTO dto = new ComplainDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		ComplainDTO dto = (ComplainDTO) form.getDTO();
		UserDTO uDto=(UserDTO)session.getAttribute("user");
		if(uDto.getRoleId()==2) {
			dto.setUserId(uDto.getId());
		}
		List<ComplainDTO> list = service.search(dto, pageNo, pageSize);
		List<ComplainDTO> totallist = service.search(dto);
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
		return "complainList";
	}
	
	

}
