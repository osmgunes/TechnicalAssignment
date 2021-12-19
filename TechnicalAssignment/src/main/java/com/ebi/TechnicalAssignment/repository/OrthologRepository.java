package com.ebi.TechnicalAssignment.repository;

import com.ebi.TechnicalAssignment.model.ortholog.Ortholog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OrthologRepository extends JpaRepository<Ortholog, Long> {
    List<Ortholog> findByMouseGeneSymbol(String symbol);

    List<Ortholog> findByMouseGeneMgiGeneAccId(String symbol);

}
