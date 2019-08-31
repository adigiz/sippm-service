package com.gizwanda.sippm.prodi;

import com.gizwanda.sippm.prodi.model.Prodi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdiRepository extends JpaRepository<Prodi, Integer> {
    boolean existsByNama(String nama);
}
