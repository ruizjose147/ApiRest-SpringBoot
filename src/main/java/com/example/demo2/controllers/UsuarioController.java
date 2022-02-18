package com.example.demo2.controllers;

import com.example.demo2.models.UsuarioModel;
import com.example.demo2.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioService.guardarUsuario(usuario);
    }

    @GetMapping(path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") long id){
        return this.usuarioService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public ResponseEntity<ArrayList<UsuarioModel>> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") int prioridad){
            ArrayList<UsuarioModel> usuarioPrioridad = this.usuarioService.obtenerPorPrioridad(prioridad);
            if(usuarioPrioridad.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(usuarioPrioridad, HttpStatus.OK);
    }

    @GetMapping("/querynombre")
    public ResponseEntity<ArrayList<UsuarioModel>> obtenerUsuarioPorNombre(@RequestParam("nombre") String nombre){
        try {
            ArrayList<UsuarioModel> usuarioEncontrado = this.usuarioService.obtenerPorNombre(nombre);
            if(usuarioEncontrado.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(usuarioEncontrado, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/queryemail")
    public ResponseEntity<ArrayList<UsuarioModel>> obtenerUsuarioPorEmail(@RequestParam("email") String email){
        try {
            ArrayList<UsuarioModel> usuarioEncontrado = this.usuarioService.obtenerPorEmail(email);
            if(usuarioEncontrado.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(usuarioEncontrado, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") long id){
        boolean ok = this.usuarioService.eliminarUsuario(id);
        if(ok){
            return "Se elimino el usuario con id " + id;
        }else {
            return "No se pudo eliminar el usuario con id" + id;
        }
    }
}
