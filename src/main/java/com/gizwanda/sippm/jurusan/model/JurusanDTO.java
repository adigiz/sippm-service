package com.gizwanda.sippm.jurusan.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class JurusanDTO {
    @NonNull
    String nama;
}
