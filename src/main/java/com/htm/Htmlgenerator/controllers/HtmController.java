package com.htm.Htmlgenerator.controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HtmController {
	
	static { /* works fine! ! */
	      System.setProperty("java.awt.headless", "false");
	      System.out.println(java.awt.GraphicsEnvironment.isHeadless());
	      /* ---> prints true */
	    }
	
	@Autowired
	Employee employee;
	
	public String updateAnswer(String customerId) {
		
		if(employee.getId()=="abc" && employee.isAnswerd() == false 
				&& employee.getCount()<1) {
			employee.setAnswerd(true);
			employee.setCount(1);
		}else {
			employee.setAnswerd(false);
			employee.setCount(0);
		}
		
		return "Your Response Updated Thank you";
	}

	@RequestMapping( value = "{customerId}/getpage", method = RequestMethod.GET)
	public @ResponseBody byte[] getLogin(@PathVariable String customerId) {
		boolean userRepliedAlready = false;
        byte[] bytes = null;
        
        //we are updating the user response here because he opened his email
        updateAnswer(customerId);
        
        try {
        	
        	if(employee.isAnswerd()) {
        		userRepliedAlready = employee.isAnswerd();
    		}
 		
        	String html = "";
        	 
        if(userRepliedAlready == true) {

        	html = new String (Files.readAllBytes(Paths.get("C:\\Users\\johns\\Downloads\\Htmlgenerator\\src\\main\\resources\\PaidTemplate.html")));
		}else {
			html = new String (Files.readAllBytes(Paths.get("C:\\Users\\johns\\Downloads\\Htmlgenerator\\src\\main\\resources\\PayTemplate.html")));
			}
			System.out.println(html);
			
			int width = 400, height = 300;

			
			  BufferedImage image = GraphicsEnvironment.getLocalGraphicsEnvironment()
			  .getDefaultScreenDevice().getDefaultConfiguration()
			  .createCompatibleImage(width, height);
			  
			  Graphics graphics = image.createGraphics();
			  graphics.setColor(Color.white);
			  graphics.setFont(new Font("Purisa", Font.PLAIN, 13));
			  graphics.fillRect(0, 0, width, height); 
			  graphics.setColor(Color.black);
			  
			  JEditorPane jep = new JEditorPane("text/html", html); 
			  jep.setSize(width,height); 
			  jep.setBackground(Color.WHITE);
			  jep.setForeground(Color.WHITE);
			  jep.print(graphics);
			 
		    
		    		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", baos);
		 bytes = baos.toByteArray();
        }catch(Exception e) {
        	e.printStackTrace();
        }
		return bytes;
	}
}
