import request from '@/utils/request'

/**
 * 清除用户登录缓存
 */
export function refreshLoginRedis(params) {
    return request({
        url: '/sjwflowbusiness/problemlist/deleteRedis',
        method: 'post',
        data: params
    });
}