package com.example.sunshineserver.quest.infrastructure;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PixelConverter {

    public BufferedImage pixelateImage(MultipartFile file, int pixSize) throws IOException {
        // MultipartFile을 BufferedImage로 변환
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(file.getBytes()));

        // Raster 데이터 가져오기
        WritableRaster raster = img.getRaster();

        // 이미지 픽셀화
        for (int y = 0; y < img.getHeight(); y += pixSize) {
            for (int x = 0; x < img.getWidth(); x += pixSize) {
	double[] pixel = raster.getPixel(x, y, new double[3]);
	for (int yd = y; (yd < y + pixSize) && (yd < img.getHeight()); yd++) {
	    for (int xd = x; (xd < x + pixSize) && (xd < img.getWidth()); xd++) {
	        raster.setPixel(xd, yd, pixel);
	    }
	}
            }
        }

        img.setData(raster);
        return img;
    }
}
