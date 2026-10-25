# Run DevTrack from an Android phone

1. Create/open a Java-capable cloud IDE that supports Java 17, Maven, and port forwarding.
2. Upload or extract the `devtrack` project folder into the workspace.
3. In the terminal, enter the project folder and run `mvn spring-boot:run`.
4. Open the forwarded port 8080 link.
5. H2 is file-backed, so a local demo does not require a separate database service.

This archive does not include Maven Wrapper binaries. If needed, generate them in your IDE with `mvn -N wrapper:wrapper`.
