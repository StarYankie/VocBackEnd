package kp.staryankie.servicedesk.voc.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Voc {
    @PrePersist
    public void prePersist() {
        // replied 컬럼의 default는 N
        this.replied = this.replied == null ? "N" : this.replied;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    @NotNull
    String userid;

    @Column
    @NotNull
    String title;

    @Column
    @NotNull
    String content;

    @Column
    String mngid;

    @Column
    String replied;

    @Column
    @CreationTimestamp
    LocalDateTime crdt;

    @OneToMany
    @JoinColumn(name = "VOC_ID")
    private List<Response> responses;

}
