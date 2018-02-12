package libs.java.imageprocessing;

/**
 * Default data store with parameterized operation
 * @author Kuldeep
 *
 * @param <T> data type
 */
public class AbstractDataObject<T> extends DataObject<T> {

	public AbstractDataObject(T data, DataSource dataSource) {
		super(data, dataSource);
	}

	@Override
	public String toString() {
		return "AbstractDataObject [data=" + data + ", source=" + source + ", metaData=" + metaData + "]";
	}

}
