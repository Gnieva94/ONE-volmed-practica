package med.voll.api.medico;

public record DTOGetMedico(Long id,String nombre, String especialidad, String documento, String email) {
    public DTOGetMedico(Medico medico){
        this(medico.getId(),medico.getNombre(), String.valueOf(medico.getEspecialidad()),medico.getDocumento(),medico.getEmail());
    }
}
