package com.worklink.todosimple.cadastro.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worklink.todosimple.cadastro.models.Usuario;
import com.worklink.todosimple.cadastro.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @Transactional
    public Usuario create(Usuario usuario) {
        usuario.setId(null);
        return usuarioRepository.saveAndFlush(usuario);
    }

    @Transactional
    public Usuario update(Usuario usuario) {
        Usuario existing = findById(usuario.getId());
        existing.setEmail(usuario.getEmail());
        existing.setSenha(usuario.getSenha());
        existing.setTipo(usuario.getTipo());
        return usuarioRepository.save(existing);
    }

    @Transactional
    public Usuario updateTipo(Usuario usuario) {
        Usuario existing = findById(usuario.getId());
        existing.setTipo(usuario.getTipo());
        return usuarioRepository.save(existing);
    }

    public void delete(Integer id) {
        Usuario usuario = findById(id);
        try {
            usuarioRepository.deleteById(usuario.getId());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar: pode haver vínculos com outras entidades");
        }
    }

    public Usuario findByEmail(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        return usuario.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}