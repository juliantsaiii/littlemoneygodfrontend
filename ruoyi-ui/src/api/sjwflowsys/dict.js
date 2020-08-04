import request from '@/utils/request'

// 查询业务字典列表
export function listDict(query) {
  return request({
    url: '/sjwflowsys/dict/list',
    method: 'get',
    params: query
  })
}

// 查询业务字典详细
export function getDict(id) {
  return request({
    url: '/sjwflowsys/dict/' + id,
    method: 'get'
  })
}

// 新增业务字典
export function addDict(data) {
  return request({
    url: '/sjwflowsys/dict',
    method: 'post',
    data: data
  })
}

// 修改业务字典
export function updateDict(data) {
  return request({
    url: '/sjwflowsys/dict',
    method: 'put',
    data: data
  })
}

// 删除业务字典
export function delDict(id) {
  return request({
    url: '/sjwflowsys/dict/' + id,
    method: 'delete'
  })
}

// 导出业务字典
export function exportDict(query) {
  return request({
    url: '/sjwflowsys/dict/export',
    method: 'get',
    params: query
  })
}