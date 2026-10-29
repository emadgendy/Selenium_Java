package com.custom.library;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class VideoRecorder {
	
	final static  Logger logger = Logger.getLogger(VideoRecorder.class);
	
	static DateFormat  df = new SimpleDateFormat ("dd_mm_yy_HH_MM_SS");
	static Date dateObj = new Date();
	
	static ExtentHtmlReporter htmlReorter = new ExtentHtmlReporter("C:\\Users\\Training 2015\\Desktop\\Selenium_recorder");
	static ExtentReports extent = new ExtentReports();
	static ExtentTest Test;
	
	static String videoFolder = "C:\\videos\\" ;
	static String videoFile = "TestVideo " + df.format (dateObj);

}