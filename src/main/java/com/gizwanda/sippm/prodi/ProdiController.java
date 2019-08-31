package com.gizwanda.sippm.prodi;

import com.gizwanda.sippm.prodi.model.Prodi;
import com.gizwanda.sippm.prodi.model.ProdiDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProdiController {
    private final ProdiService prodiService;

    public ProdiController(ProdiService prodiService) {
        this.prodiService = prodiService;
    }

    @PostMapping("/prodis")
    public ResponseEntity<ProdiDTO> createProdi(@Valid @RequestBody ProdiDTO prodi) {
        return prodiService.create(prodi);
    }

    @GetMapping("/prodis")
    public List<Prodi> fetchAllProdi() {
        return prodiService.fetchAll();
    }

    @GetMapping("/prodis/{id}")
    public Prodi fetchProdiById(@PathVariable int id) {
        return prodiService.fetchById(id);
    }
}
