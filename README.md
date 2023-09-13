# ejson-shell-parser-java-poc

Poc to parse shell bson to an `org.bson.Document` that can be used with the Java Driver. 
`ejson-shell-parser` is bundled in a single js file, and executed as a binding for a GraalVM context.

To re-generated the bundle:

```
./gradlew webpack
```
