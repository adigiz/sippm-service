package com.gizwanda.sippm.pengumuman;

import com.gizwanda.sippm.pengumuman.model.Pengumuman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PengumumanRepository extends JpaRepository<Pengumuman, Integer> {

}
