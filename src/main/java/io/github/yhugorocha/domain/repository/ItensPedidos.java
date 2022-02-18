package io.github.yhugorocha.domain.repository;

import io.github.yhugorocha.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedidos extends JpaRepository<ItemPedido,Integer> {
}
