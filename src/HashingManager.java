import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class HashingManager {
    private static final int BUFFER_SIZE = 4096;

    /**
     * Calculates shortcuts for all available shortcut algorithms for a given file.
     *
     * @param file File for which shortcuts are to be calculated.
     * @return A map containing the names of the hash algorithms and their corresponding hashes.
     * @throws IOException If an error occurs while reading the file.
     */
    public Map<String, String> hashFileWithAllAlgorithms(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException("File not found");
        }

        byte[] fileBytes = readBytesFromFile(file);
        Map<String, String> hashValues = new HashMap<>();

        for (String algorithm : getAvailableAlgorithms()) {
            String hash = calculateHash(fileBytes, algorithm);
            hashValues.put(algorithm, hash);
            System.out.println("Hash for " + algorithm + ": " + hash);
        }

        return hashValues;
    }

    /**
     * Reads the contents of the file and returns as an array of bytes.
     *
     * @param file The file whose contents are to be read.
     * @return A byte array containing the contents of the file.
     * @throws IOException If an error occurs while reading the file.
     */
    private byte[] readBytesFromFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;
        StringBuilder stringBuilder = new StringBuilder();
        while ((bytesRead = fis.read(buffer)) != -1) {
            stringBuilder.append(new String(buffer, 0, bytesRead));
        }
        fis.close();
        return stringBuilder.toString().getBytes();
    }

    /**
     * Calculates a hash for the input data using the specified hash algorithm.
     *
     * @param data Input data for which the hash is to be calculated.
     * @param algorithm Name of the hash algorithm.
     * @return Abbreviation of the input data in hexadecimal form.
     */
    private String calculateHash(byte[] data, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashBytes = digest.digest(data);
            StringBuilder hash = new StringBuilder();
            for (byte b : hashBytes) {
                hash.append(String.format("%02x", b));
            }
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("wrong algorithm: " + e.getMessage());
            return "";
        }
    }

    /**
     * Returns a list of available hash algorithms.
     *
     * @return An array containing the names of the available hash algorithms.
     */
    private String[] getAvailableAlgorithms() {
        return new String[]{"MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512"};
    }
}