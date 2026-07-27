// import authorizationHttp from "@/utils/authorizationHttp.js";
// export function addItemApi(addItem) {
//   return authorizationHttp.post('/background/item/addItem',
//     {
//       department: addItem.department,
//       itemTitle: addItem.itemTitle,
//       itemIntroduction: addItem.itemIntroduction,
//       headerImage: addItem.headerImage
//     });
// }
//
// export function deleteItemApi(itemId) {
//   // params 是一个对象，比如 { key1: 'value1', key2: 'value2' }
//   const urlEncodedData = new URLSearchParams();
//   urlEncodedData.append("itemId", itemId);
//   return authorizationHttp.post('/background/item/deleteItem', urlEncodedData, {
//     headers: {
//       'Content-Type': 'application/x-www-form-urlencoded',
//     }
//   });
// }
//
//
//
// export function getItemListApi(itemParam) {
//   return authorizationHttp.post('/background/item/getItemList',
//     {
//       pageNum: itemParam.pageNum,
//       pageSize: itemParam.pageSize,
//       department: itemParam.department
//     });
// }
//
//
// export function getItemMsgApi(itemId) {
//   // params 是一个对象，比如 { key1: 'value1', key2: 'value2' }
//   const urlEncodedData = new URLSearchParams();
//   urlEncodedData.append("itemId", itemId);
//   return authorizationHttp.post('/background/item/getItemMsg', urlEncodedData, {
//     headers: {
//       'Content-Type': 'application/x-www-form-urlencoded',
//     }
//   });
// }
//
//
// export function updateItemApi(updateItem) {
//   return authorizationHttp.post('/background/item/updateItem',
//     {
//       itemId: updateItem.itemId,
//       department: updateItem.department,
//       itemTitle: updateItem.itemTitle,
//       itemIntroduction: updateItem.itemIntroduction,
//       headerImage: updateItem.headerImage,
//       contentMarkdown: updateItem.contentMarkdown,
//       state: updateItem.state,
//       githubUrl: updateItem.githubUrl,
//       isTop: updateItem.isTop
//     });
// }
