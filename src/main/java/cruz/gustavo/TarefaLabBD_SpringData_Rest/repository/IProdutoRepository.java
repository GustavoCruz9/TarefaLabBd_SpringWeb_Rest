package cruz.gustavo.TarefaLabBD_SpringData_Rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cruz.gustavo.TarefaLabBD_SpringData_Rest.model.entity.Produto;

public interface IProdutoRepository extends JpaRepository<Produto, Integer>{
	Integer fn_contarProdutosComEstoqueAbaixo(int valor);
//	List<Produto> fn_abaixoEstoque(int valor);
}
