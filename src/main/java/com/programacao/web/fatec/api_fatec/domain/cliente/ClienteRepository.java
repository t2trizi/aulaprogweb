package com.programacao.web.fatec.api_fatec.domain.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import com.programacao.web.fatec.api_fatec.entities.Cliente;
import java.util.List;


/*extende a classe/banco
<nome_classe, tipo_chave_primaria>
 */

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    List<Cliente> findByNome(String nome);
}
