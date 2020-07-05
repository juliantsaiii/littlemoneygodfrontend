import request from '@/utils/request'

// 查询部门列表
export function listDept(query) {
  return request({
    url: '/sjwflowsys/dept/list',
    method: 'get',
    params: query
  })
}

// 查询部门详细
export function getDept(id) {
  return request({
    url: '/sjwflowsys/dept/' + id,
    method: 'get'
  })
}

// 新增部门
export function addDept(data) {
  return request({
    url: '/sjwflowsys/dept',
    method: 'post',
    data: data
  })
}

// 修改部门
export function updateDept(data) {
  return request({
    url: '/sjwflowsys/dept',
    method: 'put',
    data: data
  })
}

// 删除部门
export function delDept(id) {
  return request({
    url: '/sjwflowsys/dept/' + id,
    method: 'delete'
  })
}

// 导出部门
export function exportDept(query) {
  return request({
    url: '/sjwflowsys/dept/export',
    method: 'get',
    params: query
  })
}