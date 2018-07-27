package fr.yolse.app.service.impl;

import fr.yolse.app.service.EngraisService;
import fr.yolse.app.domain.Engrais;
import fr.yolse.app.repository.EngraisRepository;
import fr.yolse.app.service.dto.EngraisDTO;
import fr.yolse.app.service.mapper.EngraisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Engrais.
 */
@Service
@Transactional
public class EngraisServiceImpl implements EngraisService {

    private final Logger log = LoggerFactory.getLogger(EngraisServiceImpl.class);

    private final EngraisRepository engraisRepository;

    private final EngraisMapper engraisMapper;

    public EngraisServiceImpl(EngraisRepository engraisRepository, EngraisMapper engraisMapper) {
        this.engraisRepository = engraisRepository;
        this.engraisMapper = engraisMapper;
    }

    /**
     * Save a engrais.
     *
     * @param engraisDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EngraisDTO save(EngraisDTO engraisDTO) {
        log.debug("Request to save Engrais : {}", engraisDTO);
        Engrais engrais = engraisMapper.toEntity(engraisDTO);
        engrais = engraisRepository.save(engrais);
        return engraisMapper.toDto(engrais);
    }

    /**
     * Get all the engrais.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EngraisDTO> findAll() {
        log.debug("Request to get all Engrais");
        return engraisRepository.findAll().stream()
            .map(engraisMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one engrais by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EngraisDTO> findOne(Long id) {
        log.debug("Request to get Engrais : {}", id);
        return engraisRepository.findById(id)
            .map(engraisMapper::toDto);
    }

    /**
     * Delete the engrais by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Engrais : {}", id);
        engraisRepository.deleteById(id);
    }
}
