package fr.yolse.app.service.impl;

import fr.yolse.app.service.EngraisClientService;
import fr.yolse.app.domain.EngraisClient;
import fr.yolse.app.repository.EngraisClientRepository;
import fr.yolse.app.service.dto.EngraisClientDTO;
import fr.yolse.app.service.mapper.EngraisClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing EngraisClient.
 */
@Service
@Transactional
public class EngraisClientServiceImpl implements EngraisClientService {

    private final Logger log = LoggerFactory.getLogger(EngraisClientServiceImpl.class);

    private final EngraisClientRepository engraisClientRepository;

    private final EngraisClientMapper engraisClientMapper;

    public EngraisClientServiceImpl(EngraisClientRepository engraisClientRepository, EngraisClientMapper engraisClientMapper) {
        this.engraisClientRepository = engraisClientRepository;
        this.engraisClientMapper = engraisClientMapper;
    }

    /**
     * Save a engraisClient.
     *
     * @param engraisClientDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EngraisClientDTO save(EngraisClientDTO engraisClientDTO) {
        log.debug("Request to save EngraisClient : {}", engraisClientDTO);
        EngraisClient engraisClient = engraisClientMapper.toEntity(engraisClientDTO);
        engraisClient = engraisClientRepository.save(engraisClient);
        return engraisClientMapper.toDto(engraisClient);
    }

    /**
     * Get all the engraisClients.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EngraisClientDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EngraisClients");
        return engraisClientRepository.findAll(pageable)
            .map(engraisClientMapper::toDto);
    }


    /**
     * Get one engraisClient by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EngraisClientDTO> findOne(Long id) {
        log.debug("Request to get EngraisClient : {}", id);
        return engraisClientRepository.findById(id)
            .map(engraisClientMapper::toDto);
    }

    /**
     * Delete the engraisClient by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EngraisClient : {}", id);
        engraisClientRepository.deleteById(id);
    }
}
