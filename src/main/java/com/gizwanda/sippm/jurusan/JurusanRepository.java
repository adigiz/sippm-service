package com.gizwanda.sippm.jurusan;

import com.gizwanda.sippm.jurusan.model.Jurusan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JurusanRepository extends JpaRepository<Jurusan, Integer> {
    boolean existsByNama(String nama);
}
