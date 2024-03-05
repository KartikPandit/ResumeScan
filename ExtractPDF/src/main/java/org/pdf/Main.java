package org.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Directory containing PDF files
        File directory = new File("C:\\Users\\kartik\\Desktop\\resumes");

        // Keywords to search for
        String[] keywords = {"netiq", "java", "spring"};

        // Output file to store names of PDFs containing keywords
        File outputFile = new File("C:\\Users\\kartik\\Desktop\\output.txt");

        try {
            FileWriter writer = new FileWriter(outputFile);

            // Iterate over PDF files in the directory
            for (File file : directory.listFiles()) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".pdf")) {
                    if (containsKeywords(file, keywords)) {
                        writer.write(file.getName() + System.lineSeparator());
                    }
                }
            }

            writer.close();
            System.out.println("Output file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean containsKeywords(File file, String[] keywords) throws IOException {
        PDDocument document = PDDocument.load(file);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();

        for (String keyword : keywords) {
            if (!(text.toLowerCase().contains(keyword.toLowerCase()))) {
                return false;
            }
        }
        return true;
    }
}