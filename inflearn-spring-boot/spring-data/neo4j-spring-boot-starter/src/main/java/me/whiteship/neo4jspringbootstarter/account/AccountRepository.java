package me.whiteship.neo4jspringbootstarter.account;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AccountRepository extends Neo4jRepository<Account, Long> {
}
