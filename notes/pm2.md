# PM2

## 静态资源

```sh
pm2 serve ./static 8080 --name app-static
```

## ecosystem.config.js

```javascript
module.exports = {
    apps: [
        {
            name: 'main',
            script: 'sh',
            args: [
                '-c',
                'while true; do sleep 1; echo `date`; done;'
            ],
            cwd: '.',
            interpreter: ''
        }
    ]
}
```