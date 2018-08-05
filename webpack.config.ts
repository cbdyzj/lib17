import HtmlWebpackPlugin from 'html-webpack-plugin'
import { Configuration } from 'webpack';

const config: Configuration = {
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: 'ts-loader',
                exclude: /node_modules/
            }, {
                test: /\.(png|jpg|gif)$/,
                use: [{ loader: 'file-loader', options: { outputPath: 'assets' } }],
            },
            {
                test: /\.css$/,
                use: [{ loader: 'style-loader' }, { loader: 'css-loader' }]
            }
        ]
    },
    resolve: {
        extensions: ['.tsx', '.ts', '.js']
    },
    plugins: [
        new HtmlWebpackPlugin({ template: 'template/index.html' })
    ],
    mode: 'development',
}

export default config