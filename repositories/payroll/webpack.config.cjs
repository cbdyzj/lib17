const { resolve } = require('path')
const CopyPlugin = require('copy-webpack-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')

module.exports = function (env) {

    function getStyleLoaders(module = false) {

        const cssLoader = { loader: 'css-loader' }
        if (module) {
            cssLoader.options = { modules: true }
        }

        return [
            { loader: 'style-loader' },
            cssLoader,
            { loader: 'less-loader' },
        ]
    }

    return {
        module: {
            rules: [
                {
                    test: /\.tsx?$/,
                    use: [{ loader: 'ts-loader' }],
                    exclude: /node_modules/,
                }, {
                    test: /\.(png|jpg|jpeg|gif)$/,
                    use: [{ loader: 'file-loader', options: { outputPath: 'assets' } }],
                }, {
                    test: /\.module\.(css|less)$/,
                    use: getStyleLoaders(true),
                }, {
                    test: (name) => /\.(css|less)$/.test(name) && !/\.module\.(css|less)$/.test(name),
                    use: getStyleLoaders(),
                },
            ]
        },
        resolve: {
            extensions: ['.tsx', '.ts', '.js'],
            alias: { '@': resolve(__dirname, './static') },
        },
        plugins: [
            new CopyPlugin({ patterns: ['./static/favicon.ico'] }),
            new HtmlWebpackPlugin({ template: './static/index.html' }),
        ],
        optimization: {
            splitChunks: {
                chunks: 'all',
                automaticNameDelimiter: '_',
            },
        },
        entry: './static/index.tsx',
        output: {
            filename: '[name].[chunkhash].js',
            chunkFilename: '[name].[chunkhash].js',
            path: resolve(__dirname, 'dist')
        },
        mode: 'development',
    }
}
