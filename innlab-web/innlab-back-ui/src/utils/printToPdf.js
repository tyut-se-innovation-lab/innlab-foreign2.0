function printToPdf(targetId) {
    // 确保DOM已完全加载
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', function () {
            performPrint(targetId);
        });
    } else {
        performPrint(targetId);
    }
}

function performPrint(targetId) {
    const targetElement = document.getElementById(targetId);

    if (!targetElement) {
        console.error('Element with ID ' + targetId + ' not found.');
        return;
    }

    // 克隆目标元素及其子元素
    const clonedElement = targetElement.cloneNode(true);
    clonedElement.style.width = '320mm'

    // 在打印之前隐藏所有元素
    function hideAllElements() {
        document.getElementById('app').style.display = 'none'
        // 将克隆的元素添加到body中
        document.body.appendChild(clonedElement);
    }

    // 在打印结束后恢复所有元素
    function showAllElements() {
        document.body.removeChild(clonedElement);
        document.getElementById('app').style.display = ''
    }

    // 添加事件监听器以在打印之前和之后执行操作
    window.addEventListener('beforeprint', hideAllElements);
    window.addEventListener('afterprint', showAllElements);

    // 触发打印
    window.print();

    // 打印完成后移除事件监听器
    window.removeEventListener('beforeprint', hideAllElements);
    window.removeEventListener('afterprint', showAllElements);
}

// 调用函数
export default printToPdf
