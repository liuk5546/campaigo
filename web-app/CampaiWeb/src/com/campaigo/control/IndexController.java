package com.campaigo.control;

import com.jfinal.core.Controller;

public class IndexController extends Controller {
	public void index(){
		renderText("Hello World!");
	}
}
