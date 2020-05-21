package org.notification.module.entities;

import org.notification.module.enums.LogType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_for_log", indexes = {@Index(columnList = "log_type", name = "log_type_index"),
        @Index(columnList = "user_id", name = "user_id_index")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserForLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "log_type", nullable = false)
    private LogType logType;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "log_id", nullable = false)
    private int logId;


}
