package libs.java.imageprocessing.aws.s3.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import libs.java.imageprocessing.AbstractDataObject;
import libs.java.imageprocessing.DataSource;
import libs.java.imageprocessing.DataWriter;
import libs.java.imageprocessing.ImageDataObject;
import libs.java.imageprocessing.ImageDataObject.ImageType;

/**
 * S3 image writer, converts image to byte[] and leverages {@link S3DataWriter} to write byte[] data
 * @author Kuldeep
 *
 */
public class S3ImageWriter implements DataWriter<ImageDataObject> {

	private S3DataWriter writer;

	public S3ImageWriter(S3DataWriter writer) {
		this.writer = writer;
	}

	@Override
	public boolean write(ImageDataObject data, DataSource source) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageType imageType = data.getType();
		if (imageType == null) {
			System.err.println("Not a supported image type to write to.");
			System.out.println(data);
			return false;
		}
		ImageIO.write(data.getData(), imageType.name(), os);
		writer.write(new AbstractDataObject<byte[]>(os.toByteArray(), source), source);
		return true;
	}


}
