package io.github.yhugorocha;

import io.github.yhugorocha.domain.entity.Cliente;
import io.github.yhugorocha.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {

            System.out.println("CRIANDO E SALVANDO");
            //SALVANDO
            clientes.Salvar(new Cliente("Hugo"));
            clientes.Salvar(new Cliente("Igor"));

            //LISTANDO
            clientes.obterTodos().forEach(System.out::println);

            System.out.println("ATUALIZANDO");
            //ATUALIZANDO
            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(c -> {
                c.setNome(c.getNome()+ " ATUALIZADO");
                clientes.atualizar(c);
            });
            //LISTANDO
            clientes.obterTodos().forEach(System.out::println);

            System.out.println("BUSCANDO POR NOME");
            //PROCURANDO POR NOME
            List<Cliente> clientesPorNome = clientes.BuscarPorNome("ug");
            //LISTANDO
            clientesPorNome.forEach(System.out::println);

            System.out.println("DELETANDO");
            //DELETANDO
            todosClientes.forEach(c -> {
                clientes.deletar(c);
            });

            //LISTANDO
            todosClientes = clientes.obterTodos();
            if(todosClientes.isEmpty()){
                System.out.println("Nenhum Cliente Encontrado");
            }else{
                todosClientes.forEach(System.out::println);
            }





        };
    }

    public static void main(String[] args) {

        SpringApplication.run(VendasApplication.class,args);
    }
}
