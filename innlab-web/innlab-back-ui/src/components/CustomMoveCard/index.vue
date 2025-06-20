<template>
    <div class="absolute" :id="idName" :style="{width: width,height: height,top: top + 'px',left: left + 'px',right: right + 'px',bottom: bottom + 'px'}">
        <slot></slot>
     <div class="resize-handle-tl" :class="'resize-handle'+ idName"></div>
      <div class="resize-handle-tr" :class="'resize-handle'+ idName"></div>
      <div class="resize-handle-bl" :class="'resize-handle'+ idName"></div>
      <div class="resize-handle-br" :class="'resize-handle'+ idName"></div>
    </div>  
</template>

<script setup>
import { onMounted } from "vue";

const porp = defineProps({
    idName: {
        Type: String,
        required: true
    },
    width: {
    type: [Number, String],
    default: "100%", // 默认宽度
  },
  height: {
    type: [Number, String],
    default: "100%", // 默认高度
  },
  top: {
    type: Number,
    default: null,
  },
  left: {
    type: Number,
    default: null,
  },
  bottom: {
    type: Number,
    default: null,
  },
  right: {
    type: Number,
    default: null,
  }
})

let originalWidth = 0;
let originalHeight = 0;
let originalX = 0;
let originalY = 0;
let originalMouseX = 0;
let originalMouseY = 0;
let resizableBox = null;
let resizeHandle = [];
let resizeType = "";
 
onMounted(() => {
  resizableBox = document.getElementById(porp.idName);
  resizeHandle = document.querySelectorAll(".resize-handle"+ porp.idName);
  resizeHandle.forEach((handle) => {
    handle.addEventListener("mousedown", function (e) {
      e.preventDefault();
      originalWidth = parseFloat(getComputedStyle(resizableBox).width);
      originalHeight = parseFloat(getComputedStyle(resizableBox).height);
      originalMouseX = e.clientX;
      originalMouseY = e.clientY;
      resizeType = this.className;
     
      window.addEventListener("mousemove", resize);
      window.addEventListener("mouseup", stopResize);
    });
  });
});
let firstLeft = porp.left;
let firstTop = porp.top;
let firstBottom = porp.bottom;
let firstRight = porp.right
let lastTop = 0;
let lastLeft = 0;
let lastBottom = 0;
let lastRight = 0;
const resize = (e) => {
  const deltaX = e.clientX - originalMouseX;
  const deltaY = e.clientY - originalMouseY;
  resizableBox = document.getElementById(porp.idName);
  if (resizeType.includes("resize-handle-tl")) {
    if (resizableBox.style.left) {
      resizableBox.style.left = `${
        originalX + deltaX + lastLeft + firstLeft
      }px`;
      resizableBox.style.top = `${originalY + deltaY + lastTop + firstTop}px`;
    }
 
    resizableBox.style.width = `${originalWidth - deltaX}px`;
    resizableBox.style.height = `${originalHeight - deltaY}px`;
  } else if (resizeType.includes("resize-handle-tr")) {
    if(resizableBox.style.top) {
      resizableBox.style.top = `${originalY + deltaY + firstTop + lastTop}px`;
    }else {
      resizableBox.style.right = `${ originalX - deltaX + firstRight -lastRight}px`;
    }

    resizableBox.style.width = `${originalWidth + deltaX}px`;
    resizableBox.style.height = `${originalHeight - deltaY}px`;
   
  } else if (resizeType.includes("resize-handle-bl")) {
    if( resizableBox.style.left) {
      resizableBox.style.left = `${originalX + deltaX + firstLeft + lastLeft}px`;
    }else {
      resizableBox.style.bottom = `${originalY - deltaY + firstBottom - lastBottom}px`;
    }

    resizableBox.style.width = `${originalWidth - deltaX}px`;
    resizableBox.style.height = `${originalHeight + deltaY}px`;
   
  } else if (resizeType.includes("resize-handle-br")) {
    if(resizableBox.style.right) {
      resizableBox.style.right = `${ originalX - deltaX + firstRight -lastRight}px`;
      resizableBox.style.bottom = `${originalY - deltaY + firstBottom - lastBottom}px`;
    }

    resizableBox.style.width = `${originalWidth + deltaX}px`;
    resizableBox.style.height = `${originalHeight + deltaY}px`;
  }
};
 
const stopResize = (e) => {
  if(e.target.classList.contains('resize-handle-tl')) {
    lastTop += e.pageY - originalMouseY;
    lastLeft += e.pageX - originalMouseX;
  }else if(e.target.classList.contains('resize-handle-tr')) {
    lastTop += e.pageY - originalMouseY;
    lastRight += e.pageX - originalMouseX;
  }else if(e.target.classList.contains('resize-handle-bl')) {
    lastLeft += e.pageX - originalMouseX;
    lastBottom += e.pageY - originalMouseY
  }else if(e.target.classList.contains('resize-handle-br')) {
    lastBottom += e.pageY - originalMouseY
    lastRight += e.pageX - originalMouseX;
  }
 
  window.removeEventListener("mousemove", resize);
  window.removeEventListener("mouseup", stopResize);
};


</script>

<style  scoped>

.resize-handle-br {
  width: 10px;
  height: 10px;
  position: absolute;
  bottom: 0;
  right: 0;
  cursor: se-resize;
}
.resize-handle-bl {
  width: 10px;
  height: 10px;
  position: absolute;
  bottom: 0;
  left: 0;
  cursor: sw-resize;
}
.resize-handle-tl {
  width: 10px;
  height: 10px;
  position: absolute;
  top: 0;
  left: 0;
  cursor: nw-resize;
}
.resize-handle-tr {
  width: 10px;
  height: 10px;
  position: absolute;
  top: 0;
  right: 0;
  cursor: ne-resize;
}

</style>