package fr.yolse.app.service.impl;

import fr.yolse.app.service.TypeCultureService;
import fr.yolse.app.domain.TypeCulture;
import fr.yolse.app.repository.TypeCultureRepository;
import fr.yolse.app.service.dto.TypeCultureDTO;
import fr.yolse.app.service.mapper.TypeCultureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing TypeCulture.
 */
@Service
@Transactional
public class TypeCultureServiceImpl implements TypeCultureService {

    private final Logger log = LoggerFactory.getLogger(TypeCultureServiceImpl.class);

    private final TypeCultureRepository typeCultureRepository;

    private final TypeCultureMapper typeCultureMapper;

    public TypeCultureServiceImpl(TypeCultureRepository typeCultureRepository, TypeCultureMapper typeCultureMapper) {
        this.typeCultureRepository = typeCultureRepository;
        this.typeCultureMapper = typeCultureMapper;
    }

    /**
     * Save a typeCulture.
     *
     * @param typeCultureDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TypeCultureDTO save(TypeCultureDTO typeCultureDTO) {
        log.debug("Request to save TypeCulture : {}", typeCultureDTO);
        TypeCulture typeCulture = typeCultureMapper.toEntity(typeCultureDTO);
        typeCulture = typeCultureRepository.save(typeCulture);
        return typeCultureMapper.toDto(typeCulture);
    }

    /**
     * Get all the typeCultures.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TypeCultureDTO> findAll() {
        log.debug("Request to get all TypeCultures");
        return typeCultureRepository.findAll().stream()
            .map(typeCultureMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one typeCulture by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeCultureDTO> findOne(Long id) {
        log.debug("Request to get TypeCulture : {}", id);
        return typeCultureRepository.findById(id)
            .map(typeCultureMapper::toDto);
    }

    /**
     * Delete the typeCulture by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeCulture : {}", id);
        typeCultureRepository.deleteById(id);
    }
}
