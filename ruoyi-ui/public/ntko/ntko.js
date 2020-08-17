var ntko;//控件对象

//初始化去打开文档
function init(url, isReadonly) {
	ntko = document.getElementById("TANGER_OCX");
	if (window.navigator.platform == "Win64") {
		//alert("平台版文档控件，请使用32位浏览器如IE");
		//TANGER_OCX_OBJ.AddDocTypePlugin(".tif","tif.NtkoDocument","4.0.1.0","officecontrol/ntkooledocallx64.cab",51,true);	
		ntko.AddDocTypePlugin(".pdf", "PDF.NtkoDocument", "4.0.2.0", "officecontrol/ntkooledocallx64.cab", 51, true);

	} else {
		ntko.AddDocTypePlugin(".pdf", "PDF.NtkoDocument", "4.0.2.0", "officecontrol/ntkooledocall.cab", 51, true);//版增加对于PDF文件的支持
		//TANGER_OCX_OBJ.AddDocTypePlugin(".tif","tif.NtkoDocument","4.0.1.0","officecontrol/ntkooledocallx64.cab",51,true);	
	}
	ntko.OpenFromURL(url);
	ntko.FullScreenMode = true;
	ntko.SetReadOnly(isReadonly);
	if (isReadonly == "true") {
		ntko.AddCustomButtonOnMenu(2, "打印", true, 2);
		ntko.AddCustomButtonOnMenu(3, "关闭", false, 3);
	} else {
		ntko.AddCustomButtonOnMenu(0, "保存", true);
		ntko.AddCustomButtonOnMenu(1, "打印预览", true, 1);
		ntko.AddCustomButtonOnMenu(2, "打印", true, 2);
		ntko.AddCustomButtonOnMenu(3, "关闭", false, 3);
	}
}

function ntkoCloseEvent() {
	// alert("guanbile")
}

