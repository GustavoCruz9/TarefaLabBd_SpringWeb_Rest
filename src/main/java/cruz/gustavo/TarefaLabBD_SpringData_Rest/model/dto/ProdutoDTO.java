package cruz.gustavo.TarefaLabBD_SpringData_Rest.model.dto;

import jakarta.persistence.Column;
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
public class ProdutoDTO {

	private int codigo;
	private String nome;
	private double valorUnitario ;
	private int qtdEstoque ;
	
}
