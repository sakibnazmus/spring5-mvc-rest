package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static guru.springfamework.controllers.v1.VendorController.BASE_VENDOR_URL;
import static org.junit.jupiter.api.Assertions.*;

class VendorMapperTest {

    public static final long ID = 1L;
    public static final String NAME = "name";
    VendorMapper vendorMapper;

    @BeforeEach
    void setUp() {
        vendorMapper = VendorMapper.INSTANCE;
    }

    @Test
    void testVendorToVendorDTO() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(NAME, vendorDTO.getName());
        assertEquals(BASE_VENDOR_URL +"/"+ID, vendorDTO.getVendorUrl());
    }

    @Test
    void testVendorDTOToVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        assertEquals(NAME, vendor.getName());
    }
}