package br.edu.faculdadedelta.projetospringmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.faculdadedelta.projetospringmvc.modelo.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long> {

}
