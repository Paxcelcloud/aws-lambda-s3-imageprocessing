package libs.java.imageprocessing.io;

import libs.java.imageprocessing.DataObject;
import libs.java.imageprocessing.DataReader;
import libs.java.imageprocessing.DataSource;
import libs.java.imageprocessing.DataWriter;
import libs.java.imageprocessing.ImageDataObject;
import libs.java.imageprocessing.aws.s3.S3DataSource;
import libs.java.imageprocessing.aws.s3.io.S3IOFactory;

/**
 * Provides factory which can be implemented for IO operations. See
 * {@link S3IOFactory} implementation.
 * 
 * @author Kuldeep
 *
 * @param <F> object to write
 * @param <E> DataObject containing data and metadata
 */
public abstract class IOFactory<F extends Object, E extends DataObject<F>> {

	
	/**
	 * returns writer
	 * @param data data object
	 * @return data writer
	 */
	public abstract <T extends DataWriter<E>> T getWriter(DataObject<F> data);

	/**
	 * Data reader
	 * @param source from where to read, see {@link S3DataSource}
	 * @return reader 
	 */
	public abstract <T extends DataReader<E>> T getReader(DataSource source);

	// injected hardcoded, can be used other way
	protected static IOFactory instance = new S3IOFactory<>();

	/**
	 * returns default factory which is {@link S3IOFactory}
	 * @return current io factory
	 */
	public static final <F, E extends DataObject<F>> IOFactory<F, E> getDefaultFactory() {
		return instance;
	}
}
