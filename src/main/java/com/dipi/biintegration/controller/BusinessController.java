package com.dipi.biintegration.controller;

import com.dipi.biintegration.Exception.ResourceNotFoundException;
import com.dipi.biintegration.model.User;
import com.dipi.biintegration.service.ConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ny")
public class BusinessController {
    private final ConnectService connectService;

     @Autowired
    public BusinessController(ConnectService connectService) {
        this.connectService = connectService;
    }


    @GetMapping("/welcome/{id}")
    @ResponseBody
    public String greeting(@PathVariable  String id ){
        return "Welcome to Spring boot app: "+id;
    }

    @GetMapping("/welcome")
    @ResponseBody
    public String getGreetingByParams(@RequestParam  String id ){
        return "Welcome to Spring boot app: "+id;
    }

    @RequestMapping(value="/test",method = RequestMethod.GET)
    public StringBuilder testMyMethod(@RequestParam(name="name")String name,@RequestParam(value="value")String value){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("testing 1");
        stringBuilder.append("dhanpati\t");
        stringBuilder.append(22.40);
        stringBuilder.append("name\t: "+name);
        stringBuilder.append("Value\t: "+value);
        return stringBuilder;
    }

    @GetMapping ("/users")
    public List<User> getUsers(){
        return connectService.getUsers() ;
    }

    @GetMapping("users/{id}")
    public User getUser(@PathVariable int id){
         return connectService.getUserById(id);
    }


    @GetMapping("/uhit/{name}")
    public List<User>getUsersWildCardHit(@PathVariable String name){
         return connectService.getUsersByWildCardName(name);
    }

    @DeleteMapping("/uremove/{id}")
    public Map<String,Boolean> deleteUserById(@PathVariable int id) throws ResourceNotFoundException {
        Map<String,Boolean> res=   connectService.deleteUserById(id);
       throw new ResourceNotFoundException("User not found for this id :: " +id);
    }
}
