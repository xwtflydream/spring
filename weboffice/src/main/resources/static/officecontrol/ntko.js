var ntko;//控件对象

//初始化去打开文档
function  init(cmd){
	ntko = document.getElementById("TANGER_OCX");
	if(window.navigator.platform=="Win64"){
		//alert("平台版文档控件，请使用32位浏览器如IE");
		//TANGER_OCX_OBJ.AddDocTypePlugin(".tif","tif.NtkoDocument","4.0.1.0","officecontrol/ntkooledocallx64.cab",51,true);	
		ntko.AddDocTypePlugin(".pdf","PDF.NtkoDocument","4.0.2.0","officecontrol/ntkooledocallx64.cab",51,true);	
			
	}else{
			ntko.AddDocTypePlugin(".pdf","PDF.NtkoDocument","4.0.2.0","officecontrol/ntkooledocall.cab",51,true);//版增加对于PDF文件的支持
			//TANGER_OCX_OBJ.AddDocTypePlugin(".tif","tif.NtkoDocument","4.0.1.0","officecontrol/ntkooledocallx64.cab",51,true);	
	}
	if (cmd == 1)
	{
		ntko.OpenFromURL("ntkocreatnew.docx");
	}
	else
	{
		if(cmd==5){
			ntko.OpenFromURL("aboutus.docx");
			ntkoBrowser.ntkoSetReturnValueToParentPage("ntkoSendDataToChildtext","ntko");
		}else{
			ntko.OpenFromURL("aboutus.docx");
			
		}
		
	}
}