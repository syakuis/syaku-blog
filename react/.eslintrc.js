const path = require('path');

module.exports = {
  globals: {
    APP_ID: true,
  },
  env: {
    browser: true,
    es6: true,
    'jest/globals': true,
  },
  parser: 'babel-eslint',
  extends: ['airbnb', 'prettier'],
  plugins: ['prettier', 'react', 'jsx-a11y', 'jest'],
  settings: {
    'import/parser': 'babel-eslint',
    'import/resolver': 'webpack',
    'import/resolver': {
      webpack: { 
        config: './webpack.config.dev.js',
      },
    },
  },
  rules: {
    'prettier/prettier': ['error'],
    'import/no-unresolved': ['error', { commonjs: true, amd: true }],
    'import/extensions': ['error', 'never', { packages: 'always' }],
    'import/no-extraneous-dependencies': [
      'error',
      { devDependencies: true, peerDependencies: true },
    ],
    'react/jsx-filename-extension': ['error', { extensions: ['.js', '.jsx'] }],
    'jsx-a11y/label-has-for': [
      'error',
      {
        // components: ['Label'],
        required: {
          some: ['nesting', 'id'],
        },
        allowChildren: true,
      },
    ],
    'jsx-a11y/label-has-associated-control': [
      'error',
      {
        required: {
          some: ['nesting', 'id'],
        },
      },
    ],
    'jsx-a11y/click-events-have-key-events': ['off'],
    'jsx-a11y/mouse-events-have-key-events': ['off'],
    'jsx-a11y/anchor-is-valid': [
      'error',
      {
        components: ['Link'],
        specialLink: ['to', 'hrefLeft', 'hrefRight'],
        aspects: ['invalidHref'],
      },
    ],
  },
};
