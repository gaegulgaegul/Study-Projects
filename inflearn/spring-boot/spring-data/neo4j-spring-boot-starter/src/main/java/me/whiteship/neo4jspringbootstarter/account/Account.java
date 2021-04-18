package me.whiteship.neo4jspringbootstarter.account;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String email;

    @Relationship(type = "has")
    private Set<Role> roles = new HashSet<>();

}
