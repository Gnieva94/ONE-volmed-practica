package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DTOGetMedico;
import med.voll.api.medico.DTOPostMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public void postMedico(@RequestBody @Valid DTOPostMedico request){
        medicoRepository.save(new Medico(request));
    }

    @GetMapping
    /*
    size = numero(0,*) -> numero de elementos de la paginacion
    page = numero(0,*) -> numero de la pagina a mostrar
    sort = atributo [opcional = , desc] -> ordena de forma asc segun el atributo. atributo,desc para forma desc
    */
    public Page<DTOGetMedico> getMedicos(/*@PageableDefault(size,page,sort)*/Pageable pageable){
        return medicoRepository.findAll(pageable).map(DTOGetMedico::new);
    }

}
