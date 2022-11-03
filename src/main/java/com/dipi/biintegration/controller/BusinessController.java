package com.dipi.biintegration.controller;

import com.dipi.biintegration.Exception.ResourceNotFoundException;
import com.dipi.biintegration.model.UserDetail;
import com.dipi.biintegration.repository.UserRepository;
import com.dipi.biintegration.service.ConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ny")
public class BusinessController {
    private final ConnectService connectService;
    private final UserRepository userRepository;

     @Autowired
    public BusinessController(ConnectService connectService, UserRepository userRepository) {
        this.connectService = connectService;
         this.userRepository = userRepository;
     }


    @GetMapping("/welcome/{id}")
    @ResponseBody
    public String greeting(@PathVariable  String id ){
        return "Welcome to Spring boot app: "+id;
    }

    @GetMapping("/greeting")
    public String test(){
         return "Welcome to GithubWorkflow for CI/CD pipeline";
    }

    @GetMapping("/welcome")
    @ResponseBody
    public String getGreetingByParams(@RequestParam  String id ){
        return "Welcome to Spring boot app: "+id;
    }

    //http://localhost:8080/ny/test?name=dhanpati&value=123
    @RequestMapping(value="/test",method = RequestMethod.GET)
    public StringBuilder testMyMethod(@RequestParam(name="name")String name,@RequestParam(value="age")String value){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("testing 1");
        stringBuilder.append("dhanpati\t");
        stringBuilder.append(22.40);
        stringBuilder.append("name\t: "+name);
        stringBuilder.append("Value\t: "+value);
        return stringBuilder;
    }
    /*
    @GetMapping ("/users")
    public List<User> getUsers(){
        return connectService.getUsers() ;
    }
     */

    @GetMapping("users/{id}")
    public UserDetail getUser(@PathVariable int id){
         return connectService.getUserById(id);
    }


    @GetMapping("/uhit/{name}")
    public List<UserDetail>getUsersWildCardHit(@PathVariable String name){
         if(connectService.getUsersByWildCardName(name)!=null){
             return connectService.getUsersByWildCardName(name);
         }
         return null;
    }


    @DeleteMapping("/uremove/{id}")
    public Map<String,Boolean> deleteUserById(@PathVariable int id) throws ResourceNotFoundException {
        Map<String,Boolean> res=   connectService.deleteUserById(id);
       throw new ResourceNotFoundException("User not found for this id :: " +id);
    }

    @PostMapping("/adduser")
    public void createUser(@RequestBody UserDetail userDetail){
        connectService.createUser(userDetail);
    }
    @GetMapping("/finduser/{id}")
    public UserDetail findUser(@PathVariable int id ){
        return userRepository.findById(id);
    }
    @GetMapping("/findallusers")
    public List<UserDetail> getllUser(){
        return connectService.getAllUser();
    }

    @DeleteMapping("/deleteuser/{id}")
    public void deleteUser(@PathVariable int id){
        connectService.deleteUser(id);
    }

    @PutMapping("/edituser/{id}")
    public void deleteUser(@RequestBody UserDetail userDetail, @PathVariable int id){
        connectService.editUser(userDetail,id);
    }
}
