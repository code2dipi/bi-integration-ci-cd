package com.dipi.biintegration.service;

import com.dipi.biintegration.Exception.ResourceNotFoundException;
import com.dipi.biintegration.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConnectService {
    private static final Logger LOG= LoggerFactory.getLogger(ConnectService.class);
    List<User> userList=new ArrayList<>();

    public List<User> getUsers(){
        User user1=new User();
        user1.setId(1);
        user1.setName("Tester");
        user1.setEmail("testter@gmail.com");
        user1.setPhone("45656566");

        User user2=new User();
        user2.setId(2);
        user2.setName("Automation");
        user2.setEmail("automat@gmail.com");
        user2.setPhone("896532");

        User user3=new User();
        user3.setId(3);
        user3.setName("Test");
        user3.setEmail("test@gmail.com");
        user3.setPhone("546546");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        return userList;
    }
    public User getUserById(int id){
        List<User> mylist=getUsers();
        for(User user:mylist){
           if(user.getId()==id){
              return user;
           }
        }
        throw new IllegalArgumentException("Null pointer exception or null values");
    }

    /**
     * Wild card hit
     * @param name
     * @return
     */
    public List<User> getUsersByWildCardName(String name){
        List<User> mylist=getUsers();
        List<User> hitListUser=new ArrayList<>();
        for(User user:mylist){
            if(user.getName().contains(name)){
                hitListUser.add(user);
            }

        }
        return hitListUser;
    }
    public Map<String,Boolean> deleteUserById(int id) {
        List<User> mylist=getUsers();

        for(User user:mylist){
            if(user.getId()==id){
                mylist.remove(user);
            }
        }
        Map<String, Boolean> response = new HashMap<>();
        response.put("successfully deleted",Boolean.TRUE);
        return response;
    }
}
