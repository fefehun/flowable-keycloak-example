# flowable-keycloak-example

> **Fork Notice**: This is a fork of [premium-minds/flowable-keycloak-example](https://github.com/premium-minds/flowable-keycloak-example) with modifications for **Keycloak 26.x compatibility** and **all 4 Flowable UI modules**.

## Why This Fork?

The original example only supported `flowable-ui-modeler` and was designed for older Keycloak versions. This fork addresses several limitations:

1. **Single Module Support**: The original only had Keycloak integration for the Modeler module. This fork extends SSO support to all 4 Flowable UI applications.

2. **Keycloak 26.x Compatibility**: Keycloak 17+ removed the `/auth` URL prefix and changed token structures. All configuration files needed updates to work with modern Keycloak.

3. **Spring Security Changes**: Flowable's security configuration needed updates for Spring Security 5.7+ which deprecated `WebSecurityConfigurerAdapter`.

4. **Docker Deployment**: The original was designed for standalone development. This fork is optimized for containerized deployment with environment variable configuration.

## Changes Made

**Modified by [Claude Code](https://claude.ai/claude-code):**

### Extended to All 4 Modules

| Module | Original Support | This Fork |
|--------|-----------------|-----------|
| flowable-ui-modeler | ✅ Partial | ✅ Full SSO |
| flowable-ui-idm | ❌ None | ✅ Full SSO |
| flowable-ui-admin | ❌ None | ✅ Full SSO |
| flowable-ui-task | ❌ None | ✅ Full SSO |

### Modified Files Per Module

| File | Change | Reason |
|------|--------|--------|
| `flowable-default.properties` | Added Keycloak configuration | Configure OIDC endpoints, client credentials, and redirect URIs |
| `pom.xml` | Added flowable-keycloak dependency | Include the Keycloak integration library |
| `SecurityConfiguration.java` | Replaced security filter chain | Use `KeycloakCookieFilterRegistrationBean` instead of default Flowable auth |

### Configuration Changes for Keycloak 26.x

- Updated `keycloak.issuer-url` to use `/realms/{realm}` (without `/auth` prefix)
- Updated token validation for new JWT claim structure
- Added support for `access-idm` role (new in this fork)

## Branch

See the [keycloak-26-compatibility](https://github.com/fefehun/flowable-keycloak-example/tree/keycloak-26-compatibility) branch for all changes.

## Dependencies

- **[fefehun/flowable-keycloak](https://github.com/fefehun/flowable-keycloak/tree/keycloak-26-compatibility)** - Forked Keycloak integration library (required)

## Related Projects

- **[flowable-keycloak-env](https://github.com/fefehun/flowable-keycloak-env)** - Docker image built from this fork

---

Example of Flowable UI projects with Keycloak authentication/authorization.

## Supported Modules

| Module | SSO Support | Status |
|--------|-------------|--------|
| flowable-ui-modeler | ✅ | Working |
| flowable-ui-idm | ✅ | Working |
| flowable-ui-admin | ✅ | Working |
| flowable-ui-task | ✅ | Working |

## Build

```bash
mvn clean install -DskipTests
```

## Run (Development)

```bash
cd flowable-ui-modeler
./start-modeler.sh
```

## Docker Build

For Docker deployment, see [flowable-keycloak-env](https://github.com/fefehun/flowable-keycloak-env).

## Keycloak Configuration

### Required Environment Variable

You must set the Keycloak client secret before running:

```bash
export KEYCLOAK_CLIENT_SECRET=your-actual-client-secret
```

Or update the `flowable-default.properties` files in each module with your secret.

### Required Keycloak Client Roles

Configure these roles in your Keycloak client:
- `access-modeler`
- `access-idm`
- `access-admin`
- `access-task`
- `access-rest-api`

## Original Keycloak Adaptation

Original commit: https://github.com/premium-minds/flowable-keycloak-example/commit/69dda8c4fb92d9e0c68d766eafeebbaf11a59036
