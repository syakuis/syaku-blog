module.exports = {
  setupFiles: ['raf/polyfill'],
  setupTestFrameworkScriptFile: '<rootDir>src/setupTests.js',
  moduleFileExtensions: ['jsx', 'js', 'json'],
  testPathIgnorePatterns: ['<rootDir>/(dist|node_modules)/'],
  watchPathIgnorePatterns: [],
  testRegex: '(/__tests__/.*|\\.(test|spec))\\.(js|jsx)$',
  transform: {
    '^.+\\.js$': 'babel-jest',
  },
};
