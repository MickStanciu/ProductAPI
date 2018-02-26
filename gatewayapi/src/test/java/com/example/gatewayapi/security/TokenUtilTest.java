package com.example.gatewayapi.security;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TokenUtilTest {
    private String tenantId = "testTenant";
    private String accountName = "Rick and Morty";
    private String roleName = "admin";
    private String token = null;
    private Claims claims;


    @BeforeAll
    public void setup() {
        token = TokenUtil.generateToken(tenantId, accountName, roleName);
        assertNotEquals(token, "");
        claims = TokenUtil.getClaims(token);
    }

    @Test
    void testTokenIssuer() {
        assertEquals(TokenUtil.ISSUER, claims.getIssuer());
    }

    @Test
    void testTokenExpTime() {
        assertEquals(TokenUtil.TTL, claims.getExpiration().getTime() - claims.getIssuedAt().getTime());
    }

    @Test
    void testTokenTenant() {
        assertEquals(tenantId, claims.get("tenantId"));
    }

    @Test
    void testTokenRole() {
        assertEquals(roleName, claims.get("roleName"));
    }

    @Test
    void testTokenName() {
        assertEquals(accountName, claims.get("accountName"));
    }

    @Test
    void testValidateTruthy() {
        assertTrue(TokenUtil.validateToken(token));
    }

    @Test
    void testValidateFalsy() {
        assertFalse(TokenUtil.validateToken("x"));
        assertFalse(TokenUtil.validateToken(null));
    }
}
