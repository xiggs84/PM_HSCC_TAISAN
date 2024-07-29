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
import vn.vnpt.repository.DanhSachTaiSanRepository;
import vn.vnpt.service.DanhSachTaiSanService;
import vn.vnpt.service.dto.DanhSachTaiSanDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhSachTaiSan}.
 */
@RestController
@RequestMapping("/api/danh-sach-tai-sans")
public class DanhSachTaiSanResource {

    private static final Logger log = LoggerFactory.getLogger(DanhSachTaiSanResource.class);

    private static final String ENTITY_NAME = "taiSanDanhSachTaiSan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhSachTaiSanService danhSachTaiSanService;

    private final DanhSachTaiSanRepository danhSachTaiSanRepository;

    public DanhSachTaiSanResource(DanhSachTaiSanService danhSachTaiSanService, DanhSachTaiSanRepository danhSachTaiSanRepository) {
        this.danhSachTaiSanService = danhSachTaiSanService;
        this.danhSachTaiSanRepository = danhSachTaiSanRepository;
    }

    /**
     * {@code POST  /danh-sach-tai-sans} : Create a new danhSachTaiSan.
     *
     * @param danhSachTaiSanDTO the danhSachTaiSanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhSachTaiSanDTO, or with status {@code 400 (Bad Request)} if the danhSachTaiSan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhSachTaiSanDTO> createDanhSachTaiSan(@RequestBody DanhSachTaiSanDTO danhSachTaiSanDTO)
        throws URISyntaxException {
        log.debug("REST request to save DanhSachTaiSan : {}", danhSachTaiSanDTO);
        if (danhSachTaiSanDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhSachTaiSan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        danhSachTaiSanDTO = danhSachTaiSanService.save(danhSachTaiSanDTO);
        return ResponseEntity.created(new URI("/api/danh-sach-tai-sans/" + danhSachTaiSanDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhSachTaiSanDTO.getId().toString()))
            .body(danhSachTaiSanDTO);
    }

    /**
     * {@code PUT  /danh-sach-tai-sans/:id} : Updates an existing danhSachTaiSan.
     *
     * @param id the id of the danhSachTaiSanDTO to save.
     * @param danhSachTaiSanDTO the danhSachTaiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhSachTaiSanDTO,
     * or with status {@code 400 (Bad Request)} if the danhSachTaiSanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhSachTaiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DanhSachTaiSanDTO> updateDanhSachTaiSan(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DanhSachTaiSanDTO danhSachTaiSanDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DanhSachTaiSan : {}, {}", id, danhSachTaiSanDTO);
        if (danhSachTaiSanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhSachTaiSanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhSachTaiSanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhSachTaiSanDTO = danhSachTaiSanService.update(danhSachTaiSanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhSachTaiSanDTO.getId().toString()))
            .body(danhSachTaiSanDTO);
    }

    /**
     * {@code PATCH  /danh-sach-tai-sans/:id} : Partial updates given fields of an existing danhSachTaiSan, field will ignore if it is null
     *
     * @param id the id of the danhSachTaiSanDTO to save.
     * @param danhSachTaiSanDTO the danhSachTaiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhSachTaiSanDTO,
     * or with status {@code 400 (Bad Request)} if the danhSachTaiSanDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhSachTaiSanDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhSachTaiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhSachTaiSanDTO> partialUpdateDanhSachTaiSan(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DanhSachTaiSanDTO danhSachTaiSanDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DanhSachTaiSan partially : {}, {}", id, danhSachTaiSanDTO);
        if (danhSachTaiSanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhSachTaiSanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhSachTaiSanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhSachTaiSanDTO> result = danhSachTaiSanService.partialUpdate(danhSachTaiSanDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhSachTaiSanDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /danh-sach-tai-sans} : get all the danhSachTaiSans.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhSachTaiSans in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DanhSachTaiSanDTO>> getAllDanhSachTaiSans(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DanhSachTaiSans");
        Page<DanhSachTaiSanDTO> page = danhSachTaiSanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /danh-sach-tai-sans/:id} : get the "id" danhSachTaiSan.
     *
     * @param id the id of the danhSachTaiSanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhSachTaiSanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhSachTaiSanDTO> getDanhSachTaiSan(@PathVariable("id") Long id) {
        log.debug("REST request to get DanhSachTaiSan : {}", id);
        Optional<DanhSachTaiSanDTO> danhSachTaiSanDTO = danhSachTaiSanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhSachTaiSanDTO);
    }

    /**
     * {@code DELETE  /danh-sach-tai-sans/:id} : delete the "id" danhSachTaiSan.
     *
     * @param id the id of the danhSachTaiSanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhSachTaiSan(@PathVariable("id") Long id) {
        log.debug("REST request to delete DanhSachTaiSan : {}", id);
        danhSachTaiSanService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
