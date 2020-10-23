package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import static guru.springfamework.controllers.v1.VendorController.BASE_VENDOR_URL;

@Mapper
public interface VendorMapper {
    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    @Mapping(source = "id", target = "vendorUrl", qualifiedByName = "idToVendorUrl")
    VendorDTO vendorToVendorDTO(Vendor vendor);
    Vendor vendorDTOToVendor(VendorDTO vendorDTO);

    @Named("idToVendorUrl")
    static String idToVendorUrl(Long id) {
        return BASE_VENDOR_URL + "/" + id;
    }
}
