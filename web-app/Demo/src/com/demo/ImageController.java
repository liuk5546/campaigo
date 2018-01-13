package com.demo;

import com.jfinal.core.Controller;

public class ImageController extends Controller {
	public void index(){
		render("firstimage.png");
	}
	public void test(){
		renderText("Hello");
	}
}
