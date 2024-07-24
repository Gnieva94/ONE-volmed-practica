package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
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
        //return medicoRepository.findAll(pageable).map(DTOGetMedico::new);
        return medicoRepository.findByActivoTrue(pageable).map(DTOGetMedico::new);
    }

    @PutMapping
    @Transactional
    public void putMedico(@RequestBody @Valid DTOPutMedico request){
        Medico medico = medicoRepository.getReferenceById(request.id());
        medico.updateData(request);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.unactive();
    }

    //DELETE FISICO
    /*@DeleteMapping("/{id}")
    @Transactional
    public void deleteMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }*/

}
