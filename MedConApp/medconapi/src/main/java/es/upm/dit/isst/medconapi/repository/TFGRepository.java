package es.upm.dit.isst.medconapi.repository;

import org.springframework.data.repository.CrudRepository;
import es.upm.dit.isst.medconapi.model.TFG;
import java.util.*;

public interface TFGRepository extends CrudRepository<TFG, String> {
     List<TFG> findByTutor(String tutor);
}
