package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class TaiSanDuongSuAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTaiSanDuongSuAllPropertiesEquals(TaiSanDuongSu expected, TaiSanDuongSu actual) {
        assertTaiSanDuongSuAutoGeneratedPropertiesEquals(expected, actual);
        assertTaiSanDuongSuAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTaiSanDuongSuAllUpdatablePropertiesEquals(TaiSanDuongSu expected, TaiSanDuongSu actual) {
        assertTaiSanDuongSuUpdatableFieldsEquals(expected, actual);
        assertTaiSanDuongSuUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTaiSanDuongSuAutoGeneratedPropertiesEquals(TaiSanDuongSu expected, TaiSanDuongSu actual) {
        assertThat(expected)
            .as("Verify TaiSanDuongSu auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTaiSanDuongSuUpdatableFieldsEquals(TaiSanDuongSu expected, TaiSanDuongSu actual) {
        assertThat(expected)
            .as("Verify TaiSanDuongSu relevant properties")
            .satisfies(e -> assertThat(e.getTrangThai()).as("check trangThai").isEqualTo(actual.getTrangThai()))
            .satisfies(e -> assertThat(e.getIdDuongSu()).as("check idDuongSu").isEqualTo(actual.getIdDuongSu()))
            .satisfies(e -> assertThat(e.getNgayThaoTac()).as("check ngayThaoTac").isEqualTo(actual.getNgayThaoTac()))
            .satisfies(e -> assertThat(e.getIdLoaiHopDong()).as("check idLoaiHopDong").isEqualTo(actual.getIdLoaiHopDong()))
            .satisfies(e -> assertThat(e.getIdChungThuc()).as("check idChungThuc").isEqualTo(actual.getIdChungThuc()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTaiSanDuongSuUpdatableRelationshipsEquals(TaiSanDuongSu expected, TaiSanDuongSu actual) {
        assertThat(expected)
            .as("Verify TaiSanDuongSu relationships")
            .satisfies(e -> assertThat(e.getIdTaiSan()).as("check idTaiSan").isEqualTo(actual.getIdTaiSan()));
    }
}