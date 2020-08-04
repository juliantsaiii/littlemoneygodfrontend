import request from '@/utils/request'

// 查询流程管理列表
export function listWorkflowinfo(query) {
  return request({
    url: '/sjwflowsys/workflowinfo/list',
    method: 'get',
    params: query
  })
}

// 查询流程管理详细
export function getWorkflowinfo(id) {
  return request({
    url: '/sjwflowsys/workflowinfo/' + id,
    method: 'get'
  })
}

// 新增流程管理
export function addWorkflowinfo(data) {
  return request({
    url: '/sjwflowsys/workflowinfo',
    method: 'post',
    data: data
  })
}

// 修改流程管理
export function updateWorkflowinfo(data) {
  return request({
    url: '/sjwflowsys/workflowinfo',
    method: 'put',
    data: data
  })
}

// 删除流程管理
export function delWorkflowinfo(id) {
  return request({
    url: '/sjwflowsys/workflowinfo/' + id,
    method: 'delete'
  })
}

// 导出流程管理
export function exportWorkflowinfo(query) {
  return request({
    url: '/sjwflowsys/workflowinfo/export',
    method: 'get',
    params: query
  })
}

// 查询流程树
export function getWorkflowinfoTree() {
  return request({
    url: '/sjwflowsys/workflowinfo/gettree',
    method: "get"
  })
}