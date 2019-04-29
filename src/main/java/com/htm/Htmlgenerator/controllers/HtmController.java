package com.htm.Htmlgenerator.controllers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HtmController {
	
	@PersistenceContext
	private EntityManager entitymanager;

	@RequestMapping( value = "{customerId}/getpage", method = RequestMethod.GET)
	public @ResponseBody byte[] getLogin(@PathVariable String customerId) {
		boolean userRepliedAlready = false;
		int width = 250;
        int height = 40;
        byte[] bytes = null;
        try {
        	
        	if(entitymanager.find(Employee.class, customerId)!=null) {
        		Employee person = entitymanager.find(Employee.class,customerId);
        		userRepliedAlready = person.isAnswerd();
    		}
 
        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
 
        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, width, height);
		g2d.setColor(Color.black);

        System.out.println(userRepliedAlready);
		if(userRepliedAlready == true) {
			//this text we can get it from chariot 
			g2d.drawString("you already paid user", 10, 20);
		}else {
			g2d.drawString("you need to pay first", 10, 20);
		}
		
		g2d.dispose();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpg", baos);
		 bytes = baos.toByteArray();
        }catch(Exception e) {
        	e.printStackTrace();
        }
		return bytes;
	}
}
