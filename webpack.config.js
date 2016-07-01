var ExtractTextPlugin = require("extract-text-webpack-plugin");
var webpack = require("webpack");
var nodeEnvironment = process.env.NODE_ENV

var config = {
  context: __dirname,
  entry: { 
    'vendor' : "./frontend/src/ts/vendor.ts",
    'app' :  './frontend/src/ts/index.ts' ,
    'polyfills' : './frontend/src/ts/polyfills.ts'
  },
  resolve: {
    extensions: ['', '.js', '.ts',  '.tpl.html', '.css', '.less'],
    modulesDirectories: [".", "components", "node_modules"]
  },
  output : {
    path : __dirname,
    filename: '/public/javascripts/[name].bundle.js'
  },
  module: {
    loaders: [
      { test: /\.tpl.html/, loader: 'html'},
      { test: /\.ts$/, loader: 'awesome-typescript' },
      { test: /\.js$/, loader: 'babel', exclude: /node_modules/ },
      // Extract css files
      { test: /\.css$/, loader: ExtractTextPlugin.extract("style-loader", "css-loader") },
      // Optionally extract less files
      // or any other compile-to-css language
      { test: /\.less$/, loader: ExtractTextPlugin.extract("style-loader", "css-loader!less-loader") },
      { test: /\.jpe?g$|\.gif$|\.png$|\.svg$|\.eot$|\.woff$|\.woff2$|\.ttf$|\.wav$|\.mp3$/, loader: require.resolve("file-loader") + "?name=/assets/[name].[ext]"}
    ]
  },
  // Use the plugin to specify the resulting filename (and add needed behavior to the compiler)
  plugins: [ new ExtractTextPlugin("assets/style.css", {allChunks: true}) ]
};

module.exports = config;