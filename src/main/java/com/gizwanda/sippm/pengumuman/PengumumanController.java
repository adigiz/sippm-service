package com.gizwanda.sippm.pengumuman;

import com.gizwanda.sippm.pengumuman.model.Pengumuman;
import com.gizwanda.sippm.pengumuman.model.PengumumanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PengumumanController {
    private final PengumumanService pengumumanService;

    public PengumumanController(PengumumanService pengumumanService) {
        this.pengumumanService = pengumumanService;
    }

    @GetMapping("/pengumumans")
    public List<Pengumuman> fetchAllPengumuman() {
        return pengumumanService.fetchAll();
    }

    @PostMapping("/pengumumans")
    public ResponseEntity<PengumumanDTO> createPengumuman(@Valid @RequestBody PengumumanDTO pengumumanDTO) {
        return pengumumanService.create(pengumumanDTO);
    }

    @GetMapping("/pengumumans/{id}")
    public Pengumuman fetchPengumumanById(@PathVariable int id) {
        return pengumumanService.fetchById(id);
    }
}
