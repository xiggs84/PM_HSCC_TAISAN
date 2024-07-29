package vn.vnpt.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import vn.vnpt.repository.TaisanSaiQsddDgcRepository;
import vn.vnpt.service.TaisanSaiQsddDgcService;
import vn.vnpt.service.dto.TaisanSaiQsddDgcDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.TaisanSaiQsddDgc}.
 */
@RestController
@RequestMapping("/api/taisan-sai-qsdd-dgcs")
public class TaisanSaiQsddDgcResource {

    private static final Logger log = LoggerFactory.getLogger(TaisanSaiQsddDgcResource.class);

    private static final String ENTITY_NAME = "taiSanTaisanSaiQsddDgc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaisanSaiQsddDgcService taisanSaiQsddDgcService;

    private final TaisanSaiQsddDgcRepository taisanSaiQsddDgcRepository;

    public TaisanSaiQsddDgcResource(
        TaisanSaiQsddDgcService taisanSaiQsddDgcService,
        TaisanSaiQsddDgcRepository taisanSaiQsddDgcRepository
    ) {
        this.taisanSaiQsddDgcService = taisanSaiQsddDgcService;
        this.taisanSaiQsddDgcRepository = taisanSaiQsddDgcRepository;
    }

    /**
     * {@code POST  /taisan-sai-qsdd-dgcs} : Create a new taisanSaiQsddDgc.
     *
     * @param taisanSaiQsddDgcDTO the taisanSaiQsddDgcDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taisanSaiQsddDgcDTO, or with status {@code 400 (Bad Request)} if the taisanSaiQsddDgc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TaisanSaiQsddDgcDTO> createTaisanSaiQsddDgc(@RequestBody TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO)
        throws URISyntaxException {
        log.debug("REST request to save TaisanSaiQsddDgc : {}", taisanSaiQsddDgcDTO);
        if (taisanSaiQsddDgcDTO.getId() != null) {
            throw new BadRequestAlertException("A new taisanSaiQsddDgc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        taisanSaiQsddDgcDTO = taisanSaiQsddDgcService.save(taisanSaiQsddDgcDTO);
        return ResponseEntity.created(new URI("/api/taisan-sai-qsdd-dgcs/" + taisanSaiQsddDgcDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, taisanSaiQsddDgcDTO.getId().toString()))
            .body(taisanSaiQsddDgcDTO);
    }

    /**
     * {@code PUT  /taisan-sai-qsdd-dgcs/:id} : Updates an existing taisanSaiQsddDgc.
     *
     * @param id the id of the taisanSaiQsddDgcDTO to save.
     * @param taisanSaiQsddDgcDTO the taisanSaiQsddDgcDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taisanSaiQsddDgcDTO,
     * or with status {@code 400 (Bad Request)} if the taisanSaiQsddDgcDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taisanSaiQsddDgcDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaisanSaiQsddDgcDTO> updateTaisanSaiQsddDgc(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TaisanSaiQsddDgc : {}, {}", id, taisanSaiQsddDgcDTO);
        if (taisanSaiQsddDgcDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taisanSaiQsddDgcDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taisanSaiQsddDgcRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        taisanSaiQsddDgcDTO = taisanSaiQsddDgcService.update(taisanSaiQsddDgcDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taisanSaiQsddDgcDTO.getId().toString()))
            .body(taisanSaiQsddDgcDTO);
    }

    /**
     * {@code PATCH  /taisan-sai-qsdd-dgcs/:id} : Partial updates given fields of an existing taisanSaiQsddDgc, field will ignore if it is null
     *
     * @param id the id of the taisanSaiQsddDgcDTO to save.
     * @param taisanSaiQsddDgcDTO the taisanSaiQsddDgcDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taisanSaiQsddDgcDTO,
     * or with status {@code 400 (Bad Request)} if the taisanSaiQsddDgcDTO is not valid,
     * or with status {@code 404 (Not Found)} if the taisanSaiQsddDgcDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the taisanSaiQsddDgcDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaisanSaiQsddDgcDTO> partialUpdateTaisanSaiQsddDgc(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TaisanSaiQsddDgc partially : {}, {}", id, taisanSaiQsddDgcDTO);
        if (taisanSaiQsddDgcDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taisanSaiQsddDgcDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taisanSaiQsddDgcRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaisanSaiQsddDgcDTO> result = taisanSaiQsddDgcService.partialUpdate(taisanSaiQsddDgcDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taisanSaiQsddDgcDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /taisan-sai-qsdd-dgcs} : get all the taisanSaiQsddDgcs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taisanSaiQsddDgcs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<TaisanSaiQsddDgcDTO>> getAllTaisanSaiQsddDgcs(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of TaisanSaiQsddDgcs");
        Page<TaisanSaiQsddDgcDTO> page = taisanSaiQsddDgcService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /taisan-sai-qsdd-dgcs/:id} : get the "id" taisanSaiQsddDgc.
     *
     * @param id the id of the taisanSaiQsddDgcDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taisanSaiQsddDgcDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaisanSaiQsddDgcDTO> getTaisanSaiQsddDgc(@PathVariable("id") Long id) {
        log.debug("REST request to get TaisanSaiQsddDgc : {}", id);
        Optional<TaisanSaiQsddDgcDTO> taisanSaiQsddDgcDTO = taisanSaiQsddDgcService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taisanSaiQsddDgcDTO);
    }

    /**
     * {@code DELETE  /taisan-sai-qsdd-dgcs/:id} : delete the "id" taisanSaiQsddDgc.
     *
     * @param id the id of the taisanSaiQsddDgcDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaisanSaiQsddDgc(@PathVariable("id") Long id) {
        log.debug("REST request to delete TaisanSaiQsddDgc : {}", id);
        taisanSaiQsddDgcService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
