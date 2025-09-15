import authorizationHttp from "@/utils/authorizationHttp.js";

export function activityListApi(activityParam) {
  return authorizationHttp.post('/background/activity/activityList',
    {
      pageNum:activityParam.pageNum,
      pageSize: activityParam.pageSize,
      activityType: activityParam.activityType,
      department: activityParam.department
    });
}


export function addActivityApi(addActivity) {
  return authorizationHttp.post('/background/activity/addActivity',
    {
      activityTitle: addActivity.activityTitle,
      activityIntroduction: addActivity.activityIntroduction,
      headerImage: addActivity.headerImage,
      activityDepartment: addActivity.activityDepartment,
      activityType: addActivity.activityType
    });
}


export function addSubTitleApi(addSubTitle) {
  return authorizationHttp.post('/background/activity/addSubTitle',
    {
      activityId:addSubTitle.activityId,
      subtitleType:addSubTitle.subtitleType
    });
}



export function deleteActivityApi(activityId) {
  // params 是一个对象，比如 { key1: 'value1', key2: 'value2' }
  const urlEncodedData = new URLSearchParams();
  urlEncodedData.append("activityId", activityId);
  return authorizationHttp.post('/background/activity/deleteActivity', urlEncodedData, {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    }
  });
}


export function deleteSubTitleApi(subTitleId) {
  // params 是一个对象，比如 { key1: 'value1', key2: 'value2' }
  const urlEncodedData = new URLSearchParams();
  urlEncodedData.append("subTitleId", subTitleId);
  return authorizationHttp.post('/background/activity/deleteSubTitle', urlEncodedData, {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    }
  });
}



export function getActivityMsgApi(activityId) {
  // params 是一个对象，比如 { key1: 'value1', key2: 'value2' }
  const urlEncodedData = new URLSearchParams();
  urlEncodedData.append("activityId", activityId);
  return authorizationHttp.post('/background/activity/getActivityMsg1', urlEncodedData, {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    }
  });
}

export function getSubTitleMsgApi(subTitleId) {
  // params 是一个对象，比如 { key1: 'value1', key2: 'value2' }
  const urlEncodedData = new URLSearchParams();
  urlEncodedData.append("subTitleId", subTitleId);
  return authorizationHttp.post('/background/activity/getSubTitleMsg', urlEncodedData, {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    }
  });
}

export function updateActivityApi(updateActivity) {
  return authorizationHttp.post('/background/activity/updateActivity',
    {
      activityId: updateActivity.activityId,
      activityTitle: updateActivity.activityTitle,
      activityIntroduction: updateActivity.activityIntroduction,
      headerImage: updateActivity.headerImage,
      activityDepartment: updateActivity.activityDepartment,
      activityType: updateActivity.activityType,
      state: updateActivity.state,
      isTop: updateActivity.isTop
    });
}


export function updateSubTitleApi(updateSubTitle) {
  return authorizationHttp.post('/background/activity/updateSubTitle',
    {
      subtitleId: updateSubTitle.subtitleId,
      subtitleName: updateSubTitle.subtitleName,
      subtitleContent: updateSubTitle.subtitleContent,
      subtitleMove:updateSubTitle.subtitleMove
    });
}


