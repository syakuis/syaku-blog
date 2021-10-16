const prod = require('./webpack.prod.js');

module.exports = env =>
  prod(env, {
    project: 'main',
    appId: 'app',
  });
