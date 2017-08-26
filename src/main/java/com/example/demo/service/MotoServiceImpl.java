package com.example.demo.service;

import com.example.demo.model.Moto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MotoServiceImpl implements MotoService {

    private static final AtomicLong counter = new AtomicLong();
    private static List<Moto> motos;

    static{
        motos = populateDummyMotos();
    }

    public List<Moto> findAllMotos(){
        return motos;
    }

    public Moto findById(long id){
        for(Moto moto: motos){
            if(moto.getId() == id){
                return moto;
            }
        }
        return null;
    }

    public Moto findByName(String name){
        for(Moto moto: motos){
            if(moto.getMarca().equalsIgnoreCase(name)){
                return moto;
            }
        }
        return null;
    }

    public void saveMoto(Moto moto){
        moto.setId(counter.incrementAndGet());
        motos.add(moto);
    }

    public void updateMoto(Moto moto){
        int index = motos.indexOf(moto);
        motos.set(index, moto);
    }

    public void deleteMotoById(long id){
        for(Iterator<Moto> iterator = motos.iterator(); iterator.hasNext();){
            Moto moto = iterator.next();
            if(moto.getId() == id){
                iterator.remove();
            }
        }
    }

    public boolean isMotoExist(Moto moto){
        return findByName(moto.getMarca()) != null;
    }

    public void deleteAllMotos(){
        motos.clear();
    }

    private static List<Moto> populateDummyMotos(){
        List<Moto> motos = new ArrayList<Moto>();
        motos.add(new Moto(counter.incrementAndGet(),"Kawasaki",600));
        motos.add(new Moto(counter.incrementAndGet(),"Ducati",899));
        motos.add(new Moto(counter.incrementAndGet(),"Yamaha",1000));
        motos.add(new Moto(counter.incrementAndGet(),"Triumph",800));
        motos.add(new Moto(counter.incrementAndGet(),"Honda",600));
        return motos;
    }

}
