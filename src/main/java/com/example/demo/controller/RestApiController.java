package com.example.demo.controller;


import com.example.demo.model.Moto;
import com.example.demo.service.MotoService;
import com.example.demo.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.Size;
import java.util.List;


@RestController
@RequestMapping("/api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    MotoService motoService; //servicio que manipulará los datos

    //retornar todas las moto
    @RequestMapping(value = "/moto/", method = RequestMethod.GET)
    public ResponseEntity<List <Moto>> listAllMotos(){
        List <Moto> motos = motoService.findAllMotos();
        if(motos.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Moto>>(motos, HttpStatus.OK);
    }

    //retornar 1 moto
    @RequestMapping(value = "/moto/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMoto(@PathVariable("id") long id){
        logger.info("Fetching Moto with id {}", id);
        Moto moto = motoService.findById(id);
        if(moto == null){
            logger.error("User with {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Moto with id " + id + "not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Moto>(moto, HttpStatus.OK);
    }

    //crear 1 moto
    @RequestMapping(value = "/moto/", method = RequestMethod.POST)
    public ResponseEntity<?> createMoto(@RequestBody Moto moto, UriComponentsBuilder ucBuilder){
        logger.info("Creating Moto : {}", moto);
        if(motoService.isMotoExist(moto)){
            logger.error("Unable to create. A Moto with marca {} already exist", moto.getMarca());
            return new ResponseEntity(new CustomErrorType("Unable to create. A moto with marca" + moto.getMarca() + "already exist."), HttpStatus.CONFLICT);
        }
        motoService.saveMoto(moto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/moto/{id}").buildAndExpand(moto.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    //actualizar moto
    @RequestMapping(value = "moto/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMoto(@PathVariable("id") long id, @RequestBody Moto moto){
        logger.info("Updating Moto with id {}", id);

        Moto currentMoto = motoService.findById(id);

        if(currentMoto == null) {
            logger.error("Unable to update. Moto with id {} not found. ", id);
            return new ResponseEntity(new CustomErrorType("Unable to update. Moto with id " + id + "not found."), HttpStatus.NOT_FOUND);
        }
        currentMoto.setMarca(moto.getMarca());
        currentMoto.setCc(moto.getCc());
        motoService.updateMoto(currentMoto);
        return new ResponseEntity<Moto>(currentMoto, HttpStatus.OK);
    }

    //eliminar moto
    @RequestMapping(value = "/moto/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Moto> deleteMoto(@PathVariable("id") long id){
        logger.info("Fetching & Deleting Moto with id {}", id);

        Moto moto = motoService.findById(id);
        if(moto == null){
            logger.error("Unavle to delete. Moto with {} not found ", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Moto with id " + id + "not found."), HttpStatus.NOT_FOUND);
        }
        motoService.deleteMotoById(id);
        return new ResponseEntity<Moto>(HttpStatus.NO_CONTENT);
    }

    //eliminar todas las motos
    @RequestMapping(value = "/moto/", method = RequestMethod.DELETE)
    public ResponseEntity<Moto> deleteAllNMotos(){
        logger.info("Deleting All Motos");

        motoService.deleteAllMotos();
        return new ResponseEntity<Moto>(HttpStatus.NO_CONTENT);
    }
}
