package libs.java.imageprocessing;

import java.awt.image.BufferedImage;

/**
 * Exteded just to add type to avoid too much comparison using meta data map
 * 
 * @author Kuldeep
 *
 */
public class ImageDataObject extends AbstractDataObject<BufferedImage> {

	/**
	 * Type of image
	 */
	private ImageType type;
	
	/**
	 * Output processed type from {@link ProcessedType}
	 */
	private ProcessedType processedType;

	public enum ImageType {

		JPEG("jpg"), BMP("bmp"), PNG("png"), INVALID("invalid");
		private String value;
		private ImageType(String value) {
			this.value = value;
		}
		
		public String value (){
			return value;
		}
	}
	public enum ProcessedType {
		NEGATIVE, CONVERSION, THUMBNAIL;
	}

	public ImageDataObject(BufferedImage data, DataSource dataSource) {
		super(data, dataSource);
	}

	public ImageType getType() {
		return type;
	}

	public void setType(ImageType type) {
		this.type = type;
	}

	public ProcessedType getProcessedType() {
		return processedType;
	}

	public void setProcessedType(ProcessedType processedType) {
		this.processedType = processedType;
	}

	@Override
	public String toString() {
		return "ImageDataObject [type=" + type + ", processedType=" + processedType + ", data=" + data + ", source="
				+ source + ", metaData=" + metaData + "]";
	}


}
