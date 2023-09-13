const path = require('path');

module.exports = {
    entry: './index.js',
    output: {
        filename: 'ejson-shell-parser.js',
        path: path.resolve(__dirname, '..', 'src', 'main', 'resources'),
    },
    optimization: {
      minimize: false
    },
    target: 'node',
    resolve: {
      alias: {
        bson: require.resolve('bson')
      }
    }
};
