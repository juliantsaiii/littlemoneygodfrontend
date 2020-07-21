import request from '@/utils/request'

// 查询附件历史列表
export function listFileuploadtask(query) {
  return request({
    url: '/sjwflowbusiness/fileuploadtask/list',
    method: 'get',
    params: query
  })
}

// 查询附件历史详细
export function getFileuploadtask(id) {
  return request({
    url: '/sjwflowbusiness/fileuploadtask/' + id,
    method: 'get'
  })
}

// 新增附件历史
export function addFileuploadtask(data) {
  return request({
    url: '/sjwflowbusiness/fileuploadtask',
    method: 'post',
    data: data
  })
}

// 修改附件历史
export function updateFileuploadtask(data) {
  return request({
    url: '/sjwflowbusiness/fileuploadtask',
    method: 'put',
    data: data
  })
}

// 删除附件历史
export function delFileuploadtask(id) {
  return request({
    url: '/sjwflowbusiness/fileuploadtask/' + id,
    method: 'delete'
  })
}

// 导出附件历史
export function exportFileuploadtask(query) {
  return request({
    url: '/sjwflowbusiness/fileuploadtask/export',
    method: 'get',
    params: query
  })
}