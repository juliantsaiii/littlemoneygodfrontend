import request from '@/utils/request'

// 查询附件管理列表
export function listFileupload(query) {
  return request({
    url: '/sjwflowbusiness/fileupload/list',
    method: 'get',
    params: query
  })
}

// 查询附件管理详细
export function getFileupload(id) {
  return request({
    url: '/sjwflowbusiness/fileupload/' + id,
    method: 'get'
  })
}

// 新增附件管理
export function addFileupload(data) {
  return request({
    url: '/sjwflowbusiness/fileupload',
    method: 'post',
    data: data
  })
}

// 修改附件管理
export function updateFileupload(data) {
  return request({
    url: '/sjwflowbusiness/fileupload',
    method: 'put',
    data: data
  })
}

// 删除附件管理
export function delFileupload(id) {
  return request({
    url: '/sjwflowbusiness/fileupload/' + id,
    method: 'delete'
  })
}

// 导出附件管理
export function exportFileupload(query) {
  return request({
    url: '/sjwflowbusiness/fileupload/export',
    method: 'get',
    params: query
  })
}

export function getFileDownloadMsg(id) {
  return request({
    url: '/sjwflowbusiness/fileupload/getfilepath/' + id,
    method: 'get'
  })
}