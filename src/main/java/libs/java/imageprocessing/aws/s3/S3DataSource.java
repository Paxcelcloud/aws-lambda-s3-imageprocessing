package libs.java.imageprocessing.aws.s3;

import java.util.HashMap;
import java.util.Map;

import libs.java.imageprocessing.DataSource;

/**
 * S3 data source, contains information about S3 bucket, key and any data source
 * properties
 * 
 * @author Kuldeep
 *
 */
public class S3DataSource implements DataSource {

	private String path, name;
	private Map<String, String> properites = new HashMap<>();

	public S3DataSource(String bucket, String key) {
		this.path = bucket;
		this.name = key;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public Map<String, String> getProperties() {
		// TODO Auto-generated method stub
		return properites;
	}

	public void addProperty(String key, String value) {
		properites.put(key, value);

	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPath(String path) {
		this.path = path;

	}

	@Override
	public String toString() {
		return "S3DataSource [path=" + path + ", name=" + name + ", properites=" + properites + "]";
	}

}
