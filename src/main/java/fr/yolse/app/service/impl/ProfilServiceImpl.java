package fr.yolse.app.service.impl;

import fr.yolse.app.service.ProfilService;
import fr.yolse.app.domain.Profil;
import fr.yolse.app.repository.ProfilRepository;
import fr.yolse.app.service.dto.ProfilDTO;
import fr.yolse.app.service.mapper.ProfilMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Profil.
 */
@Service
@Transactional
public class ProfilServiceImpl implements ProfilService {

    private final Logger log = LoggerFactory.getLogger(ProfilServiceImpl.class);

    private final ProfilRepository profilRepository;

    private final ProfilMapper profilMapper;

    public ProfilServiceImpl(ProfilRepository profilRepository, ProfilMapper profilMapper) {
        this.profilRepository = profilRepository;
        this.profilMapper = profilMapper;
    }

    /**
     * Save a profil.
     *
     * @param profilDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProfilDTO save(ProfilDTO profilDTO) {
        log.debug("Request to save Profil : {}", profilDTO);
        Profil profil = profilMapper.toEntity(profilDTO);
        profil = profilRepository.save(profil);
        return profilMapper.toDto(profil);
    }

    /**
     * Get all the profils.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProfilDTO> findAll() {
        log.debug("Request to get all Profils");
        return profilRepository.findAll().stream()
            .map(profilMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one profil by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProfilDTO> findOne(Long id) {
        log.debug("Request to get Profil : {}", id);
        return profilRepository.findById(id)
            .map(profilMapper::toDto);
    }

    /**
     * Delete the profil by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Profil : {}", id);
        profilRepository.deleteById(id);
    }
}
