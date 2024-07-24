package med.voll.api.direccion;

import jakarta.validation.constraints.NotBlank;
public record DTODireccion(
        @NotBlank
        String calle,
        @NotBlank
        String distrito,
        @NotBlank
        String ciudad,
        @NotBlank
        String numero,
        @NotBlank
        String complemento) {
        public DTODireccion(Direccion direccion){
                this(direccion.getCalle(),
                        direccion.getDistrito(),
                        direccion.getCiudad(),
                        direccion.getNumero(),
                        direccion.getComplemento());
        }
}
