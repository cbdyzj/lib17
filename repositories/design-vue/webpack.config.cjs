const { resolve } = require('path')
const VueLoaderPlugin = require('vue-loader/lib/plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')

const entries = [
    { entryName: 'index', entryFile: './src/index.js' },
    { entryName: 'counter', entryFile: './src/counter.js' },
    { entryName: 'sample', entryFile: './src/sample.js' },
]

const config = {
    module: {
        rules: [
            {
                test: /\.vue$/,
                loader: 'vue-loader',
            }, {
                test: /\.jsx?$/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: [
                            '@vue/babel-preset-jsx',
                        ],
                        plugins: [
                            ["import", {
                                "libraryName": "ant-design-vue",
                                "libraryDirectory": "es",
                                "style": "css"
                            }]
                        ]
                    }

                }],
                exclude: /node_modules/
            }, {
                test: /\.(svg|png|jpg|jpeg|gif)$/,
                use: [{
                    loader: 'url-loader',
                    options: {
                        limit: 8192,
                        fallback: {
                            loader: 'file-loader',
                            options: { outputPath: 'assets' }
                        }
                    }
                }],
            }, {
                test: /\.module\.(css|less)$/,
                use: [
                    { loader: 'style-loader' },
                    { loader: 'css-loader', options: { modules: true } },
                    { loader: 'less-loader' },
                ],
            }, {
                test: (name) => /\.(css|less)$/.test(name) && !/\.module\.(css|less)$/.test(name),
                use: [
                    { loader: 'style-loader' },
                    { loader: 'css-loader' },
                    { loader: 'less-loader' },
                ],
            },
        ]
    },
    resolve: {
        extensions: ['.vue', '.js', '.jsx'],
        alias: {
            '@': resolve(__dirname, 'src')
        }
    },
    plugins: [
        new VueLoaderPlugin(),
        ...entries.map(entry => new HtmlWebpackPlugin({
            filename: `${entry.entryName}.html`,
            chunks: [entry.entryName],
            template: 'index.html',
        })),
    ],
    optimization: {
        splitChunks: {
            chunks: 'all',
            automaticNameDelimiter: '_',
        },
    },
    entry: entries.reduce((a, c) => Object.assign(a, { [c.entryName]: c.entryFile }), {}),
    output: {
        filename: '[name].[chunkhash].js',
        chunkFilename: '[name].[chunkhash].js',
        path: resolve(__dirname, 'dist')
    },
    mode: 'development',
}

module.exports = config
