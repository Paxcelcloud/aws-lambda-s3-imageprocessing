package libs.java.imageprocessing.aws.s3;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification.S3BucketEntity;
import com.amazonaws.services.s3.event.S3EventNotification.S3Entity;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.event.S3EventNotification.S3ObjectEntity;

import libs.java.imageprocessing.AppInitializer;
import libs.java.imageprocessing.DataObject;
import libs.java.imageprocessing.DataSource;
import libs.java.imageprocessing.ImageDataObject;
import libs.java.imageprocessing.ImageProcessor;
import libs.java.imageprocessing.ImageDataObject.ImageType;
import libs.java.imageprocessing.aws.s3.util.Util;
import libs.java.imageprocessing.aws.util.ImageUtil;
import libs.java.imageprocessing.io.IOFactory;
import libs.java.imageprocessing.processor.ImageProcessorFactory;

/**
 * 
 * Entry point for S3 event, It is called by S3 on new object creation, as we
 * configured in S3
 * 
 * @author Kuldeep
 *
 */
public class S3EventHandler {

	private boolean initialized = false;

	private void initialize() {
		AppInitializer.instance().initialize();
		initialized = true;

	}

	/**
	 * Lambda function
	 * 
	 * @param event
	 *            {@link S3Event} having details about the event and
	 *            bucket/object
	 * @throws IOException
	 *             and io exception
	 */
	public void onNewObject(S3Event event) throws IOException {
		if (!initialized) {
			initialize();
		}
		if (event == null) {
			System.err.println("Event can't be null");
			return;
		}
		List<S3EventNotificationRecord> records = event.getRecords();
		if (records == null || records.size() < 1) {
			System.err.println("Event contains no records " + records);
		}

		for (S3EventNotificationRecord record : records) {
			System.out.println("Record received, event " + record.getEventName() + " bucket "
					+ record.getS3().getBucket().getName() + " Object " + record.getS3().getObject().getKey());
			if (!record.getEventName().contains("ObjectCreated:")) {
				System.out.println("Event was not of object creation in bucket " + record.getEventName());
				return;

			}
			// on delete of object you can delete other processed event if you
			// want
			IOFactory<BufferedImage, DataObject<BufferedImage>> io = IOFactory.getDefaultFactory();
			DataSource s = new S3DataSource(record.getS3().getBucket().getName(), record.getS3().getObject().getKey());
			DataObject<BufferedImage> image = io.getReader(s).read(s);
			ImageDataObject imageObj = new ImageDataObject(image.getData(), s);
			imageObj.setType(ImageUtil.getImageType(s.getName()));
			imageObj.setMetaData(image.getMetaData());
			if (imageObj.getType() == null || imageObj.getType() == ImageType.INVALID) {
				System.err.println("Not an image (or supported) image to operate on");
				return;
			}
			/*
			 * loop through registered processor, if you want to add new
			 * processing on image, create and implementation of ImageProcessor
			 * and register it to ImageProcessorFactory
			 */
			for (ImageProcessor processor : ImageProcessorFactory.instance().getImageProcessors()) {
				List<ImageDataObject> output = processor.processImage(imageObj);
				for (ImageDataObject imageOut : output) {
					// get the target, where write to
					DataSource target = Util.findDestinationDataSource(imageOut, s.getPath());
					// ImageDataObject<BufferedImage> imageToWrite = new
					// ImageDataObject(imageObj.getData(),
					// imageObj.getDataSource());
					// imageToWrite.setMetaData(imageOut.getMetaData());
					io.getWriter(imageOut).write(imageOut, target);
				}
			}
			System.out.println("Completed processing image " + imageObj);
		}
	}

	// test method
	public static void main(String[] args) throws IOException {
	// test by simualting s3 event. Always test your code before deploying,
		// it should be working as per your business logic
		S3EventHandler obj = new S3EventHandler();
		List<S3EventNotificationRecord> records = new ArrayList<>();
		S3BucketEntity bucket = new S3BucketEntity("s3-integrations-bucket2", null, null);
		S3ObjectEntity object = new S3ObjectEntity("abc.jpg", 1L, null, null, null);
		S3Entity entity = new S3Entity(null, bucket, object, null);
		records.add(
				new S3EventNotificationRecord("us-east1", com.amazonaws.services.s3.model.S3Event.ObjectCreated.name(),
						null, "1", null, null, null, entity, null));
		S3Event event = new S3Event(records);
		obj.onNewObject(event);
	}
}
