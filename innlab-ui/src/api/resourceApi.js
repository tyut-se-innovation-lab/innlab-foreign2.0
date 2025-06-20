import touristHttp from "@/utils/touristHttp.js";
import authorizationHttp from "@/utils/authorizationHttp.js";


export function addImageApi(file, description) {
  const formData = new FormData();
  formData.append('file', file);
  formData.append('description', description);

  return authorizationHttp.post('/background/resource/addImage', formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    timeout: 100000 // 设置超时时间为5分钟
  });
}

export function addVideoApi(file, description) {
  const formData = new FormData();
  formData.append('file', file);
  formData.append('description', description);

  return authorizationHttp.post('/background/resource/addvideo', formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    timeout: 300000 // 设置超时时间为5分钟
  });
}


export function addResourceApi(file, description) {
  const formData = new FormData();
  formData.append('file', file);
  formData.append('description', description);

  return authorizationHttp.post('/background/resource/addResource', formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    timeout: 300000 // 设置超时时间为5分钟
  });
}

export function getResourceByLzApi(lz) {
  return touristHttp.post('/background/resource/getResourceByLz',
    {
      pwd:lz.pwd,
      isNewd:lz.isNewd,
      fid:lz.fid
    });
}

export function getResourceListApi(resourceParam) {
  return authorizationHttp.post('/background/resource/resourceList',
    {
      pageNum:resourceParam.pageNum,
      pageSize:resourceParam.pageSize,
      resourceType:resourceParam.resourceType
    });
}



