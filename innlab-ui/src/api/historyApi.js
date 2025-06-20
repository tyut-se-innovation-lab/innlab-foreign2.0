import authorizationHttp from "@/utils/authorizationHttp.js";

export function addHistoryApi(addHistory) {
  return authorizationHttp.post('/background/history/addHistory',
    {
      historyTime: addHistory.historyTime,
      historyTitle: addHistory.historyTitle,
      headerImageId: addHistory.headerImageId,
      historyContent: addHistory.historyContent
    });
}


export function deleteHistoryApi(historyId) {
  // params 是一个对象，比如 { key1: 'value1', key2: 'value2' }
  const urlEncodedData = new URLSearchParams();
  urlEncodedData.append("historyId", historyId);
  return authorizationHttp.post('/background/history/deleteHistory', urlEncodedData, {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    }
  });
}

export function historyListApi(pageParam) {
  return authorizationHttp.post('/background/history/historyList',
    {
      pageNum: pageParam.pageNum,
      pageSize: pageParam.pageSize
    });
}

export function updateHistoryApi(updateHistory) {
  return authorizationHttp.post('/background/history/updateHistory',
    {
      historyId: updateHistory.historyId,
      historyTime: updateHistory.historyTime,
      historyTitle: updateHistory.historyTitle,
      headerImageId:updateHistory.headerImageId,
      historyContent: updateHistory.historyContent,
      state: updateHistory.state
    });
}
