package com.example.demo.controller;


import com.example.demo.model.Auto;
import com.example.demo.service.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("/api")
public class AutoController {

    @Autowired
    private AutoRepository autoData;

    @RequestMapping(value = "/auto/", method = RequestMethod.POST)
    public ResponseEntity<?> newAuto(@RequestBody Auto auto, UriComponentsBuilder ucBuilder){
        autoData.save(auto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/auto/{id}").buildAndExpand(auto.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/auto/", method = RequestMethod.GET)
    public ResponseEntity<List <Auto>> getAutos(){
        List<Auto> autos = autoData.findAll();
        return new ResponseEntity<List<Auto>>(autos, HttpStatus.OK);
    }



}
