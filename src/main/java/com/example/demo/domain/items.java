package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ItemName;
    private long price;
    private long stock;
    private LocalDateTime EnrolledTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"enrolleditems"})
    private Users user;

}
