package CookBook.persistence;

import CookBook.model.Book;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

import java.io.*;

public class PersistenceManager {
    private final static String ENDPOINT = "https://bepblobstorageayoub.blob.core.windows.net/";
    private final static String SASTOKEN = "?sv=2020-02-10&ss=bfqt&srt=sco&sp=rwdlacupx&se=2022-05-13T03:08:56Z&st=2021-05-12T19:08:56Z&spr=https&sig=tS8xH%2Br6r6PdSYh%2FHhgp%2FV7RQPFQAQwkKD4FcM2gz7Q%3D";
    private final static String CONTAINER = "bookcontainer";

    private static final BlobContainerClient blobContainer = new BlobContainerClientBuilder().endpoint(ENDPOINT).sasToken(SASTOKEN).containerName(CONTAINER).buildClient();

    public static void loadBookFromAzure() throws IllegalStateException, IOException {
        if(!blobContainer.exists()){
            throw new IllegalStateException("geen container gevonden,laden standaard waardes..." );
        }

        BlobClient blob = blobContainer.getBlobClient("bookBlob");
        if (!blob.exists()){
            throw new IllegalStateException("geen blob gevonden,laden standaard waardes...");
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        blob.download(baos);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

        try (ObjectInputStream ois = new ObjectInputStream(bais)){
            Book loadedBook = (Book) ois.readObject();
            Book.setBook(loadedBook);
        }catch (IOException | ClassNotFoundException e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    public static void saveBookToAzure() throws IllegalStateException{
        if(!blobContainer.exists()){
            blobContainer.create();
        }

        // maak binaire data van Book Object
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)){
            oos.writeObject(Book.getBook());
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
        byte[] bytes = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

        //Schrijf die data naar Azure opslag
        blobContainer.getBlobClient("bookBlob").upload(bais, bytes.length, true);
    }

}
