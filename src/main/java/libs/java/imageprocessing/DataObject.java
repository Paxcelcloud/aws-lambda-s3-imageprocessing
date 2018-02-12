package libs.java.imageprocessing;

import java.util.HashMap;
import java.util.Map;

/**
 * A generic abstract class representing data to be processed
 * 
 * @author Kuldeep
 *
 * @param <T> data object
 */
public abstract class DataObject<T> {

	protected T data;
	protected DataSource source;

	protected DataObject(T data, DataSource source) {
		this.data = data;
		this.source = source;
	}

	/**
	 * Returns the data object
	 * 
	 * @return data object
	 */
	public T getData() {
		return data;
	}

	/**
	 * Returns the location data is linked to or loaded from. See
	 * {@link DataSource}
	 * 
	 * @return data source
	 */
	public DataSource getDataSource() {
		return source;
	}

	protected Map<String, String> metaData = null;

	/**
	 * Returns meta data as key-value
	 * 
	 * @return meta data
	 */
	public Map<String, String> getMetaData() {
		return metaData;
	}

	/**
	 * Adds meta data to object
	 * 
	 * @param key
	 *            identity of metadata
	 * @param value
	 *            of meta data
	 */
	public void addMetaData(String key, String value) {
		if (metaData == null) {
			metaData = new HashMap<String, String>();
		}
		metaData.put(key, value);
	}

	/**
	 * Sets new meta data
	 * 
	 * @param metaData
	 *            meta data for object
	 */
	public void setMetaData(Map<String, String> metaData) {
		this.metaData = metaData;

	}

	@Override
	public String toString() {
		return "DataObject [data=" + data + ", source=" + source + ", metaData=" + metaData + "]";
	}

}
