package io.github.yhugorocha.domain.repository;

import io.github.yhugorocha.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    private String INSERT = "insert into cliente (nome) values (?) ";
    private String SELECT_ALL = "select * from cliente";
    private String UPDATE = "update cliente set nome = (?) where id = (?) ";
    private String DELETE = "delete cliente where id = (?) ";
    private String BUSCAR_POR_NOME = "select * from cliente where nome like (?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente Salvar(Cliente cliente){
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public List<Cliente> obterTodos(){
        return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
    }

    private RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }

    public List<Cliente> BuscarPorNome(String nome){
        return jdbcTemplate.query(BUSCAR_POR_NOME,new Object[]{"%" +nome+ "%"},obterClienteMapper());
    }

    public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(UPDATE,new Object[]{cliente.getNome(),cliente.getId()});
        return cliente;
    }

    public void deletar (Cliente cliente){
        deletar(cliente.getId());
    }

    public void deletar (Integer id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }


}
