package me.gaegul.mybatisjpaloop.pay.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class Pay {

    @Id
    private String id;

    @CreatedDate
    private LocalDate payDate = LocalDate.now();

    private String product;

}
