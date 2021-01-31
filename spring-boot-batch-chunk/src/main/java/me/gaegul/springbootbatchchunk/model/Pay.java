package me.gaegul.springbootbatchchunk.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pay {

    @Id
    private Long id;
    private Long amount;

    public Pay(Long id, Long amount) {
        this.id = id;
        this.amount = amount;
    }
}
