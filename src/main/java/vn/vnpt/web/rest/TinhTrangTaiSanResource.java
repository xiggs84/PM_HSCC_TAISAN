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
import vn.vnpt.repository.TinhTrangTaiSanRepository;
import vn.vnpt.service.TinhTrangTaiSanQueryService;
import vn.vnpt.service.TinhTrangTaiSanService;
import vn.vnpt.service.criteria.TinhTrangTaiSanCriteria;
import vn.vnpt.service.dto.TinhTrangTaiSanDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.TinhTrangTaiSan}.
 */
@RestController
@RequestMapping("/api/tinh-trang-tai-sans")
public class TinhTrangTaiSanResource {

    private static final Logger log = LoggerFactory.getLogger(TinhTrangTaiSanResource.class);

    private static final String ENTITY_NAME = "taiSanTinhTrangTaiSan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TinhTrangTaiSanService tinhTrangTaiSanService;

    private final TinhTrangTaiSanRepository tinhTrangTaiSanRepository;

    private final TinhTrangTaiSanQueryService tinhTrangTaiSanQueryService;

    public TinhTrangTaiSanResource(
        TinhTrangTaiSanService tinhTrangTaiSanService,
        TinhTrangTaiSanRepository tinhTrangTaiSanRepository,
        TinhTrangTaiSanQueryService tinhTrangTaiSanQueryService
    ) {
        this.tinhTrangTaiSanService = tinhTrangTaiSanService;
        this.tinhTrangTaiSanRepository = tinhTrangTaiSanRepository;
        this.tinhTrangTaiSanQueryService = tinhTrangTaiSanQueryService;
    }

    /**
     * {@code POST  /tinh-trang-tai-sans} : Create a new tinhTrangTaiSan.
     *
     * @param tinhTrangTaiSanDTO the tinhTrangTaiSanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tinhTrangTaiSanDTO, or with status {@code 400 (Bad Request)} if the tinhTrangTaiSan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TinhTrangTaiSanDTO> createTinhTrangTaiSan(@RequestBody TinhTrangTaiSanDTO tinhTrangTaiSanDTO)
        throws URISyntaxException {
        log.debug("REST request to save TinhTrangTaiSan : {}", tinhTrangTaiSanDTO);
        if (tinhTrangTaiSanDTO.getId() != null) {
            throw new BadRequestAlertException("A new tinhTrangTaiSan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        tinhTrangTaiSanDTO = tinhTrangTaiSanService.save(tinhTrangTaiSanDTO);
        return ResponseEntity.created(new URI("/api/tinh-trang-tai-sans/" + tinhTrangTaiSanDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, tinhTrangTaiSanDTO.getId().toString()))
            .body(tinhTrangTaiSanDTO);
    }

    /**
     * {@code PUT  /tinh-trang-tai-sans/:id} : Updates an existing tinhTrangTaiSan.
     *
     * @param id the id of the tinhTrangTaiSanDTO to save.
     * @param tinhTrangTaiSanDTO the tinhTrangTaiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tinhTrangTaiSanDTO,
     * or with status {@code 400 (Bad Request)} if the tinhTrangTaiSanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tinhTrangTaiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TinhTrangTaiSanDTO> updateTinhTrangTaiSan(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TinhTrangTaiSanDTO tinhTrangTaiSanDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TinhTrangTaiSan : {}, {}", id, tinhTrangTaiSanDTO);
        if (tinhTrangTaiSanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tinhTrangTaiSanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tinhTrangTaiSanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        tinhTrangTaiSanDTO = tinhTrangTaiSanService.update(tinhTrangTaiSanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tinhTrangTaiSanDTO.getId().toString()))
            .body(tinhTrangTaiSanDTO);
    }

    /**
     * {@code PATCH  /tinh-trang-tai-sans/:id} : Partial updates given fields of an existing tinhTrangTaiSan, field will ignore if it is null
     *
     * @param id the id of the tinhTrangTaiSanDTO to save.
     * @param tinhTrangTaiSanDTO the tinhTrangTaiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tinhTrangTaiSanDTO,
     * or with status {@code 400 (Bad Request)} if the tinhTrangTaiSanDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tinhTrangTaiSanDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tinhTrangTaiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TinhTrangTaiSanDTO> partialUpdateTinhTrangTaiSan(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TinhTrangTaiSanDTO tinhTrangTaiSanDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TinhTrangTaiSan partially : {}, {}", id, tinhTrangTaiSanDTO);
        if (tinhTrangTaiSanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tinhTrangTaiSanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tinhTrangTaiSanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TinhTrangTaiSanDTO> result = tinhTrangTaiSanService.partialUpdate(tinhTrangTaiSanDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tinhTrangTaiSanDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tinh-trang-tai-sans} : get all the tinhTrangTaiSans.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tinhTrangTaiSans in body.
     */
    @GetMapping("")
    public ResponseEntity<List<TinhTrangTaiSanDTO>> getAllTinhTrangTaiSans(TinhTrangTaiSanCriteria criteria) {
        log.debug("REST request to get TinhTrangTaiSans by criteria: {}", criteria);

        List<TinhTrangTaiSanDTO> entityList = tinhTrangTaiSanQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /tinh-trang-tai-sans/count} : count all the tinhTrangTaiSans.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countTinhTrangTaiSans(TinhTrangTaiSanCriteria criteria) {
        log.debug("REST request to count TinhTrangTaiSans by criteria: {}", criteria);
        return ResponseEntity.ok().body(tinhTrangTaiSanQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /tinh-trang-tai-sans/:id} : get the "id" tinhTrangTaiSan.
     *
     * @param id the id of the tinhTrangTaiSanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tinhTrangTaiSanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TinhTrangTaiSanDTO> getTinhTrangTaiSan(@PathVariable("id") Long id) {
        log.debug("REST request to get TinhTrangTaiSan : {}", id);
        Optional<TinhTrangTaiSanDTO> tinhTrangTaiSanDTO = tinhTrangTaiSanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tinhTrangTaiSanDTO);
    }

    /**
     * {@code DELETE  /tinh-trang-tai-sans/:id} : delete the "id" tinhTrangTaiSan.
     *
     * @param id the id of the tinhTrangTaiSanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTinhTrangTaiSan(@PathVariable("id") Long id) {
        log.debug("REST request to delete TinhTrangTaiSan : {}", id);
        tinhTrangTaiSanService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
