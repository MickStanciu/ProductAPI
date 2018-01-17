package com.example.account.service;

import com.example.account.dao.RoleDao;
import com.example.account.model.Role;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

@Stateless
public class RoleService {

    private static final Logger log = Logger.getLogger(RoleService.class);

    @Inject
    private RoleDao roleDao;

    public Optional<Role> getRole(String tenantId, int roleId) {
        return roleDao.getRole(tenantId, roleId);
    }
}
