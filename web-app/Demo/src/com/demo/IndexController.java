package com.demo;

import java.io.File;

import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

public class IndexController extends Controller {
	public void index(){
		//PropKit.use(new File("../WebRoot/jsonfile/imageRoute.json"));
		render("jsonfile/imageRoute.json");
		System.out.println( getPara());
//		renderText("hello jfinal");
	}
	public void pageForm(){
		render("user/form.jsp");
	}
	
	public void pageImage(){
		render("jsonfile/imageRoute.json");
	}
}
