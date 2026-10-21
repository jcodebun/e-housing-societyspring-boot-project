package in.com.ehousing.ctl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

import in.com.ehousing.dto.MessageDTO;
import in.com.ehousing.dto.RentSellDTO;
import in.com.ehousing.dto.UserDTO;
import in.com.ehousing.exception.DuplicateRecordException;
import in.com.ehousing.form.MessageForm;
import in.com.ehousing.service.AllocateServiceInt;
import in.com.ehousing.service.MessageServiceInt;
import in.com.ehousing.service.RentSellServiceInt;
import in.com.ehousing.util.DataUtility;

@Controller
@RequestMapping("/message")
public class MessageCtl extends BaseCtl {

	@Autowired
	private MessageServiceInt service;
	
	@Autowired
	private RentSellServiceInt rentService;
	
	@Autowired
	private AllocateServiceInt allocateService;
	

	@ModelAttribute
	public void preload(Model model) {
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("Pending","Pending");
		map.put("Complete", "Complete");
		model.addAttribute("statusMap",map);
		
	}

	@GetMapping
	public String display(@RequestParam(required = false) Long id,Long rid, @ModelAttribute("form") MessageForm form,
			HttpSession session,Model model) {
		if (form.getId() > 0) {
			MessageDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		form.setRent(rentService.findBypk(rid));
		session.setAttribute("rid",rid);
		return "message";
	}

	@PostMapping
	public String submit(@Valid @ModelAttribute("form") MessageForm form,
			BindingResult bindingResult,HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/message";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				MessageDTO bean = (MessageDTO) form.getDTO();
				long rid=DataUtility.getLong(String.valueOf(session.getAttribute("rid")));
				RentSellDTO rDto=rentService.findBypk(rid);
				form.setRent(rDto);
				System.out.println(rDto);
				bean.setAllocateId(rDto.getAllocate().getId());
				bean.setDate(new Date());
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Message update Successfully!!!!");
				} else {
					UserDTO uDto=(UserDTO)session.getAttribute("user");
					service.add(bean);
					model.addAttribute("success", "Message Added Successfully!!!!");
				}
				return "message";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "message";
		} 
		return "";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") MessageForm form, @RequestParam(required = false) String operation,
			Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/message/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/message";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					MessageDTO dto = new MessageDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		MessageDTO dto = (MessageDTO) form.getDTO();
		UserDTO uDto=(UserDTO)session.getAttribute("user");
		if(uDto.getRoleId()==2) {
			dto.setAllocateId(allocateService.findByUserName(uDto.getLogin()).getId());
		}
		List<MessageDTO> list = service.search(dto, pageNo, pageSize);
		List<MessageDTO> totallist = service.search(dto);
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
		return "messageList";
	}
	
	

}
