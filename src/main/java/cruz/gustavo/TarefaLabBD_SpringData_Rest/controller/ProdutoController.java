package cruz.gustavo.TarefaLabBD_SpringData_Rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cruz.gustavo.TarefaLabBD_SpringData_Rest.model.dto.ProdutoDTO;
import cruz.gustavo.TarefaLabBD_SpringData_Rest.model.entity.Produto;
import cruz.gustavo.TarefaLabBD_SpringData_Rest.repository.IProdutoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProdutoController implements ICRUDController<ProdutoDTO> {

	@Autowired
	IProdutoRepository pRep;

	@Override
	@GetMapping("/produto")
	public List<ProdutoDTO> listar() {
		List<Produto> produtos = pRep.findAll();
		List<ProdutoDTO> produtosDTO = convListProdToProdDTO(produtos);
		return produtosDTO;
	}

	@Override
	@GetMapping("/produto/{codigo}")
	public ResponseEntity<ProdutoDTO> busca(@PathVariable(value = "codigo") int cod) {
		Produto p = pRep.findById(cod).orElseThrow(() -> new ResourceNotFoundException("Código inválido"));
		ProdutoDTO pDTO = convProfToProfDTO(p);
		return ResponseEntity.ok().body(pDTO);
	}

	@Override
	@PostMapping("/produto")
	public ResponseEntity<String> adicionar(@Valid @RequestBody ProdutoDTO pDTO) {
		Produto p = convProdDTOToProd(pDTO);
		pRep.save(p);
		String saida = "Produto adicionado com sucesso";
		return ResponseEntity.ok().body(saida);
	}

	@Override
	@PutMapping("/produto")
	public ResponseEntity<String> atualiza(@Valid @RequestBody ProdutoDTO pDTO) {
		Produto p = convProdDTOToProd(pDTO);
		pRep.save(p);
		String saida = "Produto atualizado com sucesso";
		return ResponseEntity.ok().body(saida);
	}

	@Override
	@DeleteMapping("/produto")
	public ResponseEntity<String> exclui(@Valid @RequestBody ProdutoDTO pDTO) {
		Produto p = convProdDTOToProd(pDTO);
		pRep.delete(p);
		String saida = "Produto excluido com sucesso";
		return ResponseEntity.ok().body(saida);
	}

	@GetMapping("/produto/qtdAbaixoEstoque/{valor}")
	public ResponseEntity<String> contarProdutosComEstoqueAbaixo(@PathVariable(value = "valor") int valor) {
		int qtd = pRep.fn_contarProdutosComEstoqueAbaixo(valor);
		String saida = "Existem " + qtd + " produtos com estoque a baixo de " + valor;
		return ResponseEntity.ok().body(saida);
	}

//	@GetMapping("/produto/abaixoEstoque/{valor}")
//	public List<ProdutoDTO> produtosComEstoqueAbaixo(@PathVariable(value = "valor") int valor) {
//		List<Produto> produtos = pRep.fn_abaixoEstoque(valor);
//		List<ProdutoDTO> produtosDTO = convListProdToProdDTO(produtos);
//		return produtosDTO;
//	}

	private List<ProdutoDTO> convListProdToProdDTO(List<Produto> produtos) {
		List<ProdutoDTO> produtosDTO = new ArrayList<>();
		for (Produto p : produtos) {
			ProdutoDTO pDTO = new ProdutoDTO();
			pDTO.setCodigo(p.getCodigo());
			pDTO.setNome(p.getNome());
			pDTO.setQtdEstoque(p.getQtdEstoque());
			if (p.getValorUnitario() != 0.0) {
				pDTO.setValorUnitario(p.getValorUnitario());
			} else {
				pDTO.setValorUnitario(-1.0);
			}
			produtosDTO.add(pDTO);
		}
		return produtosDTO;
	}

	private ProdutoDTO convProfToProfDTO(Produto p) {
		ProdutoDTO pDTO = new ProdutoDTO();
		pDTO.setCodigo(p.getCodigo());
		pDTO.setNome(p.getNome());
		pDTO.setQtdEstoque(p.getQtdEstoque());
		pDTO.setValorUnitario(p.getValorUnitario());

		return pDTO;
	}

	private Produto convProdDTOToProd(ProdutoDTO pDTO) {
		Produto p = new Produto();
		p.setCodigo(pDTO.getCodigo());
		p.setNome(pDTO.getNome());
		p.setQtdEstoque(pDTO.getQtdEstoque());
		p.setValorUnitario(pDTO.getValorUnitario());
		return p;
	}
}
