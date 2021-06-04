module.exports = {
    apps: [
        {
            name: 'boot',
            script: 'java',
            args: [
                '-jar',
                'build/libs/boot-0.1.0.jar',
            ],
            cwd: '.',
            interpreter: ''
        }
    ]
}
