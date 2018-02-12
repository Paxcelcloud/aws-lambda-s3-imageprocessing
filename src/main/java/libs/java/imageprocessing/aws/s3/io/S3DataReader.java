package libs.java.imageprocessing.aws.s3.io;

import java.io.IOException;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;

import libs.java.imageprocessing.AbstractDataObject;
import libs.java.imageprocessing.DataObject;
import libs.java.imageprocessing.DataReader;
import libs.java.imageprocessing.DataSource;
import libs.java.imageprocessing.aws.s3.util.Util;

/**
 * Reader for S3 data, read data as byte[]
 * @author Kuldeep
 *
 */
public class S3DataReader implements DataReader<DataObject<byte[]>> {
	private AmazonS3 s3Client;

	public S3DataReader(AmazonS3 s3Client) {
		this.s3Client = s3Client;
	}

	public DataObject<byte[]> read(DataSource source) throws IOException{
		
		if (source == null){
			System.err.println("Datasource can't be null");
		}
		try {
		
		S3Object object = s3Client.getObject(source.getPath(), source.getName());
		if (object == null){
			System.err.println("Unable to read object");
			return null;
		}
			final byte[] imageByteArray = Util.getContents(object.getObjectContent());
			DataObject<byte[]> data = new AbstractDataObject<byte[]>(imageByteArray, source);
			data.addMetaData("Content-Length", imageByteArray.length + "");
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	
	}

}
