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

import in.com.ehousing.dto.HouseDTO;
import in.com.ehousing.exception.DuplicateRecordException;
import in.com.ehousing.form.HouseForm;
import in.com.ehousing.service.HouseServiceInt;
import in.com.ehousing.service.SocietyServiceInt;

@Controller
@RequestMapping("/ctl/house")
public class HouseCtl extends BaseCtl {

	@Autowired
	private HouseServiceInt service;
	
	@Autowired
	private SocietyServiceInt societyService;

	@ModelAttribute
	public void preload(Model model) {
		model.addAttribute("societyList",societyService.search(null));
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("1BHK","1BHK");
		map.put("2BHK","2BHK");
		map.put("3BHK","3BHK");
		map.put("4BHK","4BHK");
		
		model.addAttribute("houseType",map);
	}

	@GetMapping
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") HouseForm form,
			Model model) {
		if (form.getId() > 0) {
			HouseDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "house";
	}

	@PostMapping
	public String submit(@Valid @ModelAttribute("form") HouseForm form,
			BindingResult bindingResult, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/house";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "house";
				}

				HouseDTO bean = (HouseDTO) form.getDTO();
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "House update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "House Added Successfully!!!!");
				}
				return "house";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "house";
		} 
		return "";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") HouseForm form, @RequestParam(required = false) String operation,
			Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/house/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/house";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					HouseDTO dto = new HouseDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		HouseDTO dto = (HouseDTO) form.getDTO();
		List<HouseDTO> list = service.search(dto, pageNo, pageSize);
		List<HouseDTO> totallist = service.search(dto);
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
		return "houseList";
	}
	
	

}
