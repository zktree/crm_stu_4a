package com.crm.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.crm.model.User;
import com.crm.service.UserService;

/**
 * 登录，登出
 * @author dz
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource(name="userService")
	private UserService userService;
	/**
	 * 登录
	 * @param name
	 * @param password
	 * @param verifyCode
	 * @param session
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(
			String name,
			String password,
			String verifyCode,
			HttpSession session){
		//比较验证码
		String sessionCode=(String) session.getAttribute("validateCode");
		if(verifyCode!=null && verifyCode.equalsIgnoreCase(sessionCode)){
			//比较用户名和密码
			User user=userService.findUser(name,password);
			if(user!=null){
				if("启用".equals(user.getStatus())){
					session.setAttribute("user", user);
					ModelAndView mav=new ModelAndView("redirect:toMain");
					return mav;
				}else{
					ModelAndView mav=new ModelAndView("/login.jsp");
					mav.addObject("error", "请联系管理员");
					return mav;
				}
			}else{//没有查到
				ModelAndView mav=new ModelAndView("/login.jsp");
				mav.addObject("error", "用户名或密码错误");
				return mav;
			}


		}else{
			ModelAndView mav=new ModelAndView("/login.jsp");
			mav.addObject("error", "验证码错误");
			return mav;
		}
	}
	@RequestMapping("/toMain")
	public ModelAndView toMain(){
		return new ModelAndView("/main.jsp");
	}

}
