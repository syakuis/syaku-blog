/**
 * @date 2017-02-03 11:46:07
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 */
const merge = require('webpack-merge');
const common = require('./webpack.common.js');

module.exports = (env, args, config) =>
  merge(
    common(env, args),
    {
      mode: 'production',
      entry: {
        [args.project]: './src/index.js',
        [`${args.project}.min`]: `./src/index.js`,
      },
      output: {
        libraryTarget: 'umd',
        library: '[name]',
      },
      externals: {
        react: 'React',
        'react-dom': 'ReactDOM',
      },
    },
    config,
  );
