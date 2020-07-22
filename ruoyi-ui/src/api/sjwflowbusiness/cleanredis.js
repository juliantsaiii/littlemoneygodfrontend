import request from '@/utils/request'

/**
 * 清除用户登录缓存
 */
export function refreshLoginRedis(id) {
    return request({
        url: '/sjwflowbusiness/problemlist/deleteRedis/' + id,
        method: 'post'
    });
}