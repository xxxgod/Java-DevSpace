2024年6月后，继续可以使用有效docker镜像仓库地址
编辑
`sudo vim /etc/docker/daemon.json`

写入

```
{
    "registry-mirrors": [
        "https://ustc-edu-cn.mirror.aliyuncs.com/",
        "https://ccr.ccs.tencentyun.com/",
        "https://docker.m.daocloud.io/"
    ]
}
```



重启 docker
`sudo service docker restart`
