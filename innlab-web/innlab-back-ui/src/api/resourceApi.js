import touristHttp from "@/utils/touristHttp.js";
import authorizationHttp from "@/utils/authorizationHttp.js";


export function addImageApi(file, description) {
  const formData = new FormData();
  formData.append('file', file);
  const desc = !description || description.trim() === '' ? file.name : description;
  formData.append('description', desc);

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
  const desc = !description || description.trim() === '' ? file.name : description;
  formData.append('description', desc);

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
  const desc = !description || description.trim() === '' ? file.name : description;
  formData.append('description', desc);

  return authorizationHttp.post('/background/resource/addResource', formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    timeout: 300000 // 设置超时时间为5分钟
  });
}
export function addMp3Api(file, description) {
  const formData = new FormData();
  formData.append('file', file);
  const desc = !description || description.trim() === '' ? file.name : description;
  formData.append('description', desc);

  return authorizationHttp.post('/background/resource/addMp3', formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    timeout: 300000 // 设置超时时间为5分钟
  });
}
export function addPdfApi(file, description) {
  const formData = new FormData();
  formData.append('file', file);
  const desc = !description || description.trim() === '' ? file.name : description;
  formData.append('description', desc);

  return authorizationHttp.post('/background/resource/addPdf', formData, {
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
export function getCacheImgApi() {
  return authorizationHttp.post('/background/resource/getCacheImg');
}
export function deleteAllCacheApi() {
  return authorizationHttp.post('/background/resource/deleteAllCache');
}


/**
 * 微云图床上传接口
 * @param {Set<string>|Array<string>} urls 要上传的图片URL集合或数组
 * @returns {Promise} 请求Promise
 * @throws {Error} 当参数不符合要求时抛出错误
 */
export function wyImgUploadApi(urls) {
  // 参数校验
  if (!urls || (typeof urls !== 'object')) {
    throw new Error('参数必须是Set或Array类型');
  }

  // 统一处理为数组
  const urlArray = Array.isArray(urls) ? urls : Array.from(urls);

  // 检查URL格式（可选）
  const isValid = urlArray.every(url => typeof url === 'string' && url.trim() !== '');
  if (!isValid) {
    throw new Error('所有URL必须是有效字符串');
  }

  return authorizationHttp.post('/background/resource/wyImgUpload', urlArray);
}
