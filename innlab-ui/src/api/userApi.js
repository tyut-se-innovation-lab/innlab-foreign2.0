import touristHttp from "@/utils/touristHttp.js";
import authorizationHttp from "@/utils/authorizationHttp.js";

export function loginApi(login) {
  return touristHttp.post('/background/login', {
    account: login.loginAccount,
    password: login.loginPassword
  });
}
export function registerApi(register) {
  return touristHttp.post('/background/register', {
    invitationCode: register.invitationCode,
    userAccount: register.userAccount,
    userPassword: register.userPassword,
    userNickname: register.userNickname,
    userEmail: register.userEmail,
    verificationCode: register.verificationCode
  });
}

export function registerVerifyApi(registerVerify) {
  return touristHttp.post('/background/register/verify', {
    invitationCode: registerVerify.invitationCode,
    userAccount: registerVerify.userAccount,
    userEmail: registerVerify.userEmail
  });
}


export function getSelfMsgApi() {
  return authorizationHttp.post('/background/getSelfMag');
}

export function logoutApi() {
  return authorizationHttp.post('/background/logout');
}

export function getUserListApi(userParam) {
  return authorizationHttp.post('/background/userList',
    {
      pageNum:userParam.pageNum,
      pageSize:userParam.pageSize,
      userAccount:userParam.userAccount,
      userDepartment:userParam.userDepartment
    });
}


export function updateUserStatusApi() {
  return authorizationHttp.post('/background/getSelfMag');
}


export function deleteUserApi() {
  return authorizationHttp.post('/background/getSelfMag');
}


export function getUserMsgApi(userId) {
  // params 是一个对象，比如 { key1: 'value1', key2: 'value2' }
  const urlEncodedData = new URLSearchParams();
  urlEncodedData.append("userId", userId);
  return authorizationHttp.post('/background/userMsg', urlEncodedData, {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    }
  });

}
export function updateUserApi(updateUser) {
  return authorizationHttp.post('/background/updateUser',
    {
      userId:updateUser.userId,
      userAvatar:updateUser.userAvatar,
      userPhone:updateUser.userPhone
    });
}

export function updateSelfMsdApi(updateUser) {
  return authorizationHttp.post('/background/updateSelfMsg',
    {
      userId:updateUser.userId,
      userAvatar:updateUser.userAvatar,
      userPhone:updateUser.userPhone
    });
}


export function updateSelfPasswordApi(updateUserPassword) {
  return authorizationHttp.post('/background/updateSelfPassword',
    {
      userId:updateUserPassword.userId,
      oldUserPassword:updateUserPassword.oldUserPassword,
      newUserPassword:updateUserPassword.newUserPassword
    });
}
export function resetUserPasswordApi(userId) {
  // params 是一个对象，比如 { key1: 'value1', key2: 'value2' }
  const urlEncodedData = new URLSearchParams();
  urlEncodedData.append("userId", userId);
  return authorizationHttp.post('/background/userMsg', urlEncodedData, {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    }
  });

}
