package org.notification.module.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "mobie_no", nullable = false)
    private BigInteger mobileNo;

    @Column(name = "email_address", nullable = false)
    private String email;

    @Column(name = "is_alert_subscription_taken", nullable = false)
    private boolean isAlertSubscriptionTaken;

    /*
    should be handled with proper password encoder and not be stored as a plain string
     */
//    String password;
}


