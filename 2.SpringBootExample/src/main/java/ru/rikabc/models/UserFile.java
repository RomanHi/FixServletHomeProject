package ru.rikabc.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

import static javax.persistence.GenerationType.*;

/**
 * @Author Roman Khayrullin on 19.04.2018
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_file")
public class UserFile {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long fileId;
    private Long userId;
    private Date createDate;
    private String type;
    private String fileName;
    private String file;
}
