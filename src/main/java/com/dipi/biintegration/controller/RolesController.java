package com.dipi.biintegration.controller;

import com.dipi.biintegration.model.Roles;
import com.dipi.biintegration.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ny")
public class RolesController {
    private final RolesRepository rolesRepository;

    @Autowired
    public RolesController(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Roles> createRoles(@RequestBody Roles roles) {
        roles = new Roles("ADMIN");
        return new ResponseEntity<>(rolesRepository.save(roles), HttpStatus.CREATED);

    }
    @GetMapping("/role")
    public List<Roles> getRoles(){
        return rolesRepository.findAll();
    }
}
