package com.gizwanda.sippm.pengumuman;

import com.gizwanda.sippm.common.exception.ResourceNotFoundException;
import com.gizwanda.sippm.pengumuman.model.Pengumuman;
import com.gizwanda.sippm.pengumuman.model.PengumumanDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PengumumanService {
    private final PengumumanRepository pengumumanRepository;

    public PengumumanService(PengumumanRepository pengumumanRepository) {
        this.pengumumanRepository = pengumumanRepository;
    }

    public List<Pengumuman> fetchAll() {
        return pengumumanRepository.findAll();
    }

    public Pengumuman fetchById(int id) {
        return pengumumanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pengumuman not found with id: " + id));
    }

    public ResponseEntity<PengumumanDTO> create(PengumumanDTO pengumumanDTO) {
        Pengumuman pengumuman = new Pengumuman(pengumumanDTO);
        Pengumuman savedPengumuman = pengumumanRepository.save(pengumuman);
        PengumumanDTO returnedPengumuman = new PengumumanDTO(savedPengumuman);

        return new ResponseEntity<>(returnedPengumuman, HttpStatus.CREATED);
    }
}
