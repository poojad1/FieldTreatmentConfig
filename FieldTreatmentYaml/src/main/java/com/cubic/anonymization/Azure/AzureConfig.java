package com.cubic.anonymization.Azure;


import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.SharedAccessAccountPermissions;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.Properties;

public class AzureConfig {

    private final String PROPERTIES_FILE = "config.properties";
    private InputStream propertiesInputStream;
    private Properties properties;
    private AzureBlobReferenceDetails azureBlobReferenceDetails;
    public AzureConfig(){
        try{
            this .properties = getProperties();
            this.azureBlobReferenceDetails = setAzureBlobReferenceDetails();
        }catch(Exception e ){
            System.out.println("Exception occured while creating Azure  configurations message  : "+e.getMessage());
        }
    }


    public Properties getProperties() throws IOException {
        Properties properties = new Properties();
        propertiesInputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
        if(propertiesInputStream !=null){
           properties.load(propertiesInputStream);
        }else{
            throw new FileNotFoundException("property file "+PROPERTIES_FILE);
        }
        return properties;
        }


    public String getAzureStorageConnectionString() {

        String storageConnectionString = "";
        try {
            Properties properties  = this.getProperties();
            storageConnectionString = "DefaultEndpointsProtocol=https;" +
                    "AccountName=" + properties.getProperty("azure.storage.account-name") + ";" +
                    "AccountKey=" + properties.getProperty("azure.storage.account-key1");
        }catch (Exception e){
            System.out.println("Issue creating connection string " + e.getMessage());
        }
        return storageConnectionString;
    }

    public AzureBlobReferenceDetails setAzureBlobReferenceDetails()  {
        AzureBlobReferenceDetails azureBlobReferenceDetails = new AzureBlobReferenceDetails();
        try {
            azureBlobReferenceDetails.setStorageAccount(CloudStorageAccount.parse(getAzureStorageConnectionString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        azureBlobReferenceDetails.setBlobClient(azureBlobReferenceDetails.getStorageAccount().createCloudBlobClient());
        try {
            azureBlobReferenceDetails.setContainer(azureBlobReferenceDetails.getBlobClient().getContainerReference(this.properties.getProperty("azure.storage.container")));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        }
        return azureBlobReferenceDetails;
    }

    public InputStreamReader getYamlStreamFromAzure(String filename)  {
        //CloudBlobClient blobClient = this.azureBlobReferenceDetails.getBlobClient();
        CloudBlobContainer container = this.azureBlobReferenceDetails.getContainer();
        CloudBlockBlob cloudBlockBlob = null;
        try {
            cloudBlockBlob = container.getBlockBlobReference(filename);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(cloudBlockBlob.openInputStream());
        } catch (StorageException e) {
            e.printStackTrace();
        }
        return inputStreamReader;
    }


}

