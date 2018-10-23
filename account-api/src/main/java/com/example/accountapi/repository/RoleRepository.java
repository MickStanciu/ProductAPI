package com.example.accountapi.repository;

import com.example.accountapi.model.RoleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RoleRepository {

    private static final Logger log = LoggerFactory.getLogger(RoleRepository.class);

    private static final String ROLE_READ_QUERY = "SELECT ar.id as role_id, ar.name as role_name, ap.name as permission_name " +
            "FROM account_roles ar " +
            "LEFT JOIN account_permissions ap on ar.id = ap.role_fk and ar.tenant_fk = ap.tenant_fk " +
            "WHERE ar.id = ? AND ar.tenant_fk = CAST(? AS uuid)";

    private JdbcTemplate conn;

    @Autowired
    public RoleRepository(JdbcTemplate conn) {
        this.conn = conn;
    }

    public RoleModel getRole(String tenantId, int id) {
        return null;
        /*
        conn.getJdbi().registerRowMapper(RoleModelMapper.class, (rs, ctx) -> new RoleModelMapper(
                rs.getInt("role_id"),
                rs.getString("role_name"),
                rs.getString("permission_name")
        ));

        List<RoleModelMapper> roleModelMapperList = conn.getJdbi().withHandle(handle -> handle
                .createQuery(ROLE_READ_QUERY)
                .bind(0, id)
                .bind(1, tenantId)
                .mapTo(RoleModelMapper.class)
                .list()
        );

        Set<RoleInfoModel> permissions  = new HashSet<>();
        String roleName = null;

        for (RoleModelMapper modelMapper : roleModelMapperList) {
            roleName = modelMapper.name;
            permissions.add(RoleInfoModel.valueOf(modelMapper.permission));
        }

        if (roleName == null || permissions.isEmpty()) {
            return null;
        }

        return RoleModel.builder()
                .withId(id)
                .withName(roleName)
                .withPermissions(permissions)
                .build();
                */
    }
}

class RoleModelMapper {
    public int id;
    public String name;
    public String permission;

    RoleModelMapper(int id, String name, String permission) {
        this.id = id;
        this.name = name;
        this.permission = permission;
    }
}