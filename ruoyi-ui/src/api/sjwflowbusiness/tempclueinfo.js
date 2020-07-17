import request from '@/utils/request'

// 查询线索操作列表
export function listTempclueinfo(query) {
  return request({
    url: '/sjwflowbusiness/tempclueinfo/list',
    method: 'get',
    params: query
  })
}

// 查询线索操作详细
export function getTempclueinfo(id) {
  return request({
    url: '/sjwflowbusiness/tempclueinfo/' + id,
    method: 'get'
  })
}

// 新增线索操作
export function addTempclueinfo(data) {
  return request({
    url: '/sjwflowbusiness/tempclueinfo',
    method: 'post',
    data: data
  })
}

// 修改线索操作
export function updateTempclueinfo(data) {
  return request({
    url: '/sjwflowbusiness/tempclueinfo',
    method: 'put',
    data: data
  })
}

// 删除线索操作
export function delTempclueinfo(id) {
  return request({
    url: '/sjwflowbusiness/tempclueinfo/' + id,
    method: 'delete'
  })
}

// 导出线索操作
export function exportTempclueinfo(query) {
  return request({
    url: '/sjwflowbusiness/tempclueinfo/export',
    method: 'get',
    params: query
  })
}