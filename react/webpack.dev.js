/**
 * @date 2017-02-03 11:46:07
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 */
const path = require('path'); // node.js 내장 패키지
const webpack = require('webpack');
const merge = require('webpack-merge');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const HtmlWebpackExcludeAssetsPlugin = require('html-webpack-exclude-assets-plugin');
const common = require('./webpack.common.js');

module.exports = (env, args, config) =>
  merge(
    common(env, args),
    {
      mode: 'development',
      // devtool: 'inline-source-map',
      devtool: 'cheap-module-source-map',
      entry: {
        [args.project]: './src/index.js',
      },
      output: {
        pathinfo: true,
      },
      plugins: [
        new webpack.LoaderOptionsPlugin({
          debug: true,
        }),
        new webpack.HotModuleReplacementPlugin(),
        new HtmlWebpackPlugin({
          filename: 'index.html',
          template: path.join(__dirname, 'index.html'),
        }),
        new HtmlWebpackExcludeAssetsPlugin(),
      ],
      module: {
        rules: [
          {
            test: /\.(html)$/,
            use: {
              loader: 'html-loader',
              options: {
                attrs: [':data-src'],
              },
            },
          },
        ],
      },
      devServer: {
        historyApiFallback: true,
        contentBase: 'dist',
        open: true,
      },
    },
    config,
  );
