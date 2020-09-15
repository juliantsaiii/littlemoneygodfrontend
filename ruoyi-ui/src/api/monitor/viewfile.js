import { ntkoBrowser } from "@/api/monitor/ntkobackground.min";

export function openNtkoWindow(url, isReadonly) {
  var CLOSEVENT = false;
  //判断插件是否安装
  var ntkoed = ntkoBrowser.ExtensionInstalled();
  if (ntkoed) {
    ntkoBrowser.openWindow(
      "/ntko/editindex.html?ntkourl=" +
        encodeURI(url) +
        "&isReadonly=" +
        isReadonly +
        "&urlPre=" +
        process.env.VUE_APP_BASE_API,
      false,
      "南京安夏电子科技有限公司",
      "D9672EDAF5C0D415DE789FFDD21BE9AD23E6FF35"
    );
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
