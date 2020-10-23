package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository
                .findAll()
                .stream()
                .map(vendorMapper::vendorToVendorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository
                .findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return vendorMapper.vendorToVendorDTO(vendorRepository
                .save(vendorMapper.vendorDTOToVendor(vendorDTO)));
    }

    @Override
    public VendorDTO updateVendorById(Long id, VendorDTO vendorDTO) {
        return vendorRepository
                .findById(id)
                .map(vendor -> {
                    vendor.setName(vendorDTO.getName());
                    return vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
                })
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository
                .findById(id)
                .map(vendor -> {
                    vendorRepository.deleteById(id);
                    return vendor;
                })
                .orElseThrow(() -> new ResourceNotFoundException());
    }
}
