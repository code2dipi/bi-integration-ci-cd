package com.dipi.biintegration.service;

import com.dipi.biintegration.model.UserDetail;
import com.dipi.biintegration.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConnectService {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectService.class);
    private final List<UserDetail> userDetailList = new ArrayList<>();

    private final UserRepository userRepository;

    @Autowired
    public ConnectService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetail getUserById(int id) {
        List<UserDetail> mylist = List.of(new UserDetail("null", null, null));

        for (UserDetail userDetail : mylist) {
            if (userDetail.getId() == id) {
                return userDetail;
            }
        }
        throw new IllegalArgumentException("Null pointer exception or null values");
    }

    /**
     * Wild card hit
     *
     * @param name
     * @return
     */
    public List<UserDetail> getUsersByWildCardName(String name) {
        List<UserDetail> mylist = null;
        List<UserDetail> hitListUserDetail = new ArrayList<>();
        for (UserDetail userDetail : mylist) {
            if (userDetail.getName().contains(name)) {
                hitListUserDetail.add(userDetail);
            }

        }
        return hitListUserDetail;
    }

    public Map<String, Boolean> deleteUserById(int id) {
        List<UserDetail> mylist = null;//getUsers();

        for (UserDetail userDetail : mylist) {
            if (userDetail.getId() == id) {
                mylist.remove(userDetail);
            }
        }
        Map<String, Boolean> response = new HashMap<>();
        response.put("successfully deleted", Boolean.TRUE);
        return response;
    }

    /**
     * Create UserDetail
     * @param userDetail
     */
    public void createUser(UserDetail userDetail) {
        if (userDetail.getName().trim().isEmpty() || userDetail.getName().trim() == null) {
            LOG.info("Can not save to database ..object is null");
            return;
        } else {
            UserDetail ud = new UserDetail();
            ud.setName(userDetail.getName());
            ud.setEmail(userDetail.getEmail());
            ud.setPhone(userDetail.getPhone());
            userRepository.save(userDetail);
            LOG.info("Successfully saved to Database");
        }

    }

    /**
     * List all UserDetail
     * @return
     */
    public List<UserDetail> getAllUser() {
        List<UserDetail> userDetails = new ArrayList<>();
        userDetails = userRepository.findAll();
        if (userDetails != null) {
            return userDetails;
        }
        return null;
    }

    /**
     * Delete UserDetail
     * @param id
     */
    public void deleteUser(int id ){
       UserDetail userFromDB=userRepository.findById(id);
        if(userFromDB!=null){
            userRepository.delete(userFromDB);
            LOG.info("User{} Deleted successfully: ");
        }else{
            throw new IllegalArgumentException("User can not delete..");
        }
    }

    /**
     * Edit UserDetail
     * @param userDetail
     * @param id
     */
    public void editUser(UserDetail userDetail,int id){
        UserDetail userFromDB=userRepository.findById(id);
        if(userFromDB!=null){
            UserDetail u=new UserDetail();
            u.setName(userDetail.getName());
            u.setEmail(userDetail.getEmail());
            u.setPhone(userDetail.getPhone());
        }
    }


}
