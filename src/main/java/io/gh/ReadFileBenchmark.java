package io.gh;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

public class ReadFileBenchmark {
    private static final String FILE = "./measurements_part_1.txt";

    public static void main(String[] args) throws IOException {
        var st = System.currentTimeMillis();
        var path = Paths.get(FILE);
        var counter = new AtomicInteger();
        Files.lines(path)
                .parallel()
                .forEach(_ -> counter.incrementAndGet());
        System.out.println("FileChannel cost:" + (System.currentTimeMillis() - st));
        System.out.println("Lines:" + counter.get());

        st = System.currentTimeMillis();
        var reader = Files.newBufferedReader(path);
        reader.lines()
                .parallel()
                .forEach(_ -> counter.decrementAndGet());
        System.out.println("BufferReader cost:" + (System.currentTimeMillis() - st));

    }
}
