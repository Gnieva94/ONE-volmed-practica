package med.voll.api.medico;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;

@Entity
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean activo;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DTOPostMedico request) {
        this.activo = true;
        this.nombre = request.nombre();
        this.email = request.email();
        this.telefono = request.telefono();
        this.documento = request.documento();
        this.especialidad = request.especialidad();
        this.direccion = new Direccion(request.direccion());
    }

    public void updateData(DTOPutMedico request){
        if(request.nombre() != null)
            this.nombre = request.nombre();
        if(request.documento() != null)
            this.documento = request.documento();
        if(request.direccion() != null)
            this.direccion = direccion.updateData(request.direccion());
    }

    public void unactive() {
        this.activo = false;
    }
}
