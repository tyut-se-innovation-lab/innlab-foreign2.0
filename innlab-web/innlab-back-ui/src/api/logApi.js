import authorizationHttp from "@/utils/authorizationHttp.js";
import touristHttp from "@/utils/touristHttp.js";

export function accessLogApi(logParam) {
  return authorizationHttp.post('/background/log/getAccessLogList',
    {
      pageNum:logParam.pageNum,
      pageSize:logParam.pageSize
    });
}


export function sysLogApi(logParam) {
  return authorizationHttp.post('/background/log/getSysLogList',
    {
      pageNum:logParam.pageNum,
      pageSize:logParam.pageSize
    });
}


export function loginLogApi(logParam) {
  return authorizationHttp.post('/background/log/getLoginLogList',
    {
      pageNum:logParam.pageNum,
      pageSize:logParam.pageSize
    });
}


