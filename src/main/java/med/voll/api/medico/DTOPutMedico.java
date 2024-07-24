package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.direccion.DTODireccion;

public record DTOPutMedico(
        @NotNull
        Long id,
        String nombre,
        String documento,
        DTODireccion direccion) {
}
