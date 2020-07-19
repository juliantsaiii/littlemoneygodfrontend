import request from '@/utils/request'

// 查询流程记录列表
export function listWorkflowtask(query) {
  return request({
    url: '/sjwflowbusiness/workflowtask/list',
    method: 'get',
    params: query
  })
}

// 查询流程记录详细
export function getWorkflowtask(id) {
  return request({
    url: '/sjwflowbusiness/workflowtask/' + id,
    method: 'get'
  })
}

// 新增流程记录
export function addWorkflowtask(data) {
  return request({
    url: '/sjwflowbusiness/workflowtask',
    method: 'post',
    data: data
  })
}

// 修改流程记录
export function updateWorkflowtask(data) {
  return request({
    url: '/sjwflowbusiness/workflowtask',
    method: 'put',
    data: data
  })
}

// 删除流程记录
export function delWorkflowtask(id) {
  return request({
    url: '/sjwflowbusiness/workflowtask/' + id,
    method: 'delete'
  })
}

// 导出流程记录
export function exportWorkflowtask(query) {
  return request({
    url: '/sjwflowbusiness/workflowtask/export',
    method: 'get',
    params: query
  })
}