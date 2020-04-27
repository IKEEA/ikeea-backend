package mif.vu.ikeea.Entity.Repository;

import mif.vu.ikeea.Entity.ApplicationUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends CrudRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByEmail(String email);

    Optional<ApplicationUser> findByEmailAndEnabled(String email, Boolean enabled);

    Optional<ApplicationUser> findByToken(String token);

    Boolean existsByEmail(String email);

    Iterable<ApplicationUser> findAllByManagerId(Long userId);

    @Modifying
    @Query("UPDATE ApplicationUser u SET u.restrictionDays = :restrictionDays WHERE u.id IN (:ids)")
    Integer updateRestrictionDays(@Param("restrictionDays") Integer restrictionDays, @Param("ids") Set<Long> ids);
}
