import authorizationHttp from "@/utils/authorizationHttp.js";

export function addPersonalApi(addPersonal) {
  return authorizationHttp.post('/background/item/addPersonal',
    {
      personnelDepartment: addPersonal.personnelDepartment,
      personnelName: addPersonal.personnelName,
      personnelAvatar: addPersonal.personnelAvatar,
      personnelPeriod: addPersonal.personnelPeriod,
      personnelPost: addPersonal.personnelPost,
      personnelExample: addPersonal.personnelExample,
      personnelSaying:addPersonal.personnelSaying,
      personnelSort:addPersonal.personnelSort
    });
}

export function deletePersonalApi(personalId) {
  // params 是一个对象，比如 { key1: 'value1', key2: 'value2' }
  const urlEncodedData = new URLSearchParams();
  urlEncodedData.append("personalId", personalId);
  return authorizationHttp.post('/background/item/deletePersonal', urlEncodedData, {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    }
  });
}

export function getPersonalListApi(personnelParam) {
  return authorizationHttp.post('/background/item/getPersonalList',
    {
      pageNum: personnelParam.pageNum,
      pageSize: personnelParam.pageSize,
      department: personnelParam.department,
      period: personnelParam.period
    });
}

export function updatePersonalApi(updatePerson) {
  return authorizationHttp.post('/background/item/updatePersonal',
    {
      personnelId: updatePerson.personnelId,
      personnelDepartment: updatePerson.personnelDepartment,
      personnelName: updatePerson.personnelName,
      personnelAvatar: updatePerson.personnelAvatar,
      personnelPeriod: updatePerson.personnelPeriod,
      personnelPost: updatePerson.personnelPost,
      personnelExample: updatePerson.personnelExample,
      personnelSaying: updatePerson.personnelSaying,
      personnelSort: updatePerson.personnelSort,
      state: updatePerson.state
    });
}


