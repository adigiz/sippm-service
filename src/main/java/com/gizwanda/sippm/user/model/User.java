package com.gizwanda.sippm.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gizwanda.sippm.common.model.Constants;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id","createdAt", "updatedAt"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NonNull
    private String nip;

    @Column
    @NonNull
    private String nama;

    @Column
    private String pangkat;

    @Column
    private String jabatan;

    @Column
    private String lab;

    @Column
    private String alamat;

    @Column
    @NonNull
    private String noTelp;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_TIME_FORMAT_WITHOUT_DASH)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_TIME_FORMAT_WITHOUT_DASH)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
}
