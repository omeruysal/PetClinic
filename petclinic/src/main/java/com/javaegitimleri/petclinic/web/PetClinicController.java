package com.javaegitimleri.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javaegitimleri.petclinic.service.PetClinicService;

@Controller //Controller beanı yaratır ve sınıfın methodlarını gelen map istekleriyle eslestırır
public class PetClinicController {

	@Autowired
	private PetClinicService petClinicService;
	
	@RequestMapping("/owners")
	public ModelAndView getOwners() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("owners", petClinicService.findOwners());
		mav.setViewName("owners");
		return mav;
	}
	
	
	
	@RequestMapping("/pcs") //calısacagı url
	@ResponseBody  // web requestlerını handle edıyoruz
	public String welcome() {  // ----->handler method
		
		return "Welcome to World";
	}
}
