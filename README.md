# Nexori API

`nexori-api` is the lightweight public Java API for Nexori integrations.

This project is intended for mod developers who need to compile against Nexori's
public contracts, especially `io.github.hyjn.nexori.plugin.api.minigame`.

Server owners should install the full `nexori-plugin.jar` at runtime. This jar
is a developer compile-time artifact and is not the Nexori runtime plugin.

## Usage

Use this artifact as `compileOnly`.

```gradle
dependencies {
    compileOnly "io.github.hyjn:nexori-api:2.3.0"
}
```

For local development before publishing:

```gradle
dependencies {
    compileOnly files("../nexori-api/build/libs/nexori-api-2.3.0.jar")
}
```

Do not shade or bundle `nexori-api` into your minigame jar. The installed
`nexori-plugin.jar` provides these API classes at runtime.
