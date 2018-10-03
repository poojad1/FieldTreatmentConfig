package com.cubic.anonymization.Azure;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;

public class AzureBlobReferenceDetails {
    private CloudStorageAccount storageAccount;
    private CloudBlobClient blobClient;
    private CloudBlobContainer container;

    public CloudStorageAccount getStorageAccount() {
        return storageAccount;
    }

    public void setStorageAccount(CloudStorageAccount storageAccount) {
        this.storageAccount = storageAccount;
    }

    public CloudBlobClient getBlobClient() {
        return blobClient;
    }

    public void setBlobClient(CloudBlobClient blobClient) {
        this.blobClient = blobClient;
    }

    public CloudBlobContainer getContainer() {
        return container;
    }

    public void setContainer(CloudBlobContainer container) {
        this.container = container;
    }
}
