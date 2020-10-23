package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static guru.springfamework.controllers.v1.VendorController.BASE_VENDOR_URL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class VendorServiceTest {

    public static final long ID = 1L;
    public static final String NAME = "name";

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    void testGetAllVendors() {
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());
        when(vendorRepository.findAll()).thenReturn(vendors);
        List<VendorDTO> categoryDTOS = vendorService.getAllVendors();
        assertEquals(3, categoryDTOS.size());
    }

    @Test
    void testGetVendorById() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(ID);
        assertEquals(NAME, vendorDTO.getName());
        assertEquals(BASE_VENDOR_URL +"/"+ID, vendorDTO.getVendorUrl());
    }

    @Test
    void testCreateNewVendor() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        when(vendorRepository.save(any())).thenReturn(vendor);

        VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);
        assertEquals(NAME, savedVendorDTO.getName());
        assertEquals(BASE_VENDOR_URL +"/"+ID, savedVendorDTO.getVendorUrl());
    }

    @Test
    void testUpdateVendorById() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));
        when(vendorRepository.save(any())).thenReturn(vendor);

        VendorDTO savedVendorDTO = vendorService.updateVendorById(ID, vendorDTO);
        assertEquals(NAME, savedVendorDTO.getName());
        assertEquals(BASE_VENDOR_URL +"/"+ID, savedVendorDTO.getVendorUrl());
    }

    @Test
    void testDeleteVendorById() {
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(new Vendor()));
        vendorService.deleteVendorById(ID);
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}