package com.tienda.service;

import com.tienda.domain.Categoria;
import com.tienda.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

    //Se usa para crear automaticamente una unica instancia de esta clase.
    @Autowired
    private CategoriaRepository categoriaRepository;

    //Se usa para indicar que se hare una transaccion a una base de datos de solo lectura 
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activos) {
        // Se usa "activos si se desea limitar la vista a solo las categorias activas
        var lista = categoriaRepository.findAll();

        if (activos) { //Si solo se quieren los registros de categorias activas 
            lista.removeIf(e -> !e.isActivo());
        }

        return lista;
    }

    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaRepository.findById(categoria.getIdCategoria())
                .orElse(null);
    }

    
    @Transactional
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

  
    @Transactional
    public void delete(Categoria categoria) {
        categoriaRepository.delete(categoria);

    }

}
