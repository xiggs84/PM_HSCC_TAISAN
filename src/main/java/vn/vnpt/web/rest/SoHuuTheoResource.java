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
import vn.vnpt.repository.SoHuuTheoRepository;
import vn.vnpt.service.SoHuuTheoQueryService;
import vn.vnpt.service.SoHuuTheoService;
import vn.vnpt.service.criteria.SoHuuTheoCriteria;
import vn.vnpt.service.dto.SoHuuTheoDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.SoHuuTheo}.
 */
@RestController
@RequestMapping("/api/so-huu-theos")
public class SoHuuTheoResource {

    private static final Logger log = LoggerFactory.getLogger(SoHuuTheoResource.class);

    private static final String ENTITY_NAME = "taiSanSoHuuTheo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SoHuuTheoService soHuuTheoService;

    private final SoHuuTheoRepository soHuuTheoRepository;

    private final SoHuuTheoQueryService soHuuTheoQueryService;

    public SoHuuTheoResource(
        SoHuuTheoService soHuuTheoService,
        SoHuuTheoRepository soHuuTheoRepository,
        SoHuuTheoQueryService soHuuTheoQueryService
    ) {
        this.soHuuTheoService = soHuuTheoService;
        this.soHuuTheoRepository = soHuuTheoRepository;
        this.soHuuTheoQueryService = soHuuTheoQueryService;
    }

    /**
     * {@code POST  /so-huu-theos} : Create a new soHuuTheo.
     *
     * @param soHuuTheoDTO the soHuuTheoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new soHuuTheoDTO, or with status {@code 400 (Bad Request)} if the soHuuTheo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SoHuuTheoDTO> createSoHuuTheo(@RequestBody SoHuuTheoDTO soHuuTheoDTO) throws URISyntaxException {
        log.debug("REST request to save SoHuuTheo : {}", soHuuTheoDTO);
        if (soHuuTheoDTO.getId() != null) {
            throw new BadRequestAlertException("A new soHuuTheo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        soHuuTheoDTO = soHuuTheoService.save(soHuuTheoDTO);
        return ResponseEntity.created(new URI("/api/so-huu-theos/" + soHuuTheoDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, soHuuTheoDTO.getId().toString()))
            .body(soHuuTheoDTO);
    }

    /**
     * {@code PUT  /so-huu-theos/:id} : Updates an existing soHuuTheo.
     *
     * @param id the id of the soHuuTheoDTO to save.
     * @param soHuuTheoDTO the soHuuTheoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soHuuTheoDTO,
     * or with status {@code 400 (Bad Request)} if the soHuuTheoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the soHuuTheoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SoHuuTheoDTO> updateSoHuuTheo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SoHuuTheoDTO soHuuTheoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SoHuuTheo : {}, {}", id, soHuuTheoDTO);
        if (soHuuTheoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, soHuuTheoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!soHuuTheoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        soHuuTheoDTO = soHuuTheoService.update(soHuuTheoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, soHuuTheoDTO.getId().toString()))
            .body(soHuuTheoDTO);
    }

    /**
     * {@code PATCH  /so-huu-theos/:id} : Partial updates given fields of an existing soHuuTheo, field will ignore if it is null
     *
     * @param id the id of the soHuuTheoDTO to save.
     * @param soHuuTheoDTO the soHuuTheoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soHuuTheoDTO,
     * or with status {@code 400 (Bad Request)} if the soHuuTheoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the soHuuTheoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the soHuuTheoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SoHuuTheoDTO> partialUpdateSoHuuTheo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SoHuuTheoDTO soHuuTheoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SoHuuTheo partially : {}, {}", id, soHuuTheoDTO);
        if (soHuuTheoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, soHuuTheoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!soHuuTheoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SoHuuTheoDTO> result = soHuuTheoService.partialUpdate(soHuuTheoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, soHuuTheoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /so-huu-theos} : get all the soHuuTheos.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of soHuuTheos in body.
     */
    @GetMapping("")
    public ResponseEntity<List<SoHuuTheoDTO>> getAllSoHuuTheos(SoHuuTheoCriteria criteria) {
        log.debug("REST request to get SoHuuTheos by criteria: {}", criteria);

        List<SoHuuTheoDTO> entityList = soHuuTheoQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /so-huu-theos/count} : count all the soHuuTheos.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countSoHuuTheos(SoHuuTheoCriteria criteria) {
        log.debug("REST request to count SoHuuTheos by criteria: {}", criteria);
        return ResponseEntity.ok().body(soHuuTheoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /so-huu-theos/:id} : get the "id" soHuuTheo.
     *
     * @param id the id of the soHuuTheoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the soHuuTheoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SoHuuTheoDTO> getSoHuuTheo(@PathVariable("id") Long id) {
        log.debug("REST request to get SoHuuTheo : {}", id);
        Optional<SoHuuTheoDTO> soHuuTheoDTO = soHuuTheoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(soHuuTheoDTO);
    }

    /**
     * {@code DELETE  /so-huu-theos/:id} : delete the "id" soHuuTheo.
     *
     * @param id the id of the soHuuTheoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoHuuTheo(@PathVariable("id") Long id) {
        log.debug("REST request to delete SoHuuTheo : {}", id);
        soHuuTheoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
