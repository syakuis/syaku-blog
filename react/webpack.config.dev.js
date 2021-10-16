const dev = require('./webpack.dev.js');

module.exports = env =>
  dev(env, {
    project: 'main',
    appId: 'app',
  });
