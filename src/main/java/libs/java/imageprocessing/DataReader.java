package libs.java.imageprocessing;

import java.io.IOException;

/**
 * Interface to provide read operation using provided data source
 * 
 * @author Kuldeep
 *
 * @param <T> object to read
 */
public interface DataReader<T> {

	/**
	 * abstract method, to be implemented by sub class to provide read
	 * using datasource 
	 * @param source {@link DataSource}
	 * @return data object
	 * @throws IOException throws any IOException
	 */
	public T read(DataSource source) throws IOException;
}
