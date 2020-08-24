import request from '@/utils/request'

export function getConnectionStatus() {
    return request({
        url: '/sjwflowbusiness/serverconn/GetConnectionStatus',
        methods: 'get'
    });
}