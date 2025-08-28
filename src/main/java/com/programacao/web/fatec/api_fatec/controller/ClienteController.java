package com.programacao.web.fatec.api_fatec.controller;

import org.springframework.web.bind.annotation.RestController;

import com.programacao.web.fatec.api_fatec.domain.cliente.ClienteRepository;
import com.programacao.web.fatec.api_fatec.entities.Cliente;

import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    
    private final List<Cliente> listaDeCliente = new ArrayList<>();

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteController() {
 
        //forma1
        listaDeCliente.add(new Cliente(1L, "Danilo", "rua xxx"));


        //forma2
        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Joao");
        cliente2.setEndereco("Rua yyyy");
        listaDeCliente.add(cliente2);
    }

    @PostConstruct()
    public void dadosIniciais() {
        clienteRepository.save(new Cliente(null, "Danilo", "rua xxx"));
        clienteRepository.save(new Cliente(null, "Danilo2", "rua xxx"));
        clienteRepository.save(new Cliente(null, "Danilo3", "rua xxx"));
    }

    @GetMapping("/listarClientes")
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
    

    @GetMapping("/testeCliente1") //-> /api/clientes/testeCliente1
    public String testeCliente() {
        return "Teste Client";
    }

    @GetMapping("/testeCliente2/{nome}") //-> /api/clientes/testeCliente2/
    public String testeCliente2(@PathVariable String nome) {
        return nome;
    }

    @PostMapping("")
    public Cliente createCliente(@RequestBody Cliente cliente) {

        listaDeCliente.add(cliente);

        return cliente;
    }

    @DeleteMapping("/{id}")
    public String deletarCliente(@PathVariable Long id) {
        
        for (Cliente cliente: listaDeCliente) {
                if (cliente.getId() == id) {
                    listaDeCliente.remove(cliente);
                    return "OK";
                }
        }

        return "NÃO ENCONTRADO ID:"+id;
    }

    @PutMapping("/{id}")
    public String alterarCliente(@PathVariable Long id, @RequestBody Cliente entity) {
        
         for (Cliente cliente: listaDeCliente) {
                if (cliente.getId() == id) {
                    cliente.setId(id);
                    cliente.setNome(entity.getNome());
                    return "ENCONTROU";
                }
        }

        return "NÃO ENCONTRADO ID:"+id;    
    }
    
}
