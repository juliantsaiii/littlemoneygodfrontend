import { ntkoBrowser } from "@/api/monitor/ntkobackground.min";


export function openNtkoWindow(url) {
    var CLOSEVENT = false;
    //判断插件是否安装
    var ntkoed = ntkoBrowser.ExtensionInstalled();
    if (ntkoed) {
        ntkoBrowser.openWindow("/ntko/editindex.html?ntkourl=" + url, false, "南京安夏电子科技有限公司某某测试用户", "EA2DD26261BAE1662BB827FB200CC90BE0C67745");
    }
}

// export function openPicPdfWindow(url) {
//     // document.getElementById("img-view").src = url;
//     this.$refs.viewer.$viewer.show();
// }

// export function viewFile(url, type) {
//     if (type == ".doc" || type == ".docx") {
//         openNtkoWindow(url);
//     } else {
//         openPicPdfWindow(url);
//     }

// }