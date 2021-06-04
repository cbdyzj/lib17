const { resolve } = require('path')
const CopyPlugin = require('copy-webpack-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')

module.exports = function (env) {

    function getStyleLoaders(module = false) {

        const cssLoader = { loader: 'css-loader' }
        if (module) {
            cssLoader.options = {
                modules: {
                    localIdentName: '[local]__[hash:base64:5]',
                },
            }
        }

        const postcssLoader = {
            loader: 'postcss-loader',
            options: {
                postcssOptions: {
                    plugins: ['autoprefixer'],
                },
            },
        }

        return [
            { loader: 'style-loader' },
            cssLoader,
            postcssLoader,
            { loader: 'less-loader' },
        ]
    }

    const config = {
        mode: 'development',
        module: {
            rules: [{
                test: /\.jsx?$/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-react'],
                    },
                }],
                exclude: /node_modules/,
            }, {
                test: /\.(svg|png|jpg|jpeg|gif)$/,
                use: [{
                    loader: 'url-loader',
                    options: {
                        limit: 8192,
                        fallback: {
                            loader: 'file-loader',
                            options: { outputPath: 'assets' },
                        },
                    },
                }],
            }, {
                test: /\.module\.(css|less)$/,
                use: getStyleLoaders(true),
            }, {
                test: (name) => /\.(css|less)$/.test(name) && !/\.module\.(css|less)$/.test(name),
                use: getStyleLoaders(),
            }],
        },
        resolve: {
            extensions: ['.js', '.jsx'],
            alias: { '@': resolve(__dirname, 'src') },
        },
        plugins: [
            new CopyPlugin({ patterns: ['static'] }),
            new HtmlWebpackPlugin({
                filename: 'index.html',
                chunks: ['index'],
                template: 'index.html',
            }),
        ],
        optimization: {
            splitChunks: {
                chunks: 'all',
                automaticNameDelimiter: '_',
            },
        },
        entry: {
            index: './src/index.jsx',
        },
        output: {
            filename: '[name].[chunkhash].js',
            chunkFilename: '[name].[chunkhash].js',
            path: resolve(__dirname, 'dist'),
        },
    }

    // webpack serve
    if (env && env['WEBPACK_SERVE']) {
        const devConfig = {
            devServer: {
                hot: true,
            },
        }

        return {
            ...config,
            ...devConfig,
        }
    }

    return config

}
