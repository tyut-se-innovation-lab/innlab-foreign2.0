import html2canvas from 'html2canvas' // 将html页面转换成图片
import jsPDF from 'jspdf' // 将图片生成pdf
import { ElLoading } from 'element-plus'
export async function downloadPdf(elementId, pdfName) {
  /**
   * 将html页面指定盒子里的内容生成pdf文档下载
   * @param elementId 要绑定的盒子,里面是要生成pdf的内容
   * @param pdfName 生成的文件的名字
   */
  const loadingInstance = ElLoading.service({
    lock: true,
    text: '下载中',
    spinner: 'el-icon-loading',
    background: 'rgba(0, 0, 0, 0.8)'
  })
  let pdfDom = document.getElementById(elementId)
  pdfDom.style.background = '#FFFFFF' // 设备背景为白色

  const originalStyle = pdfDom.getAttribute('style');
  pdfDom.style.width = '190mm'
  setTimeout(() => {
    htmlToCanvas(pdfDom)
  }, 100)
  function canvasToPdf(canvas, pdf) {
    let ctx = canvas.getContext('2d')
    let a4w = 190
    let a4h = 277 // A4纸大小,四边保留10mm的边距,显示区域190 x 277;
    let imgH = Math.floor(canvas.width / a4w * a4h) // 按A4显示比例换算一页的像素高度
    let renderH = 0
    while (renderH < canvas.height) {
      let page = document.createElement('canvas')
      page.width = canvas.width
      page.height = Math.min(imgH, canvas.height - renderH) // 内容可能不满一页
      // 用getImageData裁剪指定区域,并画到前面创建的canvas对象中
      page.getContext('2d').putImageData(ctx.getImageData(0, renderH, canvas.width, Math.min(imgH, canvas.height - renderH)), 0, 0)
      // 添加图像到页面,保留10mm边距
      pdf.addImage(page.toDataURL('image/jpeg', 1.0), 'JPEG', 10, 10, a4w, Math.min(a4h, a4w * page.height / page.width))
      renderH += imgH
      if (renderH < canvas.height) {
        // 如果还有内容,添加一个新的空页
        pdf.addPage()
      }
    }
  }
  function htmlToCanvas(pdfDom) {
    html2canvas(pdfDom, {
      // 开始截图,可以设置一些截图的属性
      logging: false, // 日志开关，便于查看html2canvas的内部执行流程
      useCORS: true
    }).then((canvas) => {
      // eslint-disable-next-line new-cap
      let pdf = new jsPDF('p', 'mm', 'a4') // 设置pdf属性,A4纸,纵向
      canvasToPdf(canvas, pdf)
      loadingInstance.close()
      pdf.save(pdfName + '.pdf')
      if (originalStyle) {
        pdfDom.setAttribute('style', originalStyle);
      }
    })
  }
}