package libs.java.imageprocessing.aws.s3.io;

import java.util.Properties;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import libs.java.imageprocessing.DataObject;
import libs.java.imageprocessing.DataReader;
import libs.java.imageprocessing.DataSource;
import libs.java.imageprocessing.DataWriter;
import libs.java.imageprocessing.io.IOFactory;

/**
 * Implementation of {@link IOFactory}, provides reader and writer
 * implementations
 * 
 * @author Kuldeep
 *
 * @param <F> data 
 * @param <E> {@link DataObject}  
 */
public class S3IOFactory<F extends Object, E extends DataObject<F>> extends IOFactory<F, E> {

	private DataReader<E> reader;
	private DataWriter<E> writer;
	static private Properties p = new Properties();

	static {
		try {
			// load s3 properties configuration from classpath
			p.load(IOFactory.class.getResourceAsStream("/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public S3IOFactory() {

		BasicAWSCredentials creds = new BasicAWSCredentials(p.getProperty("accessKey"), p.getProperty("secretKey"));
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds))
				.build();
		initialize(s3Client);
	}

	private void initialize(AmazonS3 s3Client) {
		S3DataReader s3ByteReader = new S3DataReader(s3Client);
		S3DataWriter s3ByteWriter = new S3DataWriter(s3Client);

		reader = (DataReader<E>) new S3ImageReader(s3ByteReader);
		writer = (DataWriter<E>) new S3ImageWriter(s3ByteWriter);
	}

	@Override
	public <T extends DataWriter<E>> T getWriter(DataObject<F> data) {
		return (T) writer;
	}

	public <T extends DataReader<E>> T getReader(DataSource source) {
		return (T) reader;
	}

}
