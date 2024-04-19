package matteofurgani.u5w3d5.services;

import matteofurgani.u5w3d5.entities.Utente;
import matteofurgani.u5w3d5.exceptions.BadRequestException;
import matteofurgani.u5w3d5.exceptions.NotFoundException;
import matteofurgani.u5w3d5.payloads.utente.NewUtenteDTO;
import matteofurgani.u5w3d5.repositories.UtenteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UtenteService {
    @Autowired
    private UtenteDAO utenteDAO;

    public Utente save(NewUtenteDTO body) throws IOException{
        utenteDAO.findByEmail(body.email()).ifPresent(
                utente -> {
                    throw new BadRequestException("L'email " + body.email() + " è già in uso!");
                }
        );
        Utente utente = new Utente(body.nome(), body.cognome(), body.email(), body.password());

        return utenteDAO.save(utente);
    }

    public Page<Utente> getUtente(int page, int size, String sort){
        if(size > 100) size =100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return utenteDAO.findAll(pageable);
    }

    public Utente findById(int id){
        return utenteDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

    public Utente findByIdAndUpdate(int id, Utente body){

        Utente found = this.findById(id);
        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setEmail(body.getEmail());
        found.setPassword(body.getPassword());

        return utenteDAO.save(found);
    }

    public void findByIDAndDelete(int id) {
        Utente found = this.findById(id);
        utenteDAO.delete(found);
    }

    public Utente findByEmail( String email){
        return utenteDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Il Dipendente cono email " + email + " non è stato trovato!"));
    }
}
