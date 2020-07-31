package ar.com.ada.api.magnetolab.repos;
import ar.com.ada.api.magnetolab.entities.*;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface MuestraRepo extends JpaRepository<Muestra, Integer> {
    
   @Query("select m from Muestra m where m.categoria=?1")
   List<Muestra> findAllByCategoria(int cat);

   
    
   @Query("select count(categoria) from Muestra m where m.categoria=?1")
   int contar(int cat);

   
}