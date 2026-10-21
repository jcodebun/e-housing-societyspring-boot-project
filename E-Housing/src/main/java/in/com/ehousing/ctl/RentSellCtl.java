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

import in.com.ehousing.dto.AllocateDTO;
import in.com.ehousing.dto.RentSellDTO;
import in.com.ehousing.dto.UserDTO;
import in.com.ehousing.exception.DuplicateRecordException;
import in.com.ehousing.form.RentSellForm;
import in.com.ehousing.service.AllocateServiceInt;
import in.com.ehousing.service.RentSellServiceInt;
import in.com.ehousing.service.SocietyServiceInt;

@Controller
@RequestMapping("/ctl/rentSell")
public class RentSellCtl extends BaseCtl {

	@Autowired
	private RentSellServiceInt service;
	
	@Autowired
	private AllocateServiceInt allocateService;
	

	@Autowired
	private SocietyServiceInt societyService;
	
	@ModelAttribute
	public void preload(Model model) {
		model.addAttribute("societyList",societyService.search(null));
		
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("Sell","Sell");
		map.put("Rent", "Rent");
		
		model.addAttribute("typeMap",map);
	}

	@GetMapping("/rent")
	public String displayRent(@RequestParam(required = false) Long id,HttpSession session, @ModelAttribute("form") RentSellForm form,
			Model model) {
			UserDTO uDto=(UserDTO)session.getAttribute("user");
			AllocateDTO alDto=allocateService.findByUserName(uDto.getLogin());
			if(service.findByAllocateAndType(alDto, "Rent")==null) {
				form.setAllocate(alDto);
			}else {
				form.populate(service.findByAllocateAndType(alDto, "rent"));
			}
		return "rent";
	}
	
	@GetMapping("/sell")
	public String displaySell(@RequestParam(required = false) Long id,HttpSession session, @ModelAttribute("form") RentSellForm form,
			Model model) {
		UserDTO uDto=(UserDTO)session.getAttribute("user");
		AllocateDTO alDto=allocateService.findByUserName(uDto.getLogin());
		if(service.findByAllocateAndType(alDto, "Sell")==null) {
			form.setAllocate(alDto);
		}else {
			form.populate(service.findByAllocateAndType(alDto, "Sell"));
		}
		return "sell";
	}

	@PostMapping("/rent")
	public String submit(@Valid @ModelAttribute("form") RentSellForm form,
			BindingResult bindingResult,HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/rent";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				
				
				RentSellDTO bean = (RentSellDTO) form.getDTO();
				UserDTO uDto=(UserDTO)session.getAttribute("user");
				AllocateDTO alDto=allocateService.findByUserName(uDto.getLogin());
				System.out.println(alDto.toString());
				form.setAllocate(alDto);
				bean.setAllocate(alDto);
				bean.setHouse(alDto.getHouse());
				bean.setSociety(alDto.getSociety());
				bean.setSocietyId(alDto.getSociety().getId());
				bean.setType("Rent");
				System.out.println("_----------------------------------------"+bean.getId());
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Rent update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Rent Added Successfully!!!!");
				}
				return "rent";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "rent";
		} 
		return "";
	}
	
	
	@PostMapping("/sell")
	public String submitSell(@Valid @ModelAttribute("form") RentSellForm form,
			BindingResult bindingResult,HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/rentSell/sell";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				
				
				RentSellDTO bean = (RentSellDTO) form.getDTO();
				UserDTO uDto=(UserDTO)session.getAttribute("user");
				AllocateDTO alDto=allocateService.findByUserName(uDto.getLogin());
				System.out.println(alDto.toString());
				form.setAllocate(alDto);
				bean.setAllocate(alDto);
				bean.setHouse(alDto.getHouse());
				bean.setSociety(alDto.getSociety());
				bean.setSocietyId(alDto.getSociety().getId());
				bean.setType("Sell");
				System.out.println("_----------------------------------------"+bean.getId());
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Sell update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Sell Added Successfully!!!!");
				}
				return "sell";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "sell";
		} 
		return "";
	}

	@RequestMapping(value = "/search/sell", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") RentSellForm form, @RequestParam(required = false) String operation,
			Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/rentSell/search/sell";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/rentSell/sell";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					RentSellDTO dto = new RentSellDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		RentSellDTO dto = (RentSellDTO) form.getDTO();
		dto.setType("Sell");
		List<RentSellDTO> list = service.search(dto, pageNo, pageSize);
		List<RentSellDTO> totallist = service.search(dto);
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
		return "sellList";
	}
	
	@RequestMapping(value = "/search/rent", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchListRent(@ModelAttribute("form") RentSellForm form, @RequestParam(required = false) String operation,
			Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/rentSell/search/rent";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/rentSell/rent";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					RentSellDTO dto = new RentSellDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		RentSellDTO dto = (RentSellDTO) form.getDTO();
		dto.setType("Rent");
		List<RentSellDTO> list = service.search(dto, pageNo, pageSize);
		List<RentSellDTO> totallist = service.search(dto);
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
		return "rentList";
	}
	
	
	@RequestMapping(value = "/search/home", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchListhomeSearch(@ModelAttribute("form") RentSellForm form, @RequestParam(required = false) String operation,
			Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/rentSell/search/home";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} 

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;


		RentSellDTO dto = (RentSellDTO) form.getDTO();
		if(dto!=null) {
		if(dto.getSocietyId()>0) {
			dto.setSociety(societyService.findBypk(dto.getSocietyId()));
		}
		}
		List<RentSellDTO> list = service.search(dto, pageNo, pageSize);
		List<RentSellDTO> totallist = service.search(dto);
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
		return "homeSearch";
	}
	
	

}
