package fr.yolse.app.service.impl;

import fr.yolse.app.service.SuiviChampsService;
import fr.yolse.app.domain.SuiviChamps;
import fr.yolse.app.repository.SuiviChampsRepository;
import fr.yolse.app.service.dto.SuiviChampsDTO;
import fr.yolse.app.service.mapper.SuiviChampsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing SuiviChamps.
 */
@Service
@Transactional
public class SuiviChampsServiceImpl implements SuiviChampsService {

    private final Logger log = LoggerFactory.getLogger(SuiviChampsServiceImpl.class);

    private final SuiviChampsRepository suiviChampsRepository;

    private final SuiviChampsMapper suiviChampsMapper;

    public SuiviChampsServiceImpl(SuiviChampsRepository suiviChampsRepository, SuiviChampsMapper suiviChampsMapper) {
        this.suiviChampsRepository = suiviChampsRepository;
        this.suiviChampsMapper = suiviChampsMapper;
    }

    /**
     * Save a suiviChamps.
     *
     * @param suiviChampsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SuiviChampsDTO save(SuiviChampsDTO suiviChampsDTO) {
        log.debug("Request to save SuiviChamps : {}", suiviChampsDTO);
        SuiviChamps suiviChamps = suiviChampsMapper.toEntity(suiviChampsDTO);
        suiviChamps = suiviChampsRepository.save(suiviChamps);
        return suiviChampsMapper.toDto(suiviChamps);
    }

    /**
     * Get all the suiviChamps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SuiviChampsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SuiviChamps");
        return suiviChampsRepository.findAll(pageable)
            .map(suiviChampsMapper::toDto);
    }


    /**
     * Get one suiviChamps by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SuiviChampsDTO> findOne(Long id) {
        log.debug("Request to get SuiviChamps : {}", id);
        return suiviChampsRepository.findById(id)
            .map(suiviChampsMapper::toDto);
    }

    /**
     * Delete the suiviChamps by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SuiviChamps : {}", id);
        suiviChampsRepository.deleteById(id);
    }
}
