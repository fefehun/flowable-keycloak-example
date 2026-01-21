# flowable-keycloak-example

> **Fork Notice**: This is a fork of [premium-minds/flowable-keycloak-example](https://github.com/premium-minds/flowable-keycloak-example) with modifications for **Keycloak 26.x compatibility** and **all 4 Flowable UI modules**.
>
> **Changes made by [Claude Code](https://claude.ai/claude-code):**
>
> **All 4 modules now support Keycloak SSO:**
> - `flowable-ui-modeler` - Process/Form/Decision modeler
> - `flowable-ui-idm` - Identity management
> - `flowable-ui-admin` - Administration console
> - `flowable-ui-task` - Task application
>
> **Modified files per module:**
> - `flowable-ui-*/flowable-ui-*-app/src/main/resources/flowable-default.properties` - Keycloak configuration
> - `flowable-ui-*/flowable-ui-*-conf/pom.xml` - Added flowable-keycloak dependency
> - `flowable-ui-*/flowable-ui-*-conf/src/main/java/.../SecurityConfiguration.java` - OAuth2 security config
>
> See the [keycloak-26-compatibility](https://github.com/fefehun/flowable-keycloak-example/tree/keycloak-26-compatibility) branch for all changes.
>
> **Dependencies:**
> - [fefehun/flowable-keycloak](https://github.com/fefehun/flowable-keycloak/tree/keycloak-26-compatibility) (forked library)
>
> **Used by:** [flowable-keycloak-env](https://github.com/fefehun/flowable-keycloak-env)

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

Required Keycloak client roles:
- `access-modeler`
- `access-idm`
- `access-admin`
- `access-task`
- `access-rest-api`

## Original Keycloak Adaptation

Original commit: https://github.com/premium-minds/flowable-keycloak-example/commit/69dda8c4fb92d9e0c68d766eafeebbaf11a59036
