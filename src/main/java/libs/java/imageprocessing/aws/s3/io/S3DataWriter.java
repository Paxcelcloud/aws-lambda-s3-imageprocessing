package libs.java.imageprocessing.aws.s3.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import libs.java.imageprocessing.DataObject;
import libs.java.imageprocessing.DataSource;
import libs.java.imageprocessing.DataWriter;

public class S3DataWriter implements DataWriter<DataObject<byte[]>> {
	private AmazonS3 s3Client;

	public S3DataWriter(AmazonS3 s3Client) {
		this.s3Client = s3Client;
	}

	public boolean write(DataObject<byte[]> data, DataSource source) throws IOException {
		if (data.getData() == null || data.getData().length < 1) {
			System.err.println("No data to write to S3. " + data);
			return false;
		}
		ObjectMetadata metaData = new ObjectMetadata();
		if (data.getMetaData() != null) {
			for (String key : data.getMetaData().keySet()) {
				metaData.addUserMetadata(key, data.getMetaData().get(key));
			}
		}
		if (metaData.getContentLength() < 1) {
			metaData.setContentLength(data.getData().length);
		}
		s3Client.putObject(source.getPath(), source.getName(), new ByteArrayInputStream(data.getData()), metaData);
		return true;
	}

}
