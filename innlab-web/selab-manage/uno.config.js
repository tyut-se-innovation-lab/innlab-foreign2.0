/**********************************
 * @Author: Ronnie Zhang
 * @LastEditor: Ronnie Zhang
 * @LastEditTime: 2023/12/05 21:30:57
 * @Email: zclzone@outlook.com
 * Copyright © 2023 Ronnie Zhang(大脸怪) | https://isme.top
 **********************************/
// 导入 UNO.css 提供的一些配置函数和预设
import { defineConfig, presetUno, presetIcons, presetAttributify } from 'unocss'
// 将 rem 单位转换为 px 单位的预设。
import presetRemToPx from '@unocss/preset-rem-to-px'
// 导入文件系统图标加载器
import { FileSystemIconLoader } from '@iconify/utils/lib/loader/node-loaders'
// 导入获取图标信息的函数
import { getIcons } from './builder'

const icons = getIcons()
export default defineConfig({
  presets: [
    // 该预设配置加载了 UNO.css 样式库的基本配置，包括一些基础样式和工具类
    presetUno(),
    // 该预设配置启用了 UNO.css 的属性化样式，使得可以通过 HTML 属性来应用样式，从而简化了 CSS 的编写
    presetAttributify(),
    presetIcons({
      warn: true,
      prefix: ['i-'],
      // 指定图标为行内元素
      extraProperties: {
        display: 'inline-block',
      },
      collections: {
        me: FileSystemIconLoader('./src/assets/icons/isme'),
        fe: FileSystemIconLoader('./src/assets/icons/feather'),
      },
    }),
    // 用于将 rem 单位转换为 px 单位 基础字体是4
    presetRemToPx({ baseFontSize: 4 }),
  ],
  safelist: icons.map((icon) => `${icon} ${icon}?mask`.split(' ')).flat(),
  shortcuts: [
    ['wh-full', 'w-full h-full'],
    ['f-c-c', 'flex justify-center items-center'],
    ['flex-col', 'flex flex-col'],
    ['card-border', 'border border-solid border-light_border dark:border-dark_border'],
    ['auto-bg', 'bg-white dark:bg-dark'],
    ['auto-bg-hover', 'hover:bg-#eaf0f1 hover:dark:bg-#1b2429'],
    ['auto-bg-highlight', 'bg-#eaf0f1 dark:bg-#1b2429'],
    ['text-highlight', 'rounded-4 px-8 py-2 auto-bg-highlight'],
   
  ],
  rules: [
    [
      'card-shadow',
      { 'box-shadow': '0 1px 2px -2px #00000029, 0 3px 6px #0000001f, 0 5px 12px 4px #00000017' },
    ],
  ],
  theme: {
    colors: {
      primary: 'var(--primary-color)',
      primary_hover: 'var(--primary-color-hover)',
      primary_pressed: 'var(--primary-color-pressed)',
      primary_active: 'var(--primary-color-active)',
      info: 'var(--info-color)',
      info_hover: 'var(--info-color-hover)',
      info_pressed: 'var(--info-color-pressed)',
      info_active: 'var(--info-color-active)',
      success: 'var(--success-color)',
      success_hover: 'var(--success-color-hover)',
      success_pressed: 'var(--success-color-pressed)',
      success_active: 'var(--success-color-active)',
      warning: 'var(--warning-color)',
      warning_hover: 'var(--warning-color-hover)',
      warning_pressed: 'var(--warning-color-pressed)',
      warning_active: 'var(--warning-color-active)',
      error: 'var(--error-color)',
      error_hover: 'var(--error-color-hover)',
      error_pressed: 'var(--error-color-pressed)',
      error_active: 'var(--error-color-active)',
      dark: '#18181c',
      light_border: '#efeff5',
      dark_border: '#2d2d30',
    },
  },
})
