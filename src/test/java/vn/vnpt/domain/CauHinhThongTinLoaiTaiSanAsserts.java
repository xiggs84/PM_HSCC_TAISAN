package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class CauHinhThongTinLoaiTaiSanAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCauHinhThongTinLoaiTaiSanAllPropertiesEquals(
        CauHinhThongTinLoaiTaiSan expected,
        CauHinhThongTinLoaiTaiSan actual
    ) {
        assertCauHinhThongTinLoaiTaiSanAutoGeneratedPropertiesEquals(expected, actual);
        assertCauHinhThongTinLoaiTaiSanAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCauHinhThongTinLoaiTaiSanAllUpdatablePropertiesEquals(
        CauHinhThongTinLoaiTaiSan expected,
        CauHinhThongTinLoaiTaiSan actual
    ) {
        assertCauHinhThongTinLoaiTaiSanUpdatableFieldsEquals(expected, actual);
        assertCauHinhThongTinLoaiTaiSanUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCauHinhThongTinLoaiTaiSanAutoGeneratedPropertiesEquals(
        CauHinhThongTinLoaiTaiSan expected,
        CauHinhThongTinLoaiTaiSan actual
    ) {
        assertThat(expected)
            .as("Verify CauHinhThongTinLoaiTaiSan auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCauHinhThongTinLoaiTaiSanUpdatableFieldsEquals(
        CauHinhThongTinLoaiTaiSan expected,
        CauHinhThongTinLoaiTaiSan actual
    ) {
        assertThat(expected)
            .as("Verify CauHinhThongTinLoaiTaiSan relevant properties")
            .satisfies(e -> assertThat(e.getIdCauHinh()).as("check idCauHinh").isEqualTo(actual.getIdCauHinh()))
            .satisfies(e -> assertThat(e.getNoiDung()).as("check noiDung").isEqualTo(actual.getNoiDung()))
            .satisfies(e -> assertThat(e.getJavascript()).as("check javascript").isEqualTo(actual.getJavascript()))
            .satisfies(e -> assertThat(e.getCss()).as("check css").isEqualTo(actual.getCss()))
            .satisfies(e -> assertThat(e.getIdLoaiTs()).as("check idLoaiTs").isEqualTo(actual.getIdLoaiTs()))
            .satisfies(e -> assertThat(e.getIdDonVi()).as("check idDonVi").isEqualTo(actual.getIdDonVi()))
            .satisfies(e -> assertThat(e.getTrangThai()).as("check trangThai").isEqualTo(actual.getTrangThai()))
            .satisfies(e -> assertThat(e.getXml()).as("check xml").isEqualTo(actual.getXml()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCauHinhThongTinLoaiTaiSanUpdatableRelationshipsEquals(
        CauHinhThongTinLoaiTaiSan expected,
        CauHinhThongTinLoaiTaiSan actual
    ) {}
}