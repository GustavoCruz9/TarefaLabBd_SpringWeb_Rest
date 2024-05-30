package cruz.gustavo.TarefaLabBD_SpringData_Rest.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Produto")
@NamedNativeQuery(
		name = "Produto.fn_contarProdutosComEstoqueAbaixo",
		query = "Select dbo.fn_contarProdutosComEstoqueAbaixo(?1)",
		resultClass = Integer.class
)
//@NamedNativeQuery(
//		name = "Produto.fn_abaixoEstoque",
//		query = "SELECT * FROM fn_abaixoEstoque(?1)",
//		resultClass = Produto.class
//		)
public class Produto {
	
	@Id
	@Column(name = "codigo" ,nullable = false)
	private int codigo;
	
	@Column(name = "nome" , length = 100, nullable = false)
	private String nome;
	
	@Column(name = "valorUnitario" ,nullable = false)
	private double valorUnitario ;
	
	@Column(name = "qtdEstoque" ,nullable = false)
	private int qtdEstoque ;

}
