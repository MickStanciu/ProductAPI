//package com.example.common.security;
//
//import io.jsonwebtoken.Claims;
//
//public class TokenUtilTest {
//    private static final String tenantId = "testTenant";
//    private static long accountId;
//    private static final Integer roleId = 44;
//    private static String token = null;
//    private static Claims claims;
//
//
//    @BeforeClass
//    public static void setup() {
//        accountId = 4444444L;
//        token = TokenUtil.generateToken(tenantId, accountId, roleId);
//        assertNotEquals(token, "");
//        claims = TokenUtil.getClaims(token);
//    }
//
//    @Test
//    void testTokenIssuer() {
//        assertEquals(TokenUtil.ISSUER, claims.getIssuer());
//    }
//
//    @Test
//    void testTokenExpTime() {
//        assertEquals(TokenUtil.TTL, claims.getExpiration().getTime() - claims.getIssuedAt().getTime());
//    }
//
//    @Test
//    void testTokenTenant() {
//        assertEquals(tenantId, claims.get("tenantId"));
//    }
//
//    @Test
//    void testTokenRole() {
//        assertEquals(roleId, claims.get("roleId"));
//    }
//
//    @Test
//    void testTokenAccount() {
//        Number tmp = (Number) claims.get("accountId");
//        assertEquals(accountId, tmp.longValue());
//    }
//
//    @Test
//    void testValidateTruthy() {
//        assertTrue(TokenUtil.validateToken(token));
//    }
//
//    @Test
//    void testValidateFalsy() {
//        assertFalse(TokenUtil.validateToken("x"));
//        assertFalse(TokenUtil.validateToken(null));
//    }
//}