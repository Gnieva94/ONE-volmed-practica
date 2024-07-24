package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<DTOResponseMedico> postMedico(@RequestBody @Valid DTOPostMedico request,
                                                        UriComponentsBuilder uriComponentsBuilder){
        Medico medico = medicoRepository.save(new Medico(request));
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(new DTOResponseMedico(medico));
    }

    @GetMapping
    /*
    size = numero(0,*) -> numero de elementos de la paginacion
    page = numero(0,*) -> numero de la pagina a mostrar
    sort = atributo [opcional = , desc] -> ordena de forma asc segun el atributo. atributo,desc para forma desc
    */
    public ResponseEntity<Page<DTOGetMedico>> getMedicos(/*@PageableDefault(size,page,sort)*/Pageable pageable){
        //return medicoRepository.findAll(pageable).map(DTOGetMedico::new);
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(pageable).map(DTOGetMedico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOResponseMedico> getMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DTOResponseMedico(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DTOResponseMedico> putMedico(@RequestBody @Valid DTOPutMedico request){
        Medico medico = medicoRepository.getReferenceById(request.id());
        medico.updateData(request);
        return ResponseEntity.ok(new DTOResponseMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.unactive();
        return ResponseEntity.noContent().build();
    }

    //DELETE FISICO
    /*@DeleteMapping("/{id}")
    @Transactional
    public void deleteMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }*/

}
