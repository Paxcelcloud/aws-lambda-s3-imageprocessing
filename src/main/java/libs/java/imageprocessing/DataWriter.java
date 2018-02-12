package libs.java.imageprocessing;

import java.io.IOException;

import libs.java.imageprocessing.aws.s3.io.S3DataWriter;
import libs.java.imageprocessing.aws.s3.io.S3ImageWriter;

/**
 * Basic interface to write data
 * 
 * @author Kuldeep
 *
 * @param <T>
 *            data object
 */
public interface DataWriter<T> {

	/**
	 * method to be implemented by subclasses to write to the destination
	 * according to provided data source. See implementation
	 * {@link S3DataWriter} and {@link S3ImageWriter}
	 * 
	 * @param data
	 *            object to writeto
	 * @param source
	 *            {@link DataSource}
	 * @return sucess/failture
	 * @throws IOException
	 *             any exception
	 */
	public boolean write(T data, DataSource source) throws IOException;
}
