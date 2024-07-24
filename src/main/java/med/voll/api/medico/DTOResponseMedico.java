package med.voll.api.medico;

import med.voll.api.direccion.DTODireccion;

public record DTOResponseMedico(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento,
        DTODireccion direccion
) {
    public DTOResponseMedico(Medico medico){
        this(medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getDocumento(),
                new DTODireccion(medico.getDireccion()));
    }
}
