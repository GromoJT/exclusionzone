package pl.gromotj.exclusionzone.entity.Anomaly;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import pl.gromotj.exclusionzone.entity.ZoneUser;

import java.time.ZonedDateTime;
import java.util.List;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "anomalies")
public class AnomalyModel {
    @Id
    @GeneratedValue(generator = "custom-uuid")
    @GenericGenerator(name="custom-uuid",
            strategy = "pl.gromotj.exclusionzone.functions.CustomUUIDGenerator",
            parameters = @org.hibernate.annotations.Parameter(name="prefix",value = "anom"))
    @Column(name = "id",updatable = false,nullable = false,unique = true)
    private String id;

    @ManyToOne
    @JoinColumn(name="author")
    private ZoneUser author;


    @NotBlank
    @Size(min = 2,max = 40)
    @Column(name = "anomaly_name",unique = true,nullable = false)
    private String anomalyName;

    @NotBlank
    @Size(min = 1,max = 280)
    @Column(name = "short_anomaly_description",nullable = false)
    private String shortAnomalyDescription;


    @Column(name = "anomaly_image_list",nullable = true)
    private List<String> anomalyImagesList;


    @Column(name = "is_redacted",columnDefinition = "boolean default false")
    private boolean isRedacted = false;

    @CreationTimestamp
    @Column(name="created_at",updatable = false,columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime createdAt;

    @CreationTimestamp
    @Column(name="modified_at",updatable = true,columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime modifiedAt;
}
