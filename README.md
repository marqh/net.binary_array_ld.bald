# Binary Array Linked Data

[Kotlin](https://kotlinlang.org/) library and CLI for Binary Array Linked Data (BALD) functionality.
* NetCDF to RDF conversion according to [OGC draft specification](http://docs.opengeospatial.org/DRAFTS/19-002.html).

This project consists of the following modules:
* **binary-array-ld-lib** Core library containing BALD functionality. In particular, binary array to linked data (RDF) conversion.
RDF representations are provided by [Apache Jena](https://jena.apache.org/).
* **binary-array-ld-netcdf** NetCDF implementation of binary array concepts.
* **binary-array-ld-cli** Command line interface for converting NetCDF metadata to RDF.
* **binary-array-ld-test** Common test utilities used by other modules.
* **binary-array-ld-demo** Demonstrations of BALD API usage.

## Usage

This project can be used either as a library or as a command line application.

### Library

To use the BALD core library, add the following dependency to your Maven project:

```xml
<dependency>
    <groupId>net.binary-array-ld</groupId>
    <artifactId>binary-array-ld-lib</artifactId>
    <version>${bald.version}</version>
</dependency>
```

You can implement the `net.bald.BinaryArray` interface with your own metadata representations and supply them to the API.

For NetCDF metadata files, you can use the pre-made NetCDF implementation in the `binary-array-ld-netcdf` module.
To use this module, add the following dependency to your Maven project:

```xml
<dependency>
    <groupId>net.binary-array-ld</groupId>
    <artifactId>binary-array-ld-netcdf</artifactId>
    <version>${bald.version}</version>
</dependency>
```

#### Example
Kotlin:
```kotlin
val ba = NetCdfBinaryArray.create("/path/to/input.nc", "http://test.binary-array-ld.net/example")
val model = ModelBinaryArrayConverter.convert(ba)
File("/path/to/output.ttl").outputStream.use { output ->
    model.write(output, "ttl")
}
```
Java:
```
BinaryArray ba = NetCdfBinaryArray.create("/path/to/input.ttl", "http://test.binary-array-ld.net/example");
Model model = ModelBinaryArrayConverter.convert(ba);

try (OutputStream output = new FileOutputStream("/path/to/output.ttl")) {
    model.write(output, "ttl");
}
```

### Command Line Interface
( TODO )



