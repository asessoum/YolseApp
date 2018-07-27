package fr.yolse.app.service.impl;

import fr.yolse.app.service.BesoinEngraisService;
import fr.yolse.app.domain.BesoinEngrais;
import fr.yolse.app.repository.BesoinEngraisRepository;
import fr.yolse.app.service.dto.BesoinEngraisDTO;
import fr.yolse.app.service.mapper.BesoinEngraisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing BesoinEngrais.
 */
@Service
@Transactional
public class BesoinEngraisServiceImpl implements BesoinEngraisService {

    private final Logger log = LoggerFactory.getLogger(BesoinEngraisServiceImpl.class);

    private final BesoinEngraisRepository besoinEngraisRepository;

    private final BesoinEngraisMapper besoinEngraisMapper;

    public BesoinEngraisServiceImpl(BesoinEngraisRepository besoinEngraisRepository, BesoinEngraisMapper besoinEngraisMapper) {
        this.besoinEngraisRepository = besoinEngraisRepository;
        this.besoinEngraisMapper = besoinEngraisMapper;
    }

    /**
     * Save a besoinEngrais.
     *
     * @param besoinEngraisDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BesoinEngraisDTO save(BesoinEngraisDTO besoinEngraisDTO) {
        log.debug("Request to save BesoinEngrais : {}", besoinEngraisDTO);
        BesoinEngrais besoinEngrais = besoinEngraisMapper.toEntity(besoinEngraisDTO);
        besoinEngrais = besoinEngraisRepository.save(besoinEngrais);
        return besoinEngraisMapper.toDto(besoinEngrais);
    }

    /**
     * Get all the besoinEngrais.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BesoinEngraisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BesoinEngrais");
        return besoinEngraisRepository.findAll(pageable)
            .map(besoinEngraisMapper::toDto);
    }


    /**
     * Get one besoinEngrais by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BesoinEngraisDTO> findOne(Long id) {
        log.debug("Request to get BesoinEngrais : {}", id);
        return besoinEngraisRepository.findById(id)
            .map(besoinEngraisMapper::toDto);
    }

    /**
     * Delete the besoinEngrais by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BesoinEngrais : {}", id);
        besoinEngraisRepository.deleteById(id);
    }
}
