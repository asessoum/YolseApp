package fr.yolse.app.service.impl;

import fr.yolse.app.service.TypeEngraisService;
import fr.yolse.app.domain.TypeEngrais;
import fr.yolse.app.repository.TypeEngraisRepository;
import fr.yolse.app.service.dto.TypeEngraisDTO;
import fr.yolse.app.service.mapper.TypeEngraisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing TypeEngrais.
 */
@Service
@Transactional
public class TypeEngraisServiceImpl implements TypeEngraisService {

    private final Logger log = LoggerFactory.getLogger(TypeEngraisServiceImpl.class);

    private final TypeEngraisRepository typeEngraisRepository;

    private final TypeEngraisMapper typeEngraisMapper;

    public TypeEngraisServiceImpl(TypeEngraisRepository typeEngraisRepository, TypeEngraisMapper typeEngraisMapper) {
        this.typeEngraisRepository = typeEngraisRepository;
        this.typeEngraisMapper = typeEngraisMapper;
    }

    /**
     * Save a typeEngrais.
     *
     * @param typeEngraisDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TypeEngraisDTO save(TypeEngraisDTO typeEngraisDTO) {
        log.debug("Request to save TypeEngrais : {}", typeEngraisDTO);
        TypeEngrais typeEngrais = typeEngraisMapper.toEntity(typeEngraisDTO);
        typeEngrais = typeEngraisRepository.save(typeEngrais);
        return typeEngraisMapper.toDto(typeEngrais);
    }

    /**
     * Get all the typeEngrais.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TypeEngraisDTO> findAll() {
        log.debug("Request to get all TypeEngrais");
        return typeEngraisRepository.findAll().stream()
            .map(typeEngraisMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one typeEngrais by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeEngraisDTO> findOne(Long id) {
        log.debug("Request to get TypeEngrais : {}", id);
        return typeEngraisRepository.findById(id)
            .map(typeEngraisMapper::toDto);
    }

    /**
     * Delete the typeEngrais by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeEngrais : {}", id);
        typeEngraisRepository.deleteById(id);
    }
}
