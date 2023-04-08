package es.upm.dit.isst.medconapi.model;

import java.time.Duration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //para indicar si el paciente está esperando, en consulta o finalizado. 
    private int status;
    private Duration duracion;
    private int salaEspera;
    private int salaConsulta;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;  
    @OneToOne
    @JoinColumn(name = "cita_id")
    private Cita cita;

    public Consulta() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Duration getDuracion() {
        return duracion;
    }

    public void setDuracion(Duration duracion) {
        this.duracion = duracion;
    }

    public int getSalaEspera() {
        return salaEspera;
    }

    public void setSalaEspera(int salaEspera) {
        this.salaEspera = salaEspera;
    }

    public int getSalaConsulta() {
        return salaConsulta;
    }

    public void setSalaConsulta(int salaConsulta) {
        this.salaConsulta = salaConsulta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + status;
        result = prime * result + ((duracion == null) ? 0 : duracion.hashCode());
        result = prime * result + salaEspera;
        result = prime * result + salaConsulta;
        result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
        result = prime * result + ((medico == null) ? 0 : medico.hashCode());
        result = prime * result + ((cita == null) ? 0 : cita.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Consulta other = (Consulta) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (status != other.status)
            return false;
        if (duracion == null) {
            if (other.duracion != null)
                return false;
        } else if (!duracion.equals(other.duracion))
            return false;
        if (salaEspera != other.salaEspera)
            return false;
        if (salaConsulta != other.salaConsulta)
            return false;
        if (paciente == null) {
            if (other.paciente != null)
                return false;
        } else if (!paciente.equals(other.paciente))
            return false;
        if (medico == null) {
            if (other.medico != null)
                return false;
        } else if (!medico.equals(other.medico))
            return false;
        if (cita == null) {
            if (other.cita != null)
                return false;
        } else if (!cita.equals(other.cita))
            return false;
        return true;
    }
 
}
