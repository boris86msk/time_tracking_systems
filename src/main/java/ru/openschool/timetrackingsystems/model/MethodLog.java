package ru.openschool.timetrackingsystems.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "method_log")
@NoArgsConstructor
public class MethodLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "method_name")
    private String name;
    @Column(name = "executed")
    private LocalDateTime executed;
    @Column(name = "execution_nano")
    private long nanoSecond;
    @Column(name = "execution_millis")
    private long milliSecond;
    @Column(name = "asynchronous")
    private boolean asynchronous;
}
