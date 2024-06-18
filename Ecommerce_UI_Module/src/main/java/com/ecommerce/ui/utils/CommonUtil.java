package com.ecommerce.ui.utils;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.ui.vo.CategoryUIVO;

public class CommonUtil {
	
	public String uploadFile(CategoryUIVO cd, MultipartFile documentFile, String documentType, HttpSession session) {
		try {
			//String path = System.getProperty("user.dir");
			String directoryName = System.getProperty("user.dir");
			String folderName = cd.getCategoryName();
			String userFolder = null;
			String contextFolder = "src/main/resources/META-INF/resources/static/Images";
			String databasePath = null;
			if(documentType.startsWith("productImage")) {
				userFolder = contextFolder + "/Products/" + folderName;
//				userFolder =  "/user_documents/" + folderName;
				databasePath = "/Products/" + folderName;
//			} else {
//				userFolder = contextFolder + "/user_documents/" + folderName +"/property_pics";
////				userFolder = "/user_documents/" + folderName +"/property_pics";
//				databasePath = "/user_documents/" + folderName +"/property_pics";
			}
			String path = directoryName + "/" + userFolder;
			String originalFileName = documentFile.getOriginalFilename();
			String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			String fileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "_" + documentType + "." + fileExtension;
			databasePath += "/" + fileName;
			File directory = new File(path);
			if (! directory.exists()){
		        directory.mkdirs();
		        // If you require it to make the entire directory path including parents,
		        // use directory.mkdirs(); here instead.
		    }
			
			File file = new File(directory + "/" + fileName);
			if(!file.exists()) {
				file.createNewFile();
			}
			documentFile.transferTo(file);
			if(documentType.startsWith("productImage")) {
				session.setAttribute("prd_img_path", databasePath);
			} else if(documentType.startsWith("aadhaar")) {
				session.setAttribute("aadhaar_card_path", databasePath);
			} else {
				session.setAttribute("prop_pic_path", databasePath);
			}
			return "Success";
		} catch (IOException ioe) {
			return ioe.getMessage();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
