package guru.springfamework.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static guru.springfamework.controllers.v1.VendorController.BASE_VENDOR_URL;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VendorControllerTest {
    private static final Long ID = 1L;
    public static final String NAME = "name";
    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllVendors() throws Exception {
        List<VendorDTO> vendorList = Arrays.asList(new VendorDTO(), new VendorDTO(), new VendorDTO());
        when(vendorService.getAllVendors()).thenReturn(vendorList);

        mockMvc.perform(get(BASE_VENDOR_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(3)));
    }

    @Test
    void testGetVendorById() throws Exception {
        VendorDTO vendor = new VendorDTO();
        vendor.setVendorUrl(BASE_VENDOR_URL+"/"+ID);
        vendor.setName(NAME);

        when(vendorService.getVendorById(anyLong())).thenReturn(vendor);

        mockMvc.perform(get(BASE_VENDOR_URL +"/"+1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(BASE_VENDOR_URL+"/"+ID)));
    }

    @Test
    void testCreateNewVendor() throws Exception {
        VendorDTO vendor = new VendorDTO();
        vendor.setVendorUrl(BASE_VENDOR_URL+"/"+ID);
        vendor.setName(NAME);

        when(vendorService.createNewVendor(any())).thenReturn(vendor);

        mockMvc.perform(post(BASE_VENDOR_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(vendor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(BASE_VENDOR_URL+"/"+ID)));
    }

    @Test
    void testUpdateVendorById() throws Exception {
        VendorDTO vendor = new VendorDTO();
        vendor.setVendorUrl(BASE_VENDOR_URL+"/"+ID);
        vendor.setName(NAME);

        when(vendorService.updateVendorById(anyLong(), any())).thenReturn(vendor);

        mockMvc.perform(put(BASE_VENDOR_URL+"/"+1)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(vendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(BASE_VENDOR_URL+"/"+ID)));
    }

    @Test
    void testDeleteVendorById() throws Exception {
        mockMvc.perform(delete(BASE_VENDOR_URL+"/"+1))
                .andExpect(status().isOk());
        verify(vendorService, times(1)).deleteVendorById(anyLong());
    }
}
