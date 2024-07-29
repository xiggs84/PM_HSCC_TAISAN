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
import vn.vnpt.repository.TaiSanDgcRepository;
import vn.vnpt.service.TaiSanDgcQueryService;
import vn.vnpt.service.TaiSanDgcService;
import vn.vnpt.service.criteria.TaiSanDgcCriteria;
import vn.vnpt.service.dto.TaiSanDgcDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.TaiSanDgc}.
 */
@RestController
@RequestMapping("/api/tai-san-dgcs")
public class TaiSanDgcResource {

    private static final Logger log = LoggerFactory.getLogger(TaiSanDgcResource.class);

    private static final String ENTITY_NAME = "taiSanTaiSanDgc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaiSanDgcService taiSanDgcService;

    private final TaiSanDgcRepository taiSanDgcRepository;

    private final TaiSanDgcQueryService taiSanDgcQueryService;

    public TaiSanDgcResource(
        TaiSanDgcService taiSanDgcService,
        TaiSanDgcRepository taiSanDgcRepository,
        TaiSanDgcQueryService taiSanDgcQueryService
    ) {
        this.taiSanDgcService = taiSanDgcService;
        this.taiSanDgcRepository = taiSanDgcRepository;
        this.taiSanDgcQueryService = taiSanDgcQueryService;
    }

    /**
     * {@code POST  /tai-san-dgcs} : Create a new taiSanDgc.
     *
     * @param taiSanDgcDTO the taiSanDgcDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taiSanDgcDTO, or with status {@code 400 (Bad Request)} if the taiSanDgc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TaiSanDgcDTO> createTaiSanDgc(@RequestBody TaiSanDgcDTO taiSanDgcDTO) throws URISyntaxException {
        log.debug("REST request to save TaiSanDgc : {}", taiSanDgcDTO);
        if (taiSanDgcDTO.getId() != null) {
            throw new BadRequestAlertException("A new taiSanDgc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        taiSanDgcDTO = taiSanDgcService.save(taiSanDgcDTO);
        return ResponseEntity.created(new URI("/api/tai-san-dgcs/" + taiSanDgcDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, taiSanDgcDTO.getId().toString()))
            .body(taiSanDgcDTO);
    }

    /**
     * {@code PUT  /tai-san-dgcs/:id} : Updates an existing taiSanDgc.
     *
     * @param id the id of the taiSanDgcDTO to save.
     * @param taiSanDgcDTO the taiSanDgcDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taiSanDgcDTO,
     * or with status {@code 400 (Bad Request)} if the taiSanDgcDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taiSanDgcDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaiSanDgcDTO> updateTaiSanDgc(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TaiSanDgcDTO taiSanDgcDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TaiSanDgc : {}, {}", id, taiSanDgcDTO);
        if (taiSanDgcDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taiSanDgcDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taiSanDgcRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        taiSanDgcDTO = taiSanDgcService.update(taiSanDgcDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taiSanDgcDTO.getId().toString()))
            .body(taiSanDgcDTO);
    }

    /**
     * {@code PATCH  /tai-san-dgcs/:id} : Partial updates given fields of an existing taiSanDgc, field will ignore if it is null
     *
     * @param id the id of the taiSanDgcDTO to save.
     * @param taiSanDgcDTO the taiSanDgcDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taiSanDgcDTO,
     * or with status {@code 400 (Bad Request)} if the taiSanDgcDTO is not valid,
     * or with status {@code 404 (Not Found)} if the taiSanDgcDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the taiSanDgcDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaiSanDgcDTO> partialUpdateTaiSanDgc(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TaiSanDgcDTO taiSanDgcDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TaiSanDgc partially : {}, {}", id, taiSanDgcDTO);
        if (taiSanDgcDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taiSanDgcDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taiSanDgcRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaiSanDgcDTO> result = taiSanDgcService.partialUpdate(taiSanDgcDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taiSanDgcDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tai-san-dgcs} : get all the taiSanDgcs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taiSanDgcs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<TaiSanDgcDTO>> getAllTaiSanDgcs(TaiSanDgcCriteria criteria) {
        log.debug("REST request to get TaiSanDgcs by criteria: {}", criteria);

        List<TaiSanDgcDTO> entityList = taiSanDgcQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /tai-san-dgcs/count} : count all the taiSanDgcs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countTaiSanDgcs(TaiSanDgcCriteria criteria) {
        log.debug("REST request to count TaiSanDgcs by criteria: {}", criteria);
        return ResponseEntity.ok().body(taiSanDgcQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /tai-san-dgcs/:id} : get the "id" taiSanDgc.
     *
     * @param id the id of the taiSanDgcDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taiSanDgcDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaiSanDgcDTO> getTaiSanDgc(@PathVariable("id") Long id) {
        log.debug("REST request to get TaiSanDgc : {}", id);
        Optional<TaiSanDgcDTO> taiSanDgcDTO = taiSanDgcService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taiSanDgcDTO);
    }

    /**
     * {@code DELETE  /tai-san-dgcs/:id} : delete the "id" taiSanDgc.
     *
     * @param id the id of the taiSanDgcDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaiSanDgc(@PathVariable("id") Long id) {
        log.debug("REST request to delete TaiSanDgc : {}", id);
        taiSanDgcService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
