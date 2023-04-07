package es.upm.dit.isst.medconapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class TFG {
    @Id
    private String email;
    private String pass;
    private String nombre;
    private String titulo;
    private String tutor;
    private int status;
    @Lob
    private byte[] memoria;
    private double nota;
}
