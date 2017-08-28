package com.example.demo.service;

import com.example.demo.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MotoService {

    Moto findById(long id);
    Moto findByName(String name);
    void saveMoto(Moto moto);
    void updateMoto(Moto moto);
    void deleteMotoById(long id);
    List<Moto> findAllMotos();
    void deleteAllMotos();
    boolean isMotoExist(Moto moto);

}
