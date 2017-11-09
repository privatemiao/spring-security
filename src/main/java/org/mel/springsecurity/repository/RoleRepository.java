package org.mel.springsecurity.repository;

import org.mel.springsecurity.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
