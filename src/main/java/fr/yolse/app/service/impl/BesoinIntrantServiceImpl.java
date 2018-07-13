package fr.yolse.app.service.impl;

import fr.yolse.app.service.BesoinIntrantService;
import fr.yolse.app.domain.BesoinIntrant;
import fr.yolse.app.repository.BesoinIntrantRepository;
import fr.yolse.app.service.dto.BesoinIntrantDTO;
import fr.yolse.app.service.mapper.BesoinIntrantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing BesoinIntrant.
 */
@Service
@Transactional
public class BesoinIntrantServiceImpl implements BesoinIntrantService {

    private final Logger log = LoggerFactory.getLogger(BesoinIntrantServiceImpl.class);

    private final BesoinIntrantRepository besoinIntrantRepository;

    private final BesoinIntrantMapper besoinIntrantMapper;

    public BesoinIntrantServiceImpl(BesoinIntrantRepository besoinIntrantRepository, BesoinIntrantMapper besoinIntrantMapper) {
        this.besoinIntrantRepository = besoinIntrantRepository;
        this.besoinIntrantMapper = besoinIntrantMapper;
    }

    /**
     * Save a besoinIntrant.
     *
     * @param besoinIntrantDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BesoinIntrantDTO save(BesoinIntrantDTO besoinIntrantDTO) {
        log.debug("Request to save BesoinIntrant : {}", besoinIntrantDTO);
        BesoinIntrant besoinIntrant = besoinIntrantMapper.toEntity(besoinIntrantDTO);
        besoinIntrant = besoinIntrantRepository.save(besoinIntrant);
        return besoinIntrantMapper.toDto(besoinIntrant);
    }

    /**
     * Get all the besoinIntrants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BesoinIntrantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BesoinIntrants");
        return besoinIntrantRepository.findAll(pageable)
            .map(besoinIntrantMapper::toDto);
    }


    /**
     * Get one besoinIntrant by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BesoinIntrantDTO> findOne(Long id) {
        log.debug("Request to get BesoinIntrant : {}", id);
        return besoinIntrantRepository.findById(id)
            .map(besoinIntrantMapper::toDto);
    }

    /**
     * Delete the besoinIntrant by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BesoinIntrant : {}", id);
        besoinIntrantRepository.deleteById(id);
    }
}
