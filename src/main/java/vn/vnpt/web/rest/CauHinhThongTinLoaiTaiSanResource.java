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
import vn.vnpt.repository.CauHinhThongTinLoaiTaiSanRepository;
import vn.vnpt.service.CauHinhThongTinLoaiTaiSanQueryService;
import vn.vnpt.service.CauHinhThongTinLoaiTaiSanService;
import vn.vnpt.service.criteria.CauHinhThongTinLoaiTaiSanCriteria;
import vn.vnpt.service.dto.CauHinhThongTinLoaiTaiSanDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.CauHinhThongTinLoaiTaiSan}.
 */
@RestController
@RequestMapping("/api/cau-hinh-thong-tin-loai-tai-sans")
public class CauHinhThongTinLoaiTaiSanResource {

    private static final Logger log = LoggerFactory.getLogger(CauHinhThongTinLoaiTaiSanResource.class);

    private static final String ENTITY_NAME = "taiSanCauHinhThongTinLoaiTaiSan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CauHinhThongTinLoaiTaiSanService cauHinhThongTinLoaiTaiSanService;

    private final CauHinhThongTinLoaiTaiSanRepository cauHinhThongTinLoaiTaiSanRepository;

    private final CauHinhThongTinLoaiTaiSanQueryService cauHinhThongTinLoaiTaiSanQueryService;

    public CauHinhThongTinLoaiTaiSanResource(
        CauHinhThongTinLoaiTaiSanService cauHinhThongTinLoaiTaiSanService,
        CauHinhThongTinLoaiTaiSanRepository cauHinhThongTinLoaiTaiSanRepository,
        CauHinhThongTinLoaiTaiSanQueryService cauHinhThongTinLoaiTaiSanQueryService
    ) {
        this.cauHinhThongTinLoaiTaiSanService = cauHinhThongTinLoaiTaiSanService;
        this.cauHinhThongTinLoaiTaiSanRepository = cauHinhThongTinLoaiTaiSanRepository;
        this.cauHinhThongTinLoaiTaiSanQueryService = cauHinhThongTinLoaiTaiSanQueryService;
    }

    /**
     * {@code POST  /cau-hinh-thong-tin-loai-tai-sans} : Create a new cauHinhThongTinLoaiTaiSan.
     *
     * @param cauHinhThongTinLoaiTaiSanDTO the cauHinhThongTinLoaiTaiSanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cauHinhThongTinLoaiTaiSanDTO, or with status {@code 400 (Bad Request)} if the cauHinhThongTinLoaiTaiSan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CauHinhThongTinLoaiTaiSanDTO> createCauHinhThongTinLoaiTaiSan(
        @RequestBody CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO
    ) throws URISyntaxException {
        log.debug("REST request to save CauHinhThongTinLoaiTaiSan : {}", cauHinhThongTinLoaiTaiSanDTO);
        if (cauHinhThongTinLoaiTaiSanDTO.getId() != null) {
            throw new BadRequestAlertException("A new cauHinhThongTinLoaiTaiSan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        cauHinhThongTinLoaiTaiSanDTO = cauHinhThongTinLoaiTaiSanService.save(cauHinhThongTinLoaiTaiSanDTO);
        return ResponseEntity.created(new URI("/api/cau-hinh-thong-tin-loai-tai-sans/" + cauHinhThongTinLoaiTaiSanDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, cauHinhThongTinLoaiTaiSanDTO.getId().toString())
            )
            .body(cauHinhThongTinLoaiTaiSanDTO);
    }

    /**
     * {@code PUT  /cau-hinh-thong-tin-loai-tai-sans/:id} : Updates an existing cauHinhThongTinLoaiTaiSan.
     *
     * @param id the id of the cauHinhThongTinLoaiTaiSanDTO to save.
     * @param cauHinhThongTinLoaiTaiSanDTO the cauHinhThongTinLoaiTaiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cauHinhThongTinLoaiTaiSanDTO,
     * or with status {@code 400 (Bad Request)} if the cauHinhThongTinLoaiTaiSanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cauHinhThongTinLoaiTaiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CauHinhThongTinLoaiTaiSanDTO> updateCauHinhThongTinLoaiTaiSan(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CauHinhThongTinLoaiTaiSan : {}, {}", id, cauHinhThongTinLoaiTaiSanDTO);
        if (cauHinhThongTinLoaiTaiSanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cauHinhThongTinLoaiTaiSanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cauHinhThongTinLoaiTaiSanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        cauHinhThongTinLoaiTaiSanDTO = cauHinhThongTinLoaiTaiSanService.update(cauHinhThongTinLoaiTaiSanDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cauHinhThongTinLoaiTaiSanDTO.getId().toString())
            )
            .body(cauHinhThongTinLoaiTaiSanDTO);
    }

    /**
     * {@code PATCH  /cau-hinh-thong-tin-loai-tai-sans/:id} : Partial updates given fields of an existing cauHinhThongTinLoaiTaiSan, field will ignore if it is null
     *
     * @param id the id of the cauHinhThongTinLoaiTaiSanDTO to save.
     * @param cauHinhThongTinLoaiTaiSanDTO the cauHinhThongTinLoaiTaiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cauHinhThongTinLoaiTaiSanDTO,
     * or with status {@code 400 (Bad Request)} if the cauHinhThongTinLoaiTaiSanDTO is not valid,
     * or with status {@code 404 (Not Found)} if the cauHinhThongTinLoaiTaiSanDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the cauHinhThongTinLoaiTaiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CauHinhThongTinLoaiTaiSanDTO> partialUpdateCauHinhThongTinLoaiTaiSan(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CauHinhThongTinLoaiTaiSan partially : {}, {}", id, cauHinhThongTinLoaiTaiSanDTO);
        if (cauHinhThongTinLoaiTaiSanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cauHinhThongTinLoaiTaiSanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cauHinhThongTinLoaiTaiSanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CauHinhThongTinLoaiTaiSanDTO> result = cauHinhThongTinLoaiTaiSanService.partialUpdate(cauHinhThongTinLoaiTaiSanDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cauHinhThongTinLoaiTaiSanDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /cau-hinh-thong-tin-loai-tai-sans} : get all the cauHinhThongTinLoaiTaiSans.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cauHinhThongTinLoaiTaiSans in body.
     */
    @GetMapping("")
    public ResponseEntity<List<CauHinhThongTinLoaiTaiSanDTO>> getAllCauHinhThongTinLoaiTaiSans(CauHinhThongTinLoaiTaiSanCriteria criteria) {
        log.debug("REST request to get CauHinhThongTinLoaiTaiSans by criteria: {}", criteria);

        List<CauHinhThongTinLoaiTaiSanDTO> entityList = cauHinhThongTinLoaiTaiSanQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /cau-hinh-thong-tin-loai-tai-sans/count} : count all the cauHinhThongTinLoaiTaiSans.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countCauHinhThongTinLoaiTaiSans(CauHinhThongTinLoaiTaiSanCriteria criteria) {
        log.debug("REST request to count CauHinhThongTinLoaiTaiSans by criteria: {}", criteria);
        return ResponseEntity.ok().body(cauHinhThongTinLoaiTaiSanQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cau-hinh-thong-tin-loai-tai-sans/:id} : get the "id" cauHinhThongTinLoaiTaiSan.
     *
     * @param id the id of the cauHinhThongTinLoaiTaiSanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cauHinhThongTinLoaiTaiSanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CauHinhThongTinLoaiTaiSanDTO> getCauHinhThongTinLoaiTaiSan(@PathVariable("id") Long id) {
        log.debug("REST request to get CauHinhThongTinLoaiTaiSan : {}", id);
        Optional<CauHinhThongTinLoaiTaiSanDTO> cauHinhThongTinLoaiTaiSanDTO = cauHinhThongTinLoaiTaiSanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cauHinhThongTinLoaiTaiSanDTO);
    }

    /**
     * {@code DELETE  /cau-hinh-thong-tin-loai-tai-sans/:id} : delete the "id" cauHinhThongTinLoaiTaiSan.
     *
     * @param id the id of the cauHinhThongTinLoaiTaiSanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCauHinhThongTinLoaiTaiSan(@PathVariable("id") Long id) {
        log.debug("REST request to delete CauHinhThongTinLoaiTaiSan : {}", id);
        cauHinhThongTinLoaiTaiSanService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
