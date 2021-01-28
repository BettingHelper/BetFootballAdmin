package sample;

import java.io.*;
import java.nio.file.*;
import java.util.zip.*;
import java.nio.file.attribute.*;

public class ZipDir extends SimpleFileVisitor<Path> {

    public static ZipOutputStream zos;

    private Path sourceDir;

    public ZipDir(ZipOutputStream zos, Path sourceDir) {
        this.sourceDir = sourceDir;
        this.zos = zos;
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) {

        try {
            Path targetFile = sourceDir.relativize(file);

            zos.putNextEntry(new ZipEntry(targetFile.toString()));

            byte[] bytes = Files.readAllBytes(file);
            zos.write(bytes, 0, bytes.length);
            zos.closeEntry();

        } catch (IOException ex) {
            System.err.println(ex);
        }

        return FileVisitResult.CONTINUE;
    }
}