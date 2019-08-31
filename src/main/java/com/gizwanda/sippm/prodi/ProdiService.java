package com.gizwanda.sippm.prodi;

import com.gizwanda.sippm.common.exception.AlreadyExistException;
import com.gizwanda.sippm.common.exception.ResourceNotFoundException;
import com.gizwanda.sippm.prodi.model.Prodi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdiService {
    private final ProdiRepository prodiRepository;

    public ProdiService(ProdiRepository prodiRepository) {
        this.prodiRepository = prodiRepository;
    }

    List<Prodi> fetchAll() {
        return prodiRepository.findAll();
    }

    Prodi fetchById(int prodiId) {
        return prodiRepository.findById(prodiId).orElseThrow(() -> new ResourceNotFoundException("Prodi not found with id: " + prodiId));
    }

    ResponseEntity<Prodi> create(Prodi prodi) {
        if(prodiRepository.existsByNama(prodi.getNama())) {
            throw new AlreadyExistException("Prodi with the current name already exists");
        }
        Prodi savedProdi = prodiRepository.save(prodi);
        return new ResponseEntity<>(savedProdi, HttpStatus.CREATED);
    }
}
