package com.worklink.todosimple.cadastro.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worklink.todosimple.cadastro.models.Candidato;
import com.worklink.todosimple.cadastro.models.Empresa;
import com.worklink.todosimple.cadastro.repositories.CandidatoRepository;
import com.worklink.todosimple.cadastro.repositories.EmpresaRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    // Métodos para Empresa
    public Empresa findEmpresaById(Integer id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
    }

    public Empresa findEmpresaByEmail(String email) {
        // Supondo que você tenha um repositório EmpresaRepository com o método apropriado
        return empresaRepository.findByEmail(email);
    }

    public Empresa findEmpresaByCnpj(String cnpj) {
        return empresaRepository.findByCnpj(cnpj);
    }

    public Empresa findEmpresaByTelefone(String telefone) {
        return empresaRepository.findByTelefone(telefone);
    }

    public List<Empresa> findAllEmpresas() {
        return empresaRepository.findAll();
    }

    @Transactional
    public Empresa createEmpresa(Empresa empresa) {
        empresa.setId(null);
        return empresaRepository.saveAndFlush(empresa);
    }

    @Transactional
    public Empresa updateEmpresa(Empresa empresa) {
        Empresa existing = findEmpresaById(empresa.getId());
        existing.setNome(empresa.getNome());
        existing.setEmail(empresa.getEmail());
        existing.setSenha(empresa.getSenha());
        existing.setTelefone(empresa.getTelefone());
        existing.setEndereco(empresa.getEndereco());
        existing.setCnpj(empresa.getCnpj());
        existing.setDescricao(empresa.getDescricao());
        return empresaRepository.save(existing);
    }

    public void deleteEmpresa(Integer id) {
        Empresa empresa = findEmpresaById(id);
        try {
            empresaRepository.deleteById(empresa.getId());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar: pode haver vínculos com outras entidades");
        }
    }

    // Métodos para Candidato
    public Candidato findCandidatoById(Integer id) {
        Optional<Candidato> candidato = candidatoRepository.findById(id);
        return candidato.orElseThrow(() -> new RuntimeException("Candidato não encontrado"));
    }

    public List<Candidato> findAllCandidatos() {
        return candidatoRepository.findAll();
    }

    @Transactional
    public Candidato createCandidato(Candidato candidato) {
        System.out.println("Candidato antes de salvar: " + candidato); // Log para verificar o objeto antes de salvar
        return candidatoRepository.saveAndFlush(candidato);
    }

    @Transactional
    public Candidato updateCandidato(Candidato candidato) {
        Candidato existing = findCandidatoById(candidato.getId());
        existing.setNome(candidato.getNome());
        existing.setEmail(candidato.getEmail());
        existing.setSenha(candidato.getSenha());
        existing.setTelefone(candidato.getTelefone());
        existing.setEndereco(candidato.getEndereco());
        existing.setCpf(candidato.getCpf());
        existing.setDataNasc(candidato.getDataNasc());
        existing.setAreaAtuacao(candidato.getAreaAtuacao());
        existing.setSexo(candidato.getSexo());
        existing.setCep(candidato.getCep());
        return candidatoRepository.save(existing);
    }

    public void deleteCandidato(Integer id) {
        Candidato candidato = findCandidatoById(id);
        try {
            candidatoRepository.deleteById(candidato.getId());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar: pode haver vínculos com outras entidades");
        }
    }

    public boolean emailJaExiste(String email) {
        // Verifica em Empresa
        if (empresaRepository.findByEmail(email) != null) {
            return true;
        }
        // Verifica em Candidato
        if (candidatoRepository.findByEmail(email) != null) {
            return true;
        }
        return false;
    }
}