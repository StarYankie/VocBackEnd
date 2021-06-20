package kp.staryankie.servicedesk.voc.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Response {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "VOC_ID")
    int vocid;

    @Column
    @NotNull
    String mngid;

    @Column
    @NotNull
    String content;

    @Column
    @CreationTimestamp
    LocalDateTime crdt;
}
