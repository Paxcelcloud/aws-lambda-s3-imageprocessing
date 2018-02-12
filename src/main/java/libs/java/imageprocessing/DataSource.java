package libs.java.imageprocessing;

import java.util.Map;

/**
 * Basic data source. Restricted to some fields than having generic key, value fetch
 * @author Kuldeep
 *
 */
public interface DataSource {

	public String getPath ();
	public String getName ();
	public void setName (String name);
	public void setPath (String path);
	public Map<String, String> getProperties ();
	public void addProperty (String key, String value);
}
