package com.comm.image.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.amazonaws.services.securitytoken.model.GetSessionTokenResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

@Component
public class AwsS3Wrapper implements FileWrapper {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AmazonS3Client amazonS3Client;

    @Autowired
    private AWSSecurityTokenService stsClient;

    @Value("${aws.s3.file.bucket}")
    private String bucket;

    @Override
    public void upload(InputStream inputStream, String filePath) {
//        try {
//            byte[] bytes = IOUtils.toByteArray(inputStream);
//            ObjectMetadata metadata = new ObjectMetadata();
//            metadata.setContentLength(bytes.length);
//            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, filePath, new ByteArrayInputStream(bytes), metadata);
//            putObjectRequest.setCannedAcl(CannedAccessControlList.Private);
//            amazonS3Client.putObject(putObjectRequest);
//            IOUtils.closeQuietly(inputStream);
//        } catch (IOException e) {
//            logger.error("s3 upload file error", e);
//            throw new FileException(String.format("upload error cause by: %s", e.getMessage()));
//        }
    }

    @Override
    public void copyTo(String filePath, OutputStream outputStream) {
//        try {
//            S3ObjectInputStream objectInputStream = amazonS3Client.getObject(new GetObjectRequest(bucket, filePath)).getObjectContent();
//            IOUtils.copy(objectInputStream, outputStream);
//            IOUtils.closeQuietly(objectInputStream);
//        } catch (IOException e) {
//            logger.error("s3 copy file error", e);
//            throw new FileException(String.format("s3 copy file error cause by: %s", e.getMessage()));
//        }
    }

    @Override
    public String getFileUrl(String filePath) {
        return getFileUrl(filePath, System.currentTimeMillis() + 60 * 60 * 1000);
    }

    private String getFileUrl(String path, long expirationInMillisecond) {
        Date expiration = new Date();
        expiration.setTime(expirationInMillisecond);
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, path);
        request.setMethod(HttpMethod.GET);
        request.setExpiration(expiration);
        return amazonS3Client.generatePresignedUrl(request).toString();
    }

    @Override
    public String getToken() {
        GetSessionTokenRequest getSessionTokenRequest = new GetSessionTokenRequest();
        getSessionTokenRequest.setDurationSeconds(7200);
        GetSessionTokenResult sessionTokenResult = stsClient.getSessionToken(getSessionTokenRequest);
        Credentials session_creds = sessionTokenResult.getCredentials();
        return session_creds.getSessionToken();
    }

    @Override
    public byte[] getFileAndToArray(String filePath) {
//        S3ObjectInputStream objectInputStream = null;
//        try {
//            objectInputStream = amazonS3Client.getObject(new GetObjectRequest(bucket, filePath)).getObjectContent();
//            return IOUtils.toByteArray(objectInputStream);
//        }catch (IOException e) {
//            logger.error("s3 get file error", e);
//            throw new FileException(String.format("s3 get file error cause by: %s", e.getMessage()));
//        }finally {
//            IOUtils.closeQuietly(objectInputStream);
//        }
        return null;
    }
}
