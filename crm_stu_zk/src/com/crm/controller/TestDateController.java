package com.crm.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.crm.model.Product;
import com.util.DateEditor;

@Controller
@RequestMapping("/testDate")
public class TestDateController {
    @InitBinder  
    public void initBinder(WebDataBinder binder){  
        binder.registerCustomEditor(Date.class, new DateEditor());  
    } 
	
	
	
	@RequestMapping("/showDate")
	public ModelAndView showDate(){
		Date date=new Date();
		ModelAndView mav=new ModelAndView("/jsp/date/test1.jsp");
		mav.addObject("createTime", date);
		return mav;
	}
	
	@RequestMapping("/add")
	public ModelAndView add(Product product){
		System.out.println("createTime:"+product.getCreateTime());
		return null;
	}

}
