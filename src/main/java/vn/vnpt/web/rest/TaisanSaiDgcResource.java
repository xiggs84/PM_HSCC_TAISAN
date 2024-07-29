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
import vn.vnpt.repository.TaisanSaiDgcRepository;
import vn.vnpt.service.TaisanSaiDgcQueryService;
import vn.vnpt.service.TaisanSaiDgcService;
import vn.vnpt.service.criteria.TaisanSaiDgcCriteria;
import vn.vnpt.service.dto.TaisanSaiDgcDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.TaisanSaiDgc}.
 */
@RestController
@RequestMapping("/api/taisan-sai-dgcs")
public class TaisanSaiDgcResource {

    private static final Logger log = LoggerFactory.getLogger(TaisanSaiDgcResource.class);

    private static final String ENTITY_NAME = "taiSanTaisanSaiDgc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaisanSaiDgcService taisanSaiDgcService;

    private final TaisanSaiDgcRepository taisanSaiDgcRepository;

    private final TaisanSaiDgcQueryService taisanSaiDgcQueryService;

    public TaisanSaiDgcResource(
        TaisanSaiDgcService taisanSaiDgcService,
        TaisanSaiDgcRepository taisanSaiDgcRepository,
        TaisanSaiDgcQueryService taisanSaiDgcQueryService
    ) {
        this.taisanSaiDgcService = taisanSaiDgcService;
        this.taisanSaiDgcRepository = taisanSaiDgcRepository;
        this.taisanSaiDgcQueryService = taisanSaiDgcQueryService;
    }

    /**
     * {@code POST  /taisan-sai-dgcs} : Create a new taisanSaiDgc.
     *
     * @param taisanSaiDgcDTO the taisanSaiDgcDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taisanSaiDgcDTO, or with status {@code 400 (Bad Request)} if the taisanSaiDgc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TaisanSaiDgcDTO> createTaisanSaiDgc(@RequestBody TaisanSaiDgcDTO taisanSaiDgcDTO) throws URISyntaxException {
        log.debug("REST request to save TaisanSaiDgc : {}", taisanSaiDgcDTO);
        if (taisanSaiDgcDTO.getId() != null) {
            throw new BadRequestAlertException("A new taisanSaiDgc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        taisanSaiDgcDTO = taisanSaiDgcService.save(taisanSaiDgcDTO);
        return ResponseEntity.created(new URI("/api/taisan-sai-dgcs/" + taisanSaiDgcDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, taisanSaiDgcDTO.getId().toString()))
            .body(taisanSaiDgcDTO);
    }

    /**
     * {@code PUT  /taisan-sai-dgcs/:id} : Updates an existing taisanSaiDgc.
     *
     * @param id the id of the taisanSaiDgcDTO to save.
     * @param taisanSaiDgcDTO the taisanSaiDgcDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taisanSaiDgcDTO,
     * or with status {@code 400 (Bad Request)} if the taisanSaiDgcDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taisanSaiDgcDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaisanSaiDgcDTO> updateTaisanSaiDgc(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TaisanSaiDgcDTO taisanSaiDgcDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TaisanSaiDgc : {}, {}", id, taisanSaiDgcDTO);
        if (taisanSaiDgcDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taisanSaiDgcDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taisanSaiDgcRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        taisanSaiDgcDTO = taisanSaiDgcService.update(taisanSaiDgcDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taisanSaiDgcDTO.getId().toString()))
            .body(taisanSaiDgcDTO);
    }

    /**
     * {@code PATCH  /taisan-sai-dgcs/:id} : Partial updates given fields of an existing taisanSaiDgc, field will ignore if it is null
     *
     * @param id the id of the taisanSaiDgcDTO to save.
     * @param taisanSaiDgcDTO the taisanSaiDgcDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taisanSaiDgcDTO,
     * or with status {@code 400 (Bad Request)} if the taisanSaiDgcDTO is not valid,
     * or with status {@code 404 (Not Found)} if the taisanSaiDgcDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the taisanSaiDgcDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaisanSaiDgcDTO> partialUpdateTaisanSaiDgc(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TaisanSaiDgcDTO taisanSaiDgcDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TaisanSaiDgc partially : {}, {}", id, taisanSaiDgcDTO);
        if (taisanSaiDgcDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taisanSaiDgcDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taisanSaiDgcRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaisanSaiDgcDTO> result = taisanSaiDgcService.partialUpdate(taisanSaiDgcDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taisanSaiDgcDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /taisan-sai-dgcs} : get all the taisanSaiDgcs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taisanSaiDgcs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<TaisanSaiDgcDTO>> getAllTaisanSaiDgcs(TaisanSaiDgcCriteria criteria) {
        log.debug("REST request to get TaisanSaiDgcs by criteria: {}", criteria);

        List<TaisanSaiDgcDTO> entityList = taisanSaiDgcQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /taisan-sai-dgcs/count} : count all the taisanSaiDgcs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countTaisanSaiDgcs(TaisanSaiDgcCriteria criteria) {
        log.debug("REST request to count TaisanSaiDgcs by criteria: {}", criteria);
        return ResponseEntity.ok().body(taisanSaiDgcQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /taisan-sai-dgcs/:id} : get the "id" taisanSaiDgc.
     *
     * @param id the id of the taisanSaiDgcDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taisanSaiDgcDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaisanSaiDgcDTO> getTaisanSaiDgc(@PathVariable("id") Long id) {
        log.debug("REST request to get TaisanSaiDgc : {}", id);
        Optional<TaisanSaiDgcDTO> taisanSaiDgcDTO = taisanSaiDgcService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taisanSaiDgcDTO);
    }

    /**
     * {@code DELETE  /taisan-sai-dgcs/:id} : delete the "id" taisanSaiDgc.
     *
     * @param id the id of the taisanSaiDgcDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaisanSaiDgc(@PathVariable("id") Long id) {
        log.debug("REST request to delete TaisanSaiDgc : {}", id);
        taisanSaiDgcService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
