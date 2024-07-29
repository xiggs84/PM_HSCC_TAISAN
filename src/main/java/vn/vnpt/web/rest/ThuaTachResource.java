package vn.vnpt.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;
import vn.vnpt.repository.ThuaTachRepository;
import vn.vnpt.service.ThuaTachQueryService;
import vn.vnpt.service.ThuaTachService;
import vn.vnpt.service.criteria.ThuaTachCriteria;
import vn.vnpt.service.dto.ThuaTachDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.ThuaTach}.
 */
@RestController
@RequestMapping("/api/thua-taches")
public class ThuaTachResource {

    private static final Logger log = LoggerFactory.getLogger(ThuaTachResource.class);

    private static final String ENTITY_NAME = "taiSanThuaTach";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ThuaTachService thuaTachService;

    private final ThuaTachRepository thuaTachRepository;

    private final ThuaTachQueryService thuaTachQueryService;

    public ThuaTachResource(
        ThuaTachService thuaTachService,
        ThuaTachRepository thuaTachRepository,
        ThuaTachQueryService thuaTachQueryService
    ) {
        this.thuaTachService = thuaTachService;
        this.thuaTachRepository = thuaTachRepository;
        this.thuaTachQueryService = thuaTachQueryService;
    }

    /**
     * {@code POST  /thua-taches} : Create a new thuaTach.
     *
     * @param thuaTachDTO the thuaTachDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new thuaTachDTO, or with status {@code 400 (Bad Request)} if the thuaTach has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ThuaTachDTO> createThuaTach(@RequestBody ThuaTachDTO thuaTachDTO) throws URISyntaxException {
        log.debug("REST request to save ThuaTach : {}", thuaTachDTO);
        if (thuaTachDTO.getId() != null) {
            throw new BadRequestAlertException("A new thuaTach cannot already have an ID", ENTITY_NAME, "idexists");
        }
        thuaTachDTO = thuaTachService.save(thuaTachDTO);
        return ResponseEntity.created(new URI("/api/thua-taches/" + thuaTachDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, thuaTachDTO.getId().toString()))
            .body(thuaTachDTO);
    }

    /**
     * {@code PUT  /thua-taches/:id} : Updates an existing thuaTach.
     *
     * @param id the id of the thuaTachDTO to save.
     * @param thuaTachDTO the thuaTachDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thuaTachDTO,
     * or with status {@code 400 (Bad Request)} if the thuaTachDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the thuaTachDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ThuaTachDTO> updateThuaTach(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ThuaTachDTO thuaTachDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ThuaTach : {}, {}", id, thuaTachDTO);
        if (thuaTachDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, thuaTachDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!thuaTachRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        thuaTachDTO = thuaTachService.update(thuaTachDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thuaTachDTO.getId().toString()))
            .body(thuaTachDTO);
    }

    /**
     * {@code PATCH  /thua-taches/:id} : Partial updates given fields of an existing thuaTach, field will ignore if it is null
     *
     * @param id the id of the thuaTachDTO to save.
     * @param thuaTachDTO the thuaTachDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thuaTachDTO,
     * or with status {@code 400 (Bad Request)} if the thuaTachDTO is not valid,
     * or with status {@code 404 (Not Found)} if the thuaTachDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the thuaTachDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ThuaTachDTO> partialUpdateThuaTach(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ThuaTachDTO thuaTachDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ThuaTach partially : {}, {}", id, thuaTachDTO);
        if (thuaTachDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, thuaTachDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!thuaTachRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ThuaTachDTO> result = thuaTachService.partialUpdate(thuaTachDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thuaTachDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /thua-taches} : get all the thuaTaches.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of thuaTaches in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ThuaTachDTO>> getAllThuaTaches(ThuaTachCriteria criteria) {
        log.debug("REST request to get ThuaTaches by criteria: {}", criteria);

        List<ThuaTachDTO> entityList = thuaTachQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /thua-taches/count} : count all the thuaTaches.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countThuaTaches(ThuaTachCriteria criteria) {
        log.debug("REST request to count ThuaTaches by criteria: {}", criteria);
        return ResponseEntity.ok().body(thuaTachQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /thua-taches/:id} : get the "id" thuaTach.
     *
     * @param id the id of the thuaTachDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the thuaTachDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ThuaTachDTO> getThuaTach(@PathVariable("id") Long id) {
        log.debug("REST request to get ThuaTach : {}", id);
        Optional<ThuaTachDTO> thuaTachDTO = thuaTachService.findOne(id);
        return ResponseUtil.wrapOrNotFound(thuaTachDTO);
    }

    /**
     * {@code DELETE  /thua-taches/:id} : delete the "id" thuaTach.
     *
     * @param id the id of the thuaTachDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThuaTach(@PathVariable("id") Long id) {
        log.debug("REST request to delete ThuaTach : {}", id);
        thuaTachService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
