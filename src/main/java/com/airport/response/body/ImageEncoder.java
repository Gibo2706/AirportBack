package com.airport.response.body;

import java.util.Base64;

import org.springframework.stereotype.Service;


@Service
public class ImageEncoder {

	public String encode(byte[] image) {
		if (image == null)
			return "";
		return Base64.getEncoder().encodeToString(image);
	}
	
	public String encode(String image) {
		if (image == null)
			return "";
		byte[] imageBytes = new byte[image.length()];
		for (int i = 0; i < image.length(); i++) {
			imageBytes[i] = (byte) image.charAt(i);
		}
		return Base64.getEncoder().encodeToString(imageBytes);
	}
	
	public ImageEncoder getImageEncoder() {
		return new ImageEncoder();
	}
}
