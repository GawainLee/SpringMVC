package com.gawain.controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gawain.entities.Users;
import com.gawain.servicesapi.UsersService;

@Controller
@RequestMapping("users")
public class UsersController {

   @Autowired
   UsersService userServices;

   @RequestMapping(value = "/page", method = RequestMethod.GET)
   public ModelAndView getPage() {
       ModelAndView view = new ModelAndView("users");
       return view;
   }

   @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
   public @ResponseBody Map<String, Object> getSaved(HttpServletRequest request,HttpServletResponse response,@RequestBody Users users) {
       Map<String, Object> map = new HashMap<String, Object>();

       if (userServices.saveOrUpdate(users)) {
           map.put("status", "200");
           map.put("message", "Your record have been saved successfully");
       }

       return map;
   }

   @RequestMapping(value = "/list", method = RequestMethod.POST)
   public @ResponseBody Map<String, Object> getAll(Users users) {
       Map<String, Object> map = new HashMap<String, Object>();

       List<Users> list = userServices.list();

       if (list != null) {
           map.put("status", "200");
           map.put("message", "Data found");
           map.put("data", list);
       } else {
           map.put("status", "404");
           map.put("message", "Data not found");

       }

       return map;
   }

   @RequestMapping(value = "/delete", method = RequestMethod.POST)
   public @ResponseBody Map<String, Object> delete(HttpServletRequest request,HttpServletResponse response,@RequestBody Users users) {
       Map<String, Object> map = new HashMap<String, Object>();

       if (userServices.delete(users)) {
           map.put("status", "200");
           map.put("message", "Your record have been deleted successfully");
       }

       return map;
   }
   
   @RequestMapping(value = "/getList", method = RequestMethod.GET)
   public Map<String, Object> getList() {
       Map<String, Object> map = new HashMap<String, Object>();

       List<Users> a = this.userServices.list();
       for(int i = 0; i < a.size(); i++){
    	   map.put(i+"", a.get(i));
       }
       
         return map;
   }
   
   /**
	 *  返回Json的示例：/hello/json1
	 * 
	 *  这里加了@ResponseBody注解，说明将List<User>作为响应体，
	 *  将其响应为Json数据，因为已经在spring-mvc-servlet.xml进行配置
	 *  
	 */
	@RequestMapping(value="json1",method=RequestMethod.GET)
	public @ResponseBody List<Users> getUserInJson1(){
		List<Users> a = this.userServices.list();
		return  a;
	}
	
	@RequestMapping(value="json1",method=RequestMethod.POST)
	public @ResponseBody List<Users> getUserInJson1(HttpServletRequest request,HttpServletResponse response,@RequestBody Users users){
		List<Users> a = this.userServices.list();
		return  a;
	}
	
	
	@RequestMapping(value="json2",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getUserInJson2(Users users){
	    Map<String, Object> map = new HashMap<String, Object>();

	    if (userServices.delete(users)) {
	        map.put("status", "200");
	        map.put("message", "Your record have been deleted successfully");
	    }

	    return map;
	}

	
	
}