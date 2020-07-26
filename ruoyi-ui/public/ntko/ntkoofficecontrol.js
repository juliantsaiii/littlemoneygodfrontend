// 请勿修改，否则可能出错
var userAgent = navigator.userAgent,
	rMsie = /(msie\s|trident.*rv:)([\w.]+)/,
	rFirefox = /(firefox)\/([\w.]+)/,
	rOpera = /(opera).+version\/([\w.]+)/,
	rChrome = /(chrome)\/([\w.]+)/,
	rSafari = /version\/([\w.]+).*(safari)/;
var browser;
var version;
var ua = userAgent.toLowerCase();
function uaMatch(ua) {
	var match = rMsie.exec(ua);
	if (match != null) {
		return { browser: "IE", version: match[2] || "0" };
	}
	var match = rFirefox.exec(ua);
	if (match != null) {
		return { browser: match[1] || "", version: match[2] || "0" };
	}
	var match = rOpera.exec(ua);
	if (match != null) {
		return { browser: match[1] || "", version: match[2] || "0" };
	}
	var match = rChrome.exec(ua);
	if (match != null) {
		return { browser: match[1] || "", version: match[2] || "0" };
	}
	var match = rSafari.exec(ua);
	if (match != null) {
		return { browser: match[2] || "", version: match[1] || "0" };
	}
	if (match != null) {
		return { browser: "", version: "0" };
	}
}
var browserMatch = uaMatch(userAgent.toLowerCase());
if (browserMatch.browser) {
	browser = browserMatch.browser;
	version = browserMatch.version;
}
//document.write(browser);
/*
谷歌浏览器事件接管
*/

function OnComplete5(type, code, html) {

	console.log("文档已关闭");

	//window.parent.selectWenshu();
	window.parent.queryDataGrid2();

	//alert(type);
	//alert(code);

}


function OnComplete2(type, code, html) {

	console.log("文档已保存");

	//window.parent.selectWenshu();
	window.parent.queryDataGrid2();

	//alert(type);
	//alert(code);

}
function OnComplete(type, code, html) {

	console.log("文档已打开");
	//alert(type);
	//alert(code);

}
function OnComplete3(str, doc) {

	TANGER_OCX_OBJ.SvrLoginUrl = "http://156.11.1.200:1986/ntkoSignServer/";
	//if (!TANGER_OCX_OBJ.SvrIsLogin) {
	//    TANGER_OCX_OBJ.SvrDoLogin("", "", false);
	//}
	if (isreadonly == 1)
		TANGER_OCX_OBJ.SetReadOnly(true);
}
function afteropenfromurl(Document, StatusCode) {

}
function publishashtml(type, code, html) {

}
function customtoolbarcommand(ButtonIndex) {
	if (isreadonly == 1) {
		console.log(ButtonIndex);




		if (ButtonIndex == 0) {
			TANGER_OCX_OBJ.PrintPreview();

		}

		if (ButtonIndex == 1) {
			TANGER_OCX_OBJ.PrintOut(true);

		}
		if (ButtonIndex == 2) {
			alert("保存")

		}

	}


	else {
		console.log(ButtonIndex);
		if (ButtonIndex == 0) {

			console.log(CPARA);
			TANGER_OCX_OBJ.saveToURL("/Word/SaveDoc", "fileData", CPARA, "", 0);

		}



		if (ButtonIndex == 1) {
			TANGER_OCX_OBJ.PrintPreview();

		}

		if (ButtonIndex == 2) {
			TANGER_OCX_OBJ.PrintOut(true);

		}
		if (ButtonIndex == 3) {
			alert("保存")

		}


	}

}
function publishaspdf(type, code, html) {

}
function saveasotherurl(type, code, html) {

}
function dowebget(type, code, html) {

}
function webExecute(type, code, html) {

}
function webExecute2(type, code, html) {

}
function FileCommand(TANGER_OCX_str, TANGER_OCX_obj) {
	console.log(TANGER_OCX_str);
	if (TANGER_OCX_str == 3 || TANGER_OCX_str == 4) {
		TANGER_OCX_OBJ.CancelLastCommand = true;

		TANGER_OCX_OBJ.saveToURL("/Word/SaveDoc", "fileData", CPARA, "", 0);
	}
}
function CustomMenuCmd(menuPos, submenuPos, subsubmenuPos, menuCaption, menuID) {
	alert("第" + menuPos + "," + submenuPos + "," + subsubmenuPos + "个菜单项,menuID=" + menuID + ",菜单标题为\"" + menuCaption + "\"的命令被执行.");
}
var classidx64 = "A64E3073-2016-4baf-A89D-FFE1FAA10EE0";
var classid = "A64E3073-2016-4baf-A89D-FFE1FAA10EC0";
var codebase = "/OfficeControl.cab#version=5,0,4,0";
var codebase64 = "/OfficeControlx64.cab#version=5,0,4,0";
if (browser == "IE") {
	//alert(window.navigator.platform);
	if (window.navigator.platform == "Win32") {
		document.write('<!-- 用来产生编辑状态的ActiveX控件的JS脚本-->   ');
		document.write('<!-- 因为微软的ActiveX新机制，需要一个外部引入的js-->   ');
		document.write('<object id="TANGER_OCX" classid="clsid:' + classid + '"');
		document.write('codebase="' + codebase + '" width="100%" height="100%">   ');
		document.write('<param name="IsUseUTF8URL" value="-1">   ');
		document.write('<param name="IsUseUTF8Data" value="-1">   ');
		document.write('<param name="BorderStyle" value="1">   ');
		document.write('<param name="BorderColor" value="14402205">   ');
		document.write('<param name="IsRemoveMacrosOnSave" value="false">   ');




		document.write('<param name="TitlebarTextColor" value="0">   ');
		document.write('<param name="MenubarColor" value="14402205">   ');
		document.write('<param name="MenuButtonColor" VALUE="16180947">   ');
		document.write('<param name="MenuBarStyle" value="3">   ');
		document.write('<param name="ekeytype" value="14">   ');
		document.write('<param name="WebUserName" value="NTKO">   ');
		document.write('<param name="Caption" value="NTKO OFFICE文档控件示例演示 http://www.ntko.com">   ');
		document.write('<SPAN STYLE="color:red">不能装载文档控件。请在检查浏览器的选项中检查浏览器的安全设置。</SPAN>   ');
		document.write('</object>');
	}
	if (window.navigator.platform == "Win64") {

		document.write('<!-- 用来产生编辑状态的ActiveX控件的JS脚本-->   ');
		document.write('<!-- 因为微软的ActiveX新机制，需要一个外部引入的js-->   ');
		document.write('<object id="TANGER_OCX" classid="clsid:' + classidx64 + '"');
		document.write('codebase="' + codebase64 + '" width="100%" height="100%">   ');
		document.write('<param name="IsUseUTF8URL" value="-1">   ');
		document.write('<param name="IsUseUTF8Data" value="-1">   ');
		document.write('<param name="BorderStyle" value="1">   ');
		document.write('<param name="BorderColor" value="14402205">   ');
		document.write('<param name="TitlebarColor" value="15658734">   ');
		document.write('<param name="isoptforopenspeed" value="0">   ');
		document.write('<param name="TitlebarTextColor" value="0">   ');









		document.write('<param name="MenubarColor" value="14402205">   ');
		document.write('<param name="MenuButtonColor" VALUE="16180947">   ');
		document.write('<param name="MenuBarStyle" value="3">   ');
		document.write('<param name="MenuButtonStyle" value="7">   ');
		document.write('<param name="WebUserName" value="NTKO">   ');
		document.write('<param name="Caption" value="NTKO OFFICE文档控件示例演示 http://www.ntko.com">   ');
		document.write('<SPAN STYLE="color:red">不能装载文档控件。请在检查浏览器的选项中检查浏览器的安全设置。</SPAN>   ');
		document.write('</object>');

	}

}
else if (browser == "firefox") {
	document.write('<object id="TANGER_OCX" type="application/ntko-plug"  codebase="' + codebase + '" width="100%" height="100%" ForOnSaveToURL="OnComplete2" ForOnBeginOpenFromURL="OnComplete" ForOndocumentopened="OnComplete3"');

	document.write('ForOnpublishAshtmltourl="publishashtml"');
	document.write('ForOnpublishAspdftourl="publishaspdf"');
	document.write('ForOnSaveAsOtherFormatToUrl="saveasotherurl"');
	document.write('ForOnDoWebGet="dowebget"');
	document.write('ForOnDoWebExecute="webExecute"');
	document.write('ForOnDoWebExecute2="webExecute2"');
	document.write('ForOnFileCommand="FileCommand"');
	document.write('ForOnCustomMenuCmd2="CustomMenuCmd"');
	document.write('_IsUseUTF8URL="-1"   ');





	document.write('_IsUseUTF8Data="-1"   ');
	document.write('_BorderStyle="1"   ');
	document.write('_BorderColor="14402205"   ');
	document.write('_MenubarColor="14402205"   ');
	document.write('_MenuButtonColor="16180947"   ');
	document.write('_MenuBarStyle="3"  ');
	document.write('_MenuButtonStyle="7"   ');
	document.write('_WebUserName="NTKO"   ');
	document.write('clsid="{' + classid + '}" >');
	document.write('<SPAN STYLE="color:red">尚未安装NTKO Web FireFox跨浏览器插件。请点击<a href="评测专用演示平台PLUS版本一键安装包.exe">安装组1件</a></SPAN>   ');
	document.write('</object>   ');
} else if (browser == "chrome") {
	document.write('<div id="TANGER_Container" style="padding:10px;"><object id="TANGER_OCX" style="min-height:90%" clsid="{' + classid + '}"  ForOnSaveToURL="OnComplete2"    ForOnBeginOpenFromURL="OnComplete" ForOndocumentopened="OnComplete3" ');


	document.write('ForOnpublishAshtmltourl="publishashtml"');
	document.write('ForOnpublishAspdftourl="publishaspdf"');
	document.write('ForOnSaveAsOtherFormatToUrl="saveasotherurl"');
	document.write('ForOnDoWebGet="dowebget"');
	document.write('ForOnDoWebExecute="webExecute"');
	document.write('ForOnDoWebExecute2="webExecute2"');
	document.write('ForOnAfterOpenFromURL="afteropenfromurl"');
	document.write('ForOnDocumentClosed="OnComplete5"');

	document.write('ForOnCustomToolBarCommand="customtoolbarcommand"');

	document.write('ForOnFileCommand="FileCommand"');

	document.write('_ProductCaption="江苏省纪委监察委"');
	document.write('_ProductKey="61A36E44DDBFEBA99F5EAAEDDE49A310DB0253EE"');

	/*style="display:none"*/
	document.write('ForOnCustomMenuCmd2="CustomMenuCmd"');
	document.write('codebase="' + codebase + '" width="100%" height="100%" type="application/ntko-plug" ');
	document.write('_IsUseUTF8URL="-1"   ');
	document.write('_IsUseUTF8Data="-1"   ');
	document.write('_BorderStyle="1"   ');
	document.write('_BorderColor="14402205"   ');
	document.write('_MenubarColor="14402205"   ');

	document.write('_MenuButtonColor="16180947"   ');
	document.write('_MenuBarStyle="3"  ');
	document.write('_MenuButtonStyle="7"   ');
	document.write('_WebUserName="NTKO"   ');
	document.write('_Caption="Anshare Office">    ');

	document.write('<SPAN STYLE="color:red">尚未安装NTKO Web Chrome跨浏览器插件。请点击<a href="/NTKO_Plugins.rar">安装组件</a></SPAN>   ');
	document.write('</object></div>');



} else if (Sys.opera) {
	alert("sorry,ntko web印章暂时不支持opera!");
} else if (Sys.safari) {
	alert("sorry,ntko web印章暂时不支持safari!");
}