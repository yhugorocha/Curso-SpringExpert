package io.github.yhugorocha.domain.repository;

import io.github.yhugorocha.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer> {

    @Query(" select c from Cliente c where c.nome like :nome ")
    List<Cliente> ProcurarNome( @Param("nome") String nome);

    //MESMA CONSULTA MAS AGR EM SQL NATIVO
    //@Query(value = "select * from Cliente c where c.nome like '%:nome%' ",nativeQuery = true)
    //List<Cliente> ProcurarNome( @Param("nome") String nome);



    //update ou delete precisam da tag @Modifying
    @Query(value = "delete from Cliente c where c.nome =:nome")
    @Modifying
    void deleteByNome(@Param("nome") String nome);

    Boolean existsByNome(String nome);


    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id ")
    Cliente findClientesFetchPedidos(@Param("id") Integer id);
}
