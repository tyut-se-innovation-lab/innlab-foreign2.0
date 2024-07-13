/* 将图标信息作为一个模块导入到项目中，从而方便地在应用程序中使用这些图标。 */
// 该函数用于获取图标信息。
import { getIcons } from '../index'
// 用于表示插件的标识符。
const PLUGIN_ICONS_ID = 'isme:icons'
export function pluginIcons() {
  return {
    name: 'isme:icons',
    // 解析id函数
    resolveId(id) {
      // 前面加上 \0，表示这是一个虚拟模块。
      if (id === PLUGIN_ICONS_ID) return '\0' + PLUGIN_ICONS_ID
    },
    // 加载id函数
    load(id) {
      if (id === '\0' + PLUGIN_ICONS_ID) {
        // 调用 getIcons() 函数获取的图标信息，并通过 JSON.stringify 转换为 JSON 字符串。
        return `export default ${JSON.stringify(getIcons())}`
      }
    },
  }
}
