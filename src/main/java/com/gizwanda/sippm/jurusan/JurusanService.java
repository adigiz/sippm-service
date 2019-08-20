package com.gizwanda.sippm.jurusan;

import com.gizwanda.sippm.common.exception.AlreadyExistException;
import com.gizwanda.sippm.common.exception.ResourceNotFoundException;
import com.gizwanda.sippm.jurusan.model.Jurusan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JurusanService {

    private final JurusanRepository jurusanRepository;

    public JurusanService(JurusanRepository jurusanRepository) {
        this.jurusanRepository = jurusanRepository;
    }

    Jurusan create(Jurusan jurusan) {
        String namaJurusan = jurusan.getNamaJurusan();
        if(jurusanRepository.existsByNamaJurusan(namaJurusan)){
            throw new AlreadyExistException("Jurusan with the current name already exists");
        }
        return jurusanRepository.save(jurusan);
    }

    List<Jurusan> fetchAll() {
        return jurusanRepository.findAll();
    }

    Jurusan fetchById(int jurusanId) {
        return jurusanRepository.findById(jurusanId).orElseThrow(() -> new ResourceNotFoundException("Jurusan not found with id: " + jurusanId));
    }
}
