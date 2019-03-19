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
