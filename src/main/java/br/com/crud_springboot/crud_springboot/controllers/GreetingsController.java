package br.com.crud_springboot.crud_springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crud_springboot.crud_springboot.model.Usuario;
import br.com.crud_springboot.crud_springboot.repository.UsuarioRepository;


@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping(value = "listaTodos")
	public ResponseEntity<List<Usuario>> listaTodos(){
		List<Usuario> usuarios = usuarioRepository.findAll();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@PostMapping(value = "salvar")
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
		Usuario user = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "delete")
	public ResponseEntity<String> delete(@RequestParam Long id){
		usuarioRepository.deleteById(id);
		return new ResponseEntity<String>("Usuario"+" "+id+" "+"deletado com sucesso!", HttpStatus.OK);
	}
	
	@GetMapping(value = "buscarPorId")
	public ResponseEntity<Usuario> buscarPorId(@RequestParam Long id){
		Usuario user = usuarioRepository.findById(id).get();
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}
    
	@PutMapping(value = "atualizar")
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
		if(usuario.getId() == null) {
			return new ResponseEntity<String>("O id do usuário não foi informado!", HttpStatus.OK);
		}
		Usuario user = usuarioRepository.saveAndFlush(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "buscarPorNome")
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam String nome){
		List<Usuario> usuarios = usuarioRepository.buscarPorNome(nome.trim().toUpperCase());
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
}
