package med.voll.api.medico;

public record DTOGetMedico(String nombre, String especialidad, String documento, String email) {
    public DTOGetMedico(Medico medico){
        this(medico.getNombre(), String.valueOf(medico.getEspecialidad()),medico.getDocumento(),medico.getEmail());
    }
}
