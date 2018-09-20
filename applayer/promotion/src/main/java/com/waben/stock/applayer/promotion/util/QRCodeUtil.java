package com.waben.stock.applayer.promotion.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeUtil {

	public static void write(HttpServletResponse response, String content, int width, int height)
			throws IOException, WriterException {
		response.setContentType("image/png");
		BufferedImage image = createImage(content, width, height);
		ImageIO.write(image, "png", response.getOutputStream());
	}

	public static String create(String content, int width, int height) throws IOException, WriterException {
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		BufferedImage image = createImage(content, width, height);
		ImageIO.write(image, "png", os);
		return Base64.getEncoder().encodeToString(os.toByteArray());
	}

	public static BufferedImage createImage(String content, int width, int height) throws WriterException {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.MARGIN, 1); // 设置白边
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		BufferedImage bi = MatrixToImageWriter.toBufferedImage(bitMatrix);
		int imageWidth = bi.getWidth(null);
		int imageHeight = bi.getHeight(null);
		BufferedImage image = new BufferedImage(width, imageHeight, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < imageWidth; x++) {
			for (int y = 0; y < imageHeight; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}
		return image;
	}
}
