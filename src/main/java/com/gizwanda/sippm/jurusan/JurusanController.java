package com.gizwanda.sippm.jurusan;

import com.gizwanda.sippm.jurusan.model.Jurusan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class JurusanController {

    private final JurusanService jurusanService;

    public JurusanController(JurusanService jurusanService) {
        this.jurusanService = jurusanService;
    }

    @GetMapping("/jurusans")
    public List<Jurusan> fetchAllJurusan() {
        return jurusanService.fetchAll();
    }

    @PostMapping("/jurusans")
    public ResponseEntity<Jurusan> createJurusan(@Valid @RequestBody Jurusan jurusan) {
        return jurusanService.create(jurusan);
    }

    @GetMapping("/jurusans/{id}")
    public Jurusan fetchJurusanById(@PathVariable(value = "id") int jurusanId) {
        return jurusanService.fetchById(jurusanId);
    }
}
