package fr.yolse.app.service.impl;

import fr.yolse.app.service.UtiProfilService;
import fr.yolse.app.domain.UtiProfil;
import fr.yolse.app.repository.UtiProfilRepository;
import fr.yolse.app.service.dto.UtiProfilDTO;
import fr.yolse.app.service.mapper.UtiProfilMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing UtiProfil.
 */
@Service
@Transactional
public class UtiProfilServiceImpl implements UtiProfilService {

    private final Logger log = LoggerFactory.getLogger(UtiProfilServiceImpl.class);

    private final UtiProfilRepository utiProfilRepository;

    private final UtiProfilMapper utiProfilMapper;

    public UtiProfilServiceImpl(UtiProfilRepository utiProfilRepository, UtiProfilMapper utiProfilMapper) {
        this.utiProfilRepository = utiProfilRepository;
        this.utiProfilMapper = utiProfilMapper;
    }

    /**
     * Save a utiProfil.
     *
     * @param utiProfilDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UtiProfilDTO save(UtiProfilDTO utiProfilDTO) {
        log.debug("Request to save UtiProfil : {}", utiProfilDTO);
        UtiProfil utiProfil = utiProfilMapper.toEntity(utiProfilDTO);
        utiProfil = utiProfilRepository.save(utiProfil);
        return utiProfilMapper.toDto(utiProfil);
    }

    /**
     * Get all the utiProfils.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<UtiProfilDTO> findAll() {
        log.debug("Request to get all UtiProfils");
        return utiProfilRepository.findAll().stream()
            .map(utiProfilMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one utiProfil by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UtiProfilDTO> findOne(Long id) {
        log.debug("Request to get UtiProfil : {}", id);
        return utiProfilRepository.findById(id)
            .map(utiProfilMapper::toDto);
    }

    /**
     * Delete the utiProfil by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UtiProfil : {}", id);
        utiProfilRepository.deleteById(id);
    }
}
