package org.notification.module.entities;

import org.notification.module.enums.CommunicationMode;
import org.notification.module.enums.LogType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LogBean {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "log_type", nullable = false)
    private LogType logType;
    /*
    duration in secs
     */
    @Column(nullable = false)
    @Min(value = 10)
    private int duration;

    @Column(nullable = false)
    @Min(value = 1)
    private int frequency;
    /*
    wait time defined in secs
     */
    @Column(name = "wait_time", nullable = false)
    @Min(value = 0)
    private int waitTime;

    @ElementCollection(targetClass = CommunicationMode.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "communication_modes", nullable = false)
    private List<CommunicationMode> communicationModes;

}
