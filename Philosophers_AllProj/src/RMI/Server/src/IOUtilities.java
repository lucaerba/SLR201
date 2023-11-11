package RMI.Server.src;

import java.io.*;

public class IOUtilities {

    private Writer writer;
    private Reader reader;

    public IOUtilities(Writer writer) {
        this.writer = writer;
    }

    public IOUtilities(Reader reader) {
        this.reader = reader;
    }

    public void write(Object content) {
        try {
            if (content instanceof String) {
                writer.write((String) content);
                writer.flush(); // Ensure data is written immediately
            } else {
                throw new IllegalArgumentException("Il tipo di contenuto non è supportato.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Errore durante la scrittura.", e);
        }
    }

    public String read() {
        StringBuilder sb = new StringBuilder();
        try {
            int charRead;
            while ((charRead = reader.read()) != '\n') {
                sb.append((char) charRead);
            }
        } catch (IOException e) {
            throw new RuntimeException("Errore durante la lettura.", e);
        }
        return sb.toString();
    }

    public void close() {
        try {
            if (writer != null) {
                writer.close();
            } else if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Errore durante la chiusura.", e);
        }
    }

    public static IOUtilities forFile(String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            return new IOUtilities(fileWriter);
        } catch (IOException e) {
            throw new RuntimeException("Errore durante l'apertura del file.", e);
        }
    }

    public static IOUtilities forOutputStream(OutputStream outputStream) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        return new IOUtilities(outputStreamWriter);
    }

    public static IOUtilities forInputStream(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new IOUtilities(inputStreamReader);
    }

    public static IOUtilities forKeyboard() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        return new IOUtilities(new BufferedReader(inputStreamReader));
    }
}
