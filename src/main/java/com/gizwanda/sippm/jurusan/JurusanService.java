package com.gizwanda.sippm.jurusan;

import com.gizwanda.sippm.common.exception.AlreadyExistException;
import com.gizwanda.sippm.common.exception.ResourceNotFoundException;
import com.gizwanda.sippm.jurusan.model.Jurusan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JurusanService {

    private final JurusanRepository jurusanRepository;

    public JurusanService(JurusanRepository jurusanRepository) {
        this.jurusanRepository = jurusanRepository;
    }

    ResponseEntity<Jurusan> create(Jurusan jurusan) {
        String namaJurusan = jurusan.getNamaJurusan();
        if(jurusanRepository.existsByNamaJurusan(namaJurusan)){
            throw new AlreadyExistException("Jurusan with the current name already exists");
        }
        Jurusan savedJurusan = jurusanRepository.save(jurusan);
        return new ResponseEntity<>(savedJurusan, HttpStatus.CREATED);
    }

    List<Jurusan> fetchAll() {
        return jurusanRepository.findAll();
    }

    Jurusan fetchById(int jurusanId) {
        return jurusanRepository.findById(jurusanId).orElseThrow(() -> new ResourceNotFoundException("Jurusan not found with id: " + jurusanId));
    }
}
