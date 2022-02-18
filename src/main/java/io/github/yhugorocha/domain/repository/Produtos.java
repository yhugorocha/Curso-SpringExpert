package io.github.yhugorocha.domain.repository;

import io.github.yhugorocha.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}

