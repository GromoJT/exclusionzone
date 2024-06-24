package pl.gromotj.exclusionzone.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Indexed;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="email_verification_tokens")
public class EmailConfirmationToken {
    private static  final int EXPIRATION = 60 * 24;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false,unique = true)
    private Long id;
    @NotBlank
    @Column(name = "token",nullable = false,unique = true)
    private String token;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp timeStamp;


    @Column(name="expire_at",columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Basic(optional = false)
    private ZonedDateTime expireAt;

    @ManyToOne
    @JoinColumn(name = "zone_user_id")
    private ZoneUser zoneUser;

    @Transient
    private boolean isExpired;



    private ZonedDateTime calculateExpiryDate(int expiryTimeInMinutes){
        return ZonedDateTime.now().plusMinutes(expiryTimeInMinutes);
    }

}
