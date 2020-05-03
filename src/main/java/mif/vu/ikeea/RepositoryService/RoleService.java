package mif.vu.ikeea.RepositoryService;

import mif.vu.ikeea.Entity.Repository.RoleRepository;
import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Enums.ERole;
import mif.vu.ikeea.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Transactional
    public void update(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    public Role findByName(ERole erole) throws ResourceNotFoundException {
        Role role = roleRepository.findByName(erole)
                .orElseThrow(() -> new ResourceNotFoundException("User Role not set."));

        return role;
    }

}
