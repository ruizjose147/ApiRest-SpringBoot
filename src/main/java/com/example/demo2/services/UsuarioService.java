package com.example.demo2.services;

import com.example.demo2.models.UsuarioModel;
import com.example.demo2.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> obtenerPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public ArrayList<UsuarioModel> obtenerPorPrioridad(int prioridad){
        return usuarioRepository.findByPrioridad(prioridad);
    }

    public ArrayList<UsuarioModel> obtenerPorNombre(String nombre){
        return usuarioRepository.findByNombre(nombre);
    }

    public boolean eliminarUsuario(long id){
        try {
            usuarioRepository.deleteAllById(Collections.singleton(id));
            return true;
        }catch (Exception err){
            return false;
        }
    }
}
