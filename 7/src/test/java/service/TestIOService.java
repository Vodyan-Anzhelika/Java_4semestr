package service;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static service.MockFactory.getDirMock;
import static service.MockFactory.getFileMock;


public class TestIOService {
    @TempDir
    static File tempDir;
    static File rootDir, emptyDir, textDir, binDir, imagesDir, imagesPngDir, makefile1, makefile2;
    static File uselessTxt, essayDocx, oneBin, catTiff, dogTiff, logoPng;
    static final Comparator<File> mockFileComparator = Comparator.comparing(File::getName);
    
    
    @BeforeAll
    static void initMocks() {
        makefile1 = getFileMock("Makefile", "~/");
        makefile2 = getFileMock("Makefile", "~/text/");
        uselessTxt = getFileMock("useless.txt", "~/text/");
        essayDocx = getFileMock("essay.docx", "~/text/");
        oneBin = getFileMock("1.bin", "~/bin/");
        catTiff = getFileMock("cat.tiff", "~/images/");
        dogTiff = getFileMock("dog.tiff", "~/images/");
        logoPng = getFileMock("logo.png", "~/images/png/");
        
        emptyDir = getDirMock("empty", "~/");
        textDir = getDirMock("text", "~/", makefile2, uselessTxt, essayDocx);
        binDir = getDirMock("bin", "~/", oneBin);
        imagesPngDir = getDirMock("png", "~/images/", logoPng);
        imagesDir = getDirMock("images", "~/", catTiff, imagesPngDir, dogTiff);
        rootDir = getDirMock("~", "", makefile1, emptyDir, textDir, binDir, imagesDir);

//      ~/
//      ├ Makefile
//      ├ empty/
//      ├ text/
//      | ├ Makefile
//      | ├ useless.txt
//      | └ essay.docx
//      ├ bin/
//      | └ 1.bin
//      └ images/
//        ├ cat.tiff
//        ├ png/
//        | └ logo.png
//        └ dog.tiff
    }
    
    
    @Test
    void testReadWriteBinaryStream1() throws IOException {
        int[] expected = new int[]{-1, 3, -2147483648, 20, 141, 1, 2};
        int[] actual = new int[7];
        
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            IOService.writeIntArrayToBinaryStream(expected, out);
            
            try (ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray())) {
                IOService.readIntArrayFromBinaryStream(actual, in);
                
                assertAll(
                        () -> assertArrayEquals(expected, actual),
                        () -> assertThrows(IOException.class,
                                () -> IOService.readIntArrayFromBinaryStream(new int[8], in))
                );
            }
        }
    }
    
    
    @Test
    void testReadWriteBinaryStream2() throws IOException {
        File file = new File(tempDir, "test.bin");
        
        int[] expected = new int[]{1, 2, -3, 2000000};
        int[] actual = new int[4];
        
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            IOService.writeIntArrayToBinaryStream(expected, out);
        }
        
        try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {
            IOService.readIntArrayFromBinaryStream(actual, in);
        }
        
        assertArrayEquals(expected, actual);
    }
    
    
    @Test
    void testReadWriteCharStream1() throws IOException {
        int[] expected = new int[]{-1, 3, -2147483648, 20, 141, 1, 2};
        int[] actual = new int[7];
        
        try (StringWriter out = new StringWriter()) {
            IOService.writeIntArrayToCharStream(expected, out);
            String str = out.toString();
            
            try (StringReader in = new StringReader(str)) {
                IOService.readIntArrayFromCharStream(actual, in);
            }
            
            assertAll(
                    () -> assertArrayEquals(expected, actual),
                    () -> assertEquals("-1 3 -2147483648 20 141 1 2 ", str)
            );
        }
    }
    
    
    @Test
    void testReadWriteCharStream2() throws IOException {
        File file = new File(tempDir, "test.txt");
        
        int[] expected = new int[]{1, 2, -3, 2000000};
        int[] actual = new int[4];
        
        try (Writer out = new BufferedWriter(new FileWriter(file))) {
            IOService.writeIntArrayToCharStream(expected, out);
        }
        
        try (Reader in = new BufferedReader(new FileReader(file))) {
            IOService.readIntArrayFromCharStream(actual, in);
        }
        
        assertArrayEquals(expected, actual);
    }
    
    
    @Test
    void testReadCharStreamException() {
        try (StringReader in1 = new StringReader("");
             StringReader in2 = new StringReader("1 2n 3 4 ")) {
            assertAll(
                    () -> assertThrows(IOException.class,
                            () -> IOService.readIntArrayFromCharStream(new int[1], in1)),
                    () -> assertThrows(NumberFormatException.class,
                            () -> IOService.readIntArrayFromCharStream(new int[4], in2))
            );
        }
    }
    
    
    @Test
    void testReadIntArrayFromRandomAccessFile() throws IOException {
        File file = new File(tempDir, "test.bin");
        
        int[] actual1 = new int[10];
        int[] actual2 = new int[3];
        
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            for (int i = 0; i < 10; i++)
                raf.writeInt(i);
            
            IOService.readIntArrayFromRandomAccessFile(actual1, raf, 0);
            IOService.readIntArrayFromRandomAccessFile(actual2, raf, 4);
            
            assertAll(
                    () -> assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, actual1),
                    () -> assertArrayEquals(new int[]{1, 2, 3}, actual2),
                    () -> assertThrows(IOException.class,
                            () -> IOService.readIntArrayFromRandomAccessFile(new int[2], raf, -1))
            );
        }
    }
    
    
    @Test
    void testGetFilesByExtension() {
        List<File> files1 = IOService.getFilesByExtension(imagesDir, "tiff");
        List<File> files2 = IOService.getFilesByExtension(binDir, "bin");
        List<File> files3 = IOService.getFilesByExtension(rootDir, "");
        List<File> files4 = IOService.getFilesByExtension(rootDir, "txt");
        List<File> files5 = IOService.getFilesByExtension(rootDir, null);
        
        files1.sort(mockFileComparator);
        
        assertAll(
                () -> assertEquals(Arrays.asList(catTiff, dogTiff), files1),
                () -> assertEquals(Collections.singletonList(oneBin), files2),
                () -> assertEquals(Collections.singletonList(makefile1), files3),
                () -> assertEquals(Collections.emptyList(), files4),
                () -> assertEquals(Collections.emptyList(), files5)
        );
    }
    
    
    @Test
    void testGetFilesByExtensionNotADirectory() {
        assertThrows(IllegalArgumentException.class, () -> IOService.getFilesByExtension(essayDocx, "txt"));
    }
    
    
    @Test
    void testGetFilesByRegex() {
        List<File> files1 = IOService.getFilesByRegex(textDir, ".*txt");
        List<File> files2 = IOService.getFilesByRegex(rootDir, "\\d.bin");
        List<File> files3 = IOService.getFilesByRegex(rootDir, "^[a-z]*\\.[a-z]*x.?$");
        List<File> files4 = IOService.getFilesByRegex(rootDir, "[a-z]*es[a-z\\.]*");
        List<File> files5 = IOService.getFilesByRegex(rootDir, "[\\w\\.]*n[\\w\\.]*");
        List<File> files6 = IOService.getFilesByRegex(rootDir, "[\\w\\.]*");
        List<File> files7 = IOService.getFilesByRegex(textDir, null);
        
        files3.sort(mockFileComparator);
        files4.sort(mockFileComparator);
        files5.sort(mockFileComparator);
        files6.sort(Comparator.comparing(File::getAbsolutePath));
        
        assertAll(
                () -> assertEquals(Collections.singletonList(uselessTxt), files1),
                () -> assertEquals(Collections.singletonList(oneBin), files2),
                () -> assertEquals(Arrays.asList(essayDocx, uselessTxt), files3),
                () -> assertEquals(Arrays.asList(essayDocx, imagesDir, uselessTxt), files4),
                () -> assertEquals(
                        Arrays.asList(
                                makefile1, binDir, oneBin, emptyDir, imagesDir, catTiff, dogTiff, imagesPngDir, logoPng,
                                textDir, makefile2, essayDocx, uselessTxt
                        ), files6),
                () -> assertEquals(Collections.emptyList(), files7)
        );
    }
    
    
    @Test
    void testGetFilesByRegexNotADirectory() {
        assertThrows(IllegalArgumentException.class, () -> IOService.getFilesByRegex(essayDocx, ".*txt"));
    }
}
