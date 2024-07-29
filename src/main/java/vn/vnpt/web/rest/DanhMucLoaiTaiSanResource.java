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
import vn.vnpt.repository.DanhMucLoaiTaiSanRepository;
import vn.vnpt.service.DanhMucLoaiTaiSanQueryService;
import vn.vnpt.service.DanhMucLoaiTaiSanService;
import vn.vnpt.service.criteria.DanhMucLoaiTaiSanCriteria;
import vn.vnpt.service.dto.DanhMucLoaiTaiSanDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhMucLoaiTaiSan}.
 */
@RestController
@RequestMapping("/api/danh-muc-loai-tai-sans")
public class DanhMucLoaiTaiSanResource {

    private static final Logger log = LoggerFactory.getLogger(DanhMucLoaiTaiSanResource.class);

    private static final String ENTITY_NAME = "taiSanDanhMucLoaiTaiSan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhMucLoaiTaiSanService danhMucLoaiTaiSanService;

    private final DanhMucLoaiTaiSanRepository danhMucLoaiTaiSanRepository;

    private final DanhMucLoaiTaiSanQueryService danhMucLoaiTaiSanQueryService;

    public DanhMucLoaiTaiSanResource(
        DanhMucLoaiTaiSanService danhMucLoaiTaiSanService,
        DanhMucLoaiTaiSanRepository danhMucLoaiTaiSanRepository,
        DanhMucLoaiTaiSanQueryService danhMucLoaiTaiSanQueryService
    ) {
        this.danhMucLoaiTaiSanService = danhMucLoaiTaiSanService;
        this.danhMucLoaiTaiSanRepository = danhMucLoaiTaiSanRepository;
        this.danhMucLoaiTaiSanQueryService = danhMucLoaiTaiSanQueryService;
    }

    /**
     * {@code POST  /danh-muc-loai-tai-sans} : Create a new danhMucLoaiTaiSan.
     *
     * @param danhMucLoaiTaiSanDTO the danhMucLoaiTaiSanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhMucLoaiTaiSanDTO, or with status {@code 400 (Bad Request)} if the danhMucLoaiTaiSan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhMucLoaiTaiSanDTO> createDanhMucLoaiTaiSan(@RequestBody DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO)
        throws URISyntaxException {
        log.debug("REST request to save DanhMucLoaiTaiSan : {}", danhMucLoaiTaiSanDTO);
        if (danhMucLoaiTaiSanDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhMucLoaiTaiSan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        danhMucLoaiTaiSanDTO = danhMucLoaiTaiSanService.save(danhMucLoaiTaiSanDTO);
        return ResponseEntity.created(new URI("/api/danh-muc-loai-tai-sans/" + danhMucLoaiTaiSanDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhMucLoaiTaiSanDTO.getId().toString()))
            .body(danhMucLoaiTaiSanDTO);
    }

    /**
     * {@code PUT  /danh-muc-loai-tai-sans/:id} : Updates an existing danhMucLoaiTaiSan.
     *
     * @param id the id of the danhMucLoaiTaiSanDTO to save.
     * @param danhMucLoaiTaiSanDTO the danhMucLoaiTaiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucLoaiTaiSanDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucLoaiTaiSanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhMucLoaiTaiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DanhMucLoaiTaiSanDTO> updateDanhMucLoaiTaiSan(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DanhMucLoaiTaiSan : {}, {}", id, danhMucLoaiTaiSanDTO);
        if (danhMucLoaiTaiSanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhMucLoaiTaiSanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucLoaiTaiSanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhMucLoaiTaiSanDTO = danhMucLoaiTaiSanService.update(danhMucLoaiTaiSanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucLoaiTaiSanDTO.getId().toString()))
            .body(danhMucLoaiTaiSanDTO);
    }

    /**
     * {@code PATCH  /danh-muc-loai-tai-sans/:id} : Partial updates given fields of an existing danhMucLoaiTaiSan, field will ignore if it is null
     *
     * @param id the id of the danhMucLoaiTaiSanDTO to save.
     * @param danhMucLoaiTaiSanDTO the danhMucLoaiTaiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucLoaiTaiSanDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucLoaiTaiSanDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhMucLoaiTaiSanDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhMucLoaiTaiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhMucLoaiTaiSanDTO> partialUpdateDanhMucLoaiTaiSan(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DanhMucLoaiTaiSan partially : {}, {}", id, danhMucLoaiTaiSanDTO);
        if (danhMucLoaiTaiSanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhMucLoaiTaiSanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucLoaiTaiSanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhMucLoaiTaiSanDTO> result = danhMucLoaiTaiSanService.partialUpdate(danhMucLoaiTaiSanDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucLoaiTaiSanDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /danh-muc-loai-tai-sans} : get all the danhMucLoaiTaiSans.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhMucLoaiTaiSans in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DanhMucLoaiTaiSanDTO>> getAllDanhMucLoaiTaiSans(DanhMucLoaiTaiSanCriteria criteria) {
        log.debug("REST request to get DanhMucLoaiTaiSans by criteria: {}", criteria);

        List<DanhMucLoaiTaiSanDTO> entityList = danhMucLoaiTaiSanQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /danh-muc-loai-tai-sans/count} : count all the danhMucLoaiTaiSans.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDanhMucLoaiTaiSans(DanhMucLoaiTaiSanCriteria criteria) {
        log.debug("REST request to count DanhMucLoaiTaiSans by criteria: {}", criteria);
        return ResponseEntity.ok().body(danhMucLoaiTaiSanQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /danh-muc-loai-tai-sans/:id} : get the "id" danhMucLoaiTaiSan.
     *
     * @param id the id of the danhMucLoaiTaiSanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhMucLoaiTaiSanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhMucLoaiTaiSanDTO> getDanhMucLoaiTaiSan(@PathVariable("id") Long id) {
        log.debug("REST request to get DanhMucLoaiTaiSan : {}", id);
        Optional<DanhMucLoaiTaiSanDTO> danhMucLoaiTaiSanDTO = danhMucLoaiTaiSanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucLoaiTaiSanDTO);
    }

    /**
     * {@code DELETE  /danh-muc-loai-tai-sans/:id} : delete the "id" danhMucLoaiTaiSan.
     *
     * @param id the id of the danhMucLoaiTaiSanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhMucLoaiTaiSan(@PathVariable("id") Long id) {
        log.debug("REST request to delete DanhMucLoaiTaiSan : {}", id);
        danhMucLoaiTaiSanService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
