package br.com.gamesranking.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gamesranking.api.model.Jogador;
import br.com.gamesranking.api.service.JogadorService;

@RestController
@RequestMapping("/jogadores")
public class JogadorResource {
	
	@Autowired
	private JogadorService jogadorService;

	@GetMapping
    public ResponseEntity<List<Jogador>> listar(){
        List<Jogador> listaJogadores = jogadorService.listar();
        return new ResponseEntity<>(listaJogadores,HttpStatus.OK);
    }
	
	@PostMapping("/salvar")
    public ResponseEntity<Jogador> salvar(@Valid @RequestBody Jogador jogador, HttpServletResponse response) throws Exception {
        Jogador jogadorSalvo = jogadorService.salvar(jogador);
        return new ResponseEntity<>(jogadorSalvo, HttpStatus.CREATED);
    }
	
	@PutMapping("/atualizar")
    public ResponseEntity<Jogador> atualizar(@Valid @RequestBody Jogador jogador, HttpServletResponse response) throws Exception {
		Jogador jogadorAtualizado = jogadorService.atualizar(jogador);
        return new ResponseEntity<>(jogadorAtualizado, HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
		jogadorService.excluir(id);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Jogador> buscarPorId(@PathVariable("id") Long id) {
        Jogador Jogador = jogadorService.carregarJogador(id).get();
        return new ResponseEntity<>(Jogador, HttpStatus.OK);
    }
	
	 @ExceptionHandler({ValidationException.class})
	    public ResponseEntity<Error> handleValidationException(ValidationException e) {
	        return new ResponseEntity<>(new Error(e.getMessage()), HttpStatus.BAD_REQUEST);
	    }
}
