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
import vn.vnpt.repository.TaiSanDatNhaRepository;
import vn.vnpt.service.TaiSanDatNhaQueryService;
import vn.vnpt.service.TaiSanDatNhaService;
import vn.vnpt.service.criteria.TaiSanDatNhaCriteria;
import vn.vnpt.service.dto.TaiSanDatNhaDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.TaiSanDatNha}.
 */
@RestController
@RequestMapping("/api/tai-san-dat-nhas")
public class TaiSanDatNhaResource {

    private static final Logger log = LoggerFactory.getLogger(TaiSanDatNhaResource.class);

    private static final String ENTITY_NAME = "taiSanTaiSanDatNha";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaiSanDatNhaService taiSanDatNhaService;

    private final TaiSanDatNhaRepository taiSanDatNhaRepository;

    private final TaiSanDatNhaQueryService taiSanDatNhaQueryService;

    public TaiSanDatNhaResource(
        TaiSanDatNhaService taiSanDatNhaService,
        TaiSanDatNhaRepository taiSanDatNhaRepository,
        TaiSanDatNhaQueryService taiSanDatNhaQueryService
    ) {
        this.taiSanDatNhaService = taiSanDatNhaService;
        this.taiSanDatNhaRepository = taiSanDatNhaRepository;
        this.taiSanDatNhaQueryService = taiSanDatNhaQueryService;
    }

    /**
     * {@code POST  /tai-san-dat-nhas} : Create a new taiSanDatNha.
     *
     * @param taiSanDatNhaDTO the taiSanDatNhaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taiSanDatNhaDTO, or with status {@code 400 (Bad Request)} if the taiSanDatNha has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TaiSanDatNhaDTO> createTaiSanDatNha(@RequestBody TaiSanDatNhaDTO taiSanDatNhaDTO) throws URISyntaxException {
        log.debug("REST request to save TaiSanDatNha : {}", taiSanDatNhaDTO);
        if (taiSanDatNhaDTO.getId() != null) {
            throw new BadRequestAlertException("A new taiSanDatNha cannot already have an ID", ENTITY_NAME, "idexists");
        }
        taiSanDatNhaDTO = taiSanDatNhaService.save(taiSanDatNhaDTO);
        return ResponseEntity.created(new URI("/api/tai-san-dat-nhas/" + taiSanDatNhaDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, taiSanDatNhaDTO.getId().toString()))
            .body(taiSanDatNhaDTO);
    }

    /**
     * {@code PUT  /tai-san-dat-nhas/:id} : Updates an existing taiSanDatNha.
     *
     * @param id the id of the taiSanDatNhaDTO to save.
     * @param taiSanDatNhaDTO the taiSanDatNhaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taiSanDatNhaDTO,
     * or with status {@code 400 (Bad Request)} if the taiSanDatNhaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taiSanDatNhaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaiSanDatNhaDTO> updateTaiSanDatNha(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TaiSanDatNhaDTO taiSanDatNhaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TaiSanDatNha : {}, {}", id, taiSanDatNhaDTO);
        if (taiSanDatNhaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taiSanDatNhaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taiSanDatNhaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        taiSanDatNhaDTO = taiSanDatNhaService.update(taiSanDatNhaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taiSanDatNhaDTO.getId().toString()))
            .body(taiSanDatNhaDTO);
    }

    /**
     * {@code PATCH  /tai-san-dat-nhas/:id} : Partial updates given fields of an existing taiSanDatNha, field will ignore if it is null
     *
     * @param id the id of the taiSanDatNhaDTO to save.
     * @param taiSanDatNhaDTO the taiSanDatNhaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taiSanDatNhaDTO,
     * or with status {@code 400 (Bad Request)} if the taiSanDatNhaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the taiSanDatNhaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the taiSanDatNhaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaiSanDatNhaDTO> partialUpdateTaiSanDatNha(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TaiSanDatNhaDTO taiSanDatNhaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TaiSanDatNha partially : {}, {}", id, taiSanDatNhaDTO);
        if (taiSanDatNhaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taiSanDatNhaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taiSanDatNhaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaiSanDatNhaDTO> result = taiSanDatNhaService.partialUpdate(taiSanDatNhaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taiSanDatNhaDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tai-san-dat-nhas} : get all the taiSanDatNhas.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taiSanDatNhas in body.
     */
    @GetMapping("")
    public ResponseEntity<List<TaiSanDatNhaDTO>> getAllTaiSanDatNhas(TaiSanDatNhaCriteria criteria) {
        log.debug("REST request to get TaiSanDatNhas by criteria: {}", criteria);

        List<TaiSanDatNhaDTO> entityList = taiSanDatNhaQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /tai-san-dat-nhas/count} : count all the taiSanDatNhas.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countTaiSanDatNhas(TaiSanDatNhaCriteria criteria) {
        log.debug("REST request to count TaiSanDatNhas by criteria: {}", criteria);
        return ResponseEntity.ok().body(taiSanDatNhaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /tai-san-dat-nhas/:id} : get the "id" taiSanDatNha.
     *
     * @param id the id of the taiSanDatNhaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taiSanDatNhaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaiSanDatNhaDTO> getTaiSanDatNha(@PathVariable("id") Long id) {
        log.debug("REST request to get TaiSanDatNha : {}", id);
        Optional<TaiSanDatNhaDTO> taiSanDatNhaDTO = taiSanDatNhaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taiSanDatNhaDTO);
    }

    /**
     * {@code DELETE  /tai-san-dat-nhas/:id} : delete the "id" taiSanDatNha.
     *
     * @param id the id of the taiSanDatNhaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaiSanDatNha(@PathVariable("id") Long id) {
        log.debug("REST request to delete TaiSanDatNha : {}", id);
        taiSanDatNhaService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
