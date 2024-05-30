package cruz.gustavo.TarefaLabBD_SpringData_Rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ICRUDController<T> {

	public List<T> listar();
	public ResponseEntity<T> busca(int cod);
	public ResponseEntity<String> adicionar(T t);
	public ResponseEntity<String> atualiza(T t);
	public ResponseEntity<String> exclui(T t);
	
}
