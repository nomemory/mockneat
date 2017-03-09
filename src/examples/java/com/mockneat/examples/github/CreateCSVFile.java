package com.mockneat.examples.github;

import com.mockneat.mock.MockNeat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;
import static java.util.Locale.GERMANY;

/**
 * Created by andreinicolinciobanu on 09/03/2017.
 */
public class CreateCSVFile {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();
        final Path path = Paths.get("./test.csv");

        m.fmt("#{id},#{first},#{last},#{email},#{salary}")
         .param("id", m.longSeq())
         .param("first", m.names().first())
         .param("last", m.names().last())
         .param("email", m.emails())
         .param("salary", m.money().locale(GERMANY).range(2000, 5000))
         .list(1000)
         .consume(list -> {
             try { Files.write(path, list, CREATE, WRITE); }
             catch (IOException e) { e.printStackTrace(); }
         });
    }
}
