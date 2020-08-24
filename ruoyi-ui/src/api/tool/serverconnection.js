import request from '@/utils/request'

export function getConnectionStatus() {
    return request({
        url: '/sjwflowsys/roledata/getDeptIDs',
        methods: 'get'
    });
}