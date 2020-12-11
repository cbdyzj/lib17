# Java Modularity

Similar to how classes from the `java.lang` package are implicitly imported into source files. Modules do not need to explicitly declare that they depend on the `java.base` module

### requires …

Correspond to the `implementation` of Gradle, both a runtime and a compile-time dependency

### requires static …

Correspond to the `compileOnly` of Gradle, a compile-time-only dependency

### requires transitive …

Correspond to the `api` of Gradle, force any downstream consumers also to read our required dependencies

### exports …

Public types in the package are accessible. By default, a module doesn't expose any of its API to other modules

### exports … to …

Public types in the package are accessible by specific modules

### uses

Designate the services our module consumes with the uses directive

### provides … with

A module can also be a service provider that other modules can consume.

### open

Simply open the entire module up to allow full reflection as older versions of Java did

### opens

Use the opens directive to expose specific packages

### opens … to …

Selectively open our packages to a pre-approved list of modules, in this case, using the opens … to … directive

## Reference resources

- https://www.baeldung.com/java-9-modularity
