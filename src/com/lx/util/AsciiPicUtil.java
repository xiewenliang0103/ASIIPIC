/*
 * Copyright (C) 2017 ShenZhen LiXiang Software Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市理想软件有限公司
 */
package com.lx.util;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import io.korhner.asciimg.image.AsciiImgCache;
import io.korhner.asciimg.image.character_fit_strategy.BestCharacterFitStrategy;
import io.korhner.asciimg.image.character_fit_strategy.ColorSquareErrorFitStrategy;
import io.korhner.asciimg.image.character_fit_strategy.StructuralSimilarityFitStrategy;
import io.korhner.asciimg.image.converter.AsciiToImageConverter;
import io.korhner.asciimg.image.converter.AsciiToStringConverter;
import io.korhner.asciimg.image.converter.GifToAsciiConvert;

/**
 * 图片转为Ascii字符
 * 
 * @version 2018年5月31日下午4:39:10
 * @author zhuwenbin
 */
public class AsciiPicUtil {

	public static void main(String[] args) {
		// 静态图片
		// String imgPath = System.getProperty("user.dir") + File.separator +
		// "img/蜡笔小新.jpg";
		// String asciimgPath = System.getProperty("user.dir") + File.separator
		// + "img/蜡笔小新-ascii.jpg";
		// generateAsciiPic(imgPath, asciimgPath);
		// 动态图片
		String imgPath2 = System.getProperty("user.dir") + File.separator + "img/谢谢.gif";
		String asciimgPath2 = System.getProperty("user.dir") + File.separator + "img/谢谢-ascii.gif";
		generateAsciiGifPic(imgPath2, asciimgPath2);
	}

	/**
	 * 
	 * 生成字符图片
	 * 
	 * @version 2018年5月31日下午5:29:24
	 * @author zhuwenbin
	 * @param imgPath
	 * @param asciimgPath
	 */
	public static void generateAsciiPic(String imgPath, String asciimgPath) {
		try {
			// initialize caches
			AsciiImgCache smallFontCache = AsciiImgCache.create(new Font("Courier", Font.BOLD, 6));

			// load image
			BufferedImage portraitImage = ImageIO.read(new File(imgPath));

			// initialize algorithms
			BestCharacterFitStrategy squareErrorStrategy = new ColorSquareErrorFitStrategy();
			BestCharacterFitStrategy ssimStrategy = new StructuralSimilarityFitStrategy();

			// initialize converters
			AsciiToImageConverter imageConverter = new AsciiToImageConverter(smallFontCache, squareErrorStrategy);
			AsciiToStringConverter stringConverter = new AsciiToStringConverter(smallFontCache, ssimStrategy);

			// small font images, ssim
			imageConverter.setCharacterCache(smallFontCache);
			imageConverter.setCharacterFitStrategy(ssimStrategy);
			ImageIO.write(imageConverter.convertImage(portraitImage), "png", new File(asciimgPath));

			// string converter, output to console
			System.out.println(stringConverter.convertImage(portraitImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 生成gif字符图片
	 * 
	 * @version 2018年5月31日下午5:29:03
	 * @author zhuwenbin
	 * @param imgPath
	 * @param asciimgPath
	 */
	public static void generateAsciiGifPic(String imgPath, String asciimgPath) {
		// initialize caches
		AsciiImgCache smallFontCache = AsciiImgCache.create(new Font("Courier", Font.BOLD, 6));
		// initialize ssimStrategy
		BestCharacterFitStrategy ssimStrategy = new StructuralSimilarityFitStrategy();

		int delay = 100;// ms

		GifToAsciiConvert asciiConvert = new GifToAsciiConvert(smallFontCache, ssimStrategy);

		asciiConvert.convertGitToAscii(imgPath, asciimgPath, delay, 0);
	}

}
